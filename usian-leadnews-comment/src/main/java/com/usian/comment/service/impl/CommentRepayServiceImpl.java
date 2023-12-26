package com.usian.comment.service.impl;

import com.usian.comment.feign.UserFeign;
import com.usian.comment.service.CommentRepayService;
import com.usian.model.comment.dtos.CommentRepayDto;
import com.usian.model.comment.dtos.CommentRepayLikeDto;
import com.usian.model.comment.dtos.CommentRepaySaveDto;
import com.usian.model.comment.pojos.ApComment;
import com.usian.model.comment.pojos.ApCommentLike;
import com.usian.model.comment.pojos.ApCommentRepay;
import com.usian.model.comment.pojos.ApCommentRepayLike;
import com.usian.model.comment.vo.ApCommentRepayVo;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentRepayServiceImpl implements CommentRepayService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserFeign userFeign;
    @Override
    public ResponseResult loadCommentRepay(CommentRepayDto dto) {
        //1.检查参数
        if (dto.getCommentId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        if (dto.getSize()==null ||dto.getSize()==0){
            dto.setSize(10);
        }
        //2.按照评论id过渡，设置分页和排序
        Query query=Query.query(Criteria.where("commentId").is(dto.getCommentId()).and("createdTime").lt(dto.getMinDate()));
        query.limit(dto.getSize()).with(Sort.by(Sort.Direction.DESC,"createdTime"));
        List<ApCommentRepay> list = mongoTemplate.find(query, ApCommentRepay.class);
        //3.数据封装返回
        //3.1用户未登录 加载数据
        ApUser user = AppThreadLocalUtils.getUser();
        if (user==null){
            return ResponseResult.okResult(list);
        }
        //3.2用户已登录，加载数据，需要判断当前用户点赞了哪些评论
        List<String> idList = list.stream().map(x -> x.getId()).collect(Collectors.toList());
        Query query1=Query.query(Criteria.where("commentRepayId").in(idList).and("authorId").is(user.getId()));
        List<ApCommentRepayLike> apCommentRepayLikes = mongoTemplate.find(query1, ApCommentRepayLike.class);
        List<ApCommentRepayVo> resultList=new ArrayList<>();
        if (list!=null&&apCommentRepayLikes!=null){
            list.stream().forEach(x->{
                ApCommentRepayVo apCommentRepayVo = new ApCommentRepayVo();
                BeanUtils.copyProperties(x,apCommentRepayVo);
                for (ApCommentRepayLike apCommentRepayLike : apCommentRepayLikes) {

                    if (x.getId().equals(apCommentRepayLike.getCommentRepayId())){
                        apCommentRepayVo.setOperation((short) 0);
                        break;
                    }
                }
                resultList.add(apCommentRepayVo);
            });
            return ResponseResult.okResult(resultList);
        }
        else {
            return ResponseResult.okResult(list);
        }
    }

    @Override
    public ResponseResult saveCommentRepay(CommentRepaySaveDto dto) {
        //1.检查参数
        if (dto.getCommentId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        if (dto.getContent()!=null&& dto.getContent().length()>140){
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
        ApCommentRepay apCommentRepay = new ApCommentRepay();
        apCommentRepay.setAuthorId(apUser.getId());
        apCommentRepay.setAuthorName(apUser.getName());
        apCommentRepay.setContent(dto.getContent());
        apCommentRepay.setCommentId(dto.getCommentId());
        apCommentRepay.setCreatedTime(new Date());
        apCommentRepay.setUpdatedTime(new Date());
        apCommentRepay.setLikes(0);
        mongoTemplate.insert(apCommentRepay);
        //更新评论的回复数量
        ApComment apComment = mongoTemplate.findById(dto.getCommentId(), ApComment.class);
        apComment.setReply(apComment.getReply()+1);
        mongoTemplate.save(apComment);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult saveCommentRepayLike(CommentRepayLikeDto dto) {
        //1.检查参数
        if (dto.getCommentRepayId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //2.判断是否登录
        ApUser user = AppThreadLocalUtils.getUser();
        if (user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        //3.点赞操作
        ApCommentRepay apCommentRepay = mongoTemplate.findById(dto.getCommentRepayId(), ApCommentRepay.class);
        if (apCommentRepay!=null&&dto.getOperation()==0){
            //更新评论的点赞数量
            apCommentRepay.setLikes(apCommentRepay.getLikes()+1);
            mongoTemplate.save(apCommentRepay);
            //保存app评论信息点赞
            ApCommentRepayLike apCommentLike = new ApCommentRepayLike();
            apCommentLike.setAuthorId(user.getId());
            apCommentLike.setCommentRepayId(apCommentRepay.getId());
            apCommentLike.setOperation(dto.getOperation());
            mongoTemplate.save(apCommentLike);
        }else if (apCommentRepay!=null&&dto.getOperation()==1){
            //取消点赞
            //更新评论的点赞数量
            apCommentRepay.setLikes(apCommentRepay.getLikes()<0?0:apCommentRepay.getLikes()-1);
            mongoTemplate.save(apCommentRepay);
            //更新app评论信息点赞
            mongoTemplate.remove(Query.query(Criteria.where("authorId").is(user.getId()).and("commentRepayId").is(apCommentRepay.getId())), ApCommentLike.class);
        }
        //5.数据返回
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("likes",apCommentRepay.getLikes());
        return ResponseResult.okResult(resultMap);
    }
}
