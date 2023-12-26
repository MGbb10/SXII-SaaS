package com.usian.comment.service.impl;

import com.usian.comment.feign.UserFeign;
import com.usian.comment.service.CommentService;
import com.usian.model.comment.dtos.CommentDto;
import com.usian.model.comment.dtos.CommentSaveDto;
import com.usian.model.comment.pojos.ApComment;
import com.usian.model.comment.pojos.ApCommentLike;
import com.usian.model.comment.vo.ApCommentVo;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.common.enums.AppHttpCodeEnum;
import com.usian.model.user.pojos.ApUser;
import com.usian.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserFeign userFeign;

    @Override
    public ResponseResult saveComment(CommentSaveDto dto) {
        //1.检查参数
        if (dto.getArticleId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        if (dto.getContent()!=null && dto.getContent().length()>140){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE,"评论内容不能超过140字");
        }
        //2.判断是否登录
        ApUser user = AppThreadLocalUtils.getUser();
        if (user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        //4.保存评论
        ApUser apUser = userFeign.findUserById(user.getId().longValue());
        if (apUser==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE,"当前登录信息有误");
        }
        ApComment apComment = new ApComment();
        apComment.setAuthorId(apUser.getId());
        apComment.setAuthorName(apUser.getName());
        apComment.setContent(dto.getContent());
        apComment.setEntryId(dto.getArticleId());
        apComment.setCreatedTime(new Date());
        apComment.setUpdatedTime(new Date());
        apComment.setImage(apUser.getImage());
        apComment.setLikes(0);
        apComment.setReply(0);
        apComment.setType((short) 0);
        apComment.setFlag((short) 0);
        mongoTemplate.insert(apComment);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult findByArticleId(CommentDto dto) {
        //1.检查参数
        if (dto.getArticleId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        int size=10;
        //2.按照文章id过渡，设置分页和排序
        Query query = Query.query(Criteria.where("entryId").is(dto.getArticleId()).and("createdTime").lt(dto.getMinDate()));
        query.limit(size).with(Sort.by(Sort.Direction.DESC,"createdTime"));
        List<ApComment> list = mongoTemplate.find(query, ApComment.class);
        //3.数据封装返回
        //3.1用户未登录 加载数据
        ApUser user = AppThreadLocalUtils.getUser();
        if (user==null){
            return ResponseResult.okResult(list);
        }
        //3.2用户已登录，加载数据，需要判断当前用户点赞了哪些评论
        List<String> idList = list.stream().map(x -> x.getId()).collect(Collectors.toList());
        Query query1 = Query.query(Criteria.where("commentId").in(idList).and("authorId").is(user.getId()));
        List<ApCommentLike> apCommentLikes = mongoTemplate.find(query1, ApCommentLike.class);
        List<ApCommentVo> resultlist=new ArrayList<>();
        if (list!=null && apCommentLikes!=null){
            list.stream().forEach(x->{
                ApCommentVo apCommentVo = new ApCommentVo();
                BeanUtils.copyProperties(x,apCommentVo);
                for (ApCommentLike apCommentLike:apCommentLikes){
                    if (x.getId().equals(apCommentLike.getCommentId())){
                        apCommentVo.setOperation((short) 0);
                        break;
                    }
                }
                resultlist.add(apCommentVo);
            });
            return ResponseResult.okResult(resultlist);
        }else {
            return ResponseResult.okResult(list);
        }
    }
}
