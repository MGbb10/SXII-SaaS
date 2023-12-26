package com.usian.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usian.article.mapper.ApArticleMapper;
import com.usian.article.service.ApArticleService;
import com.usian.common.constants.article.ArticleConstants;
import com.usian.model.article.dtos.ArticleHomeDto;
import com.usian.model.article.pojos.ApArticle;
import com.usian.model.common.dtos.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    //单页最大加载的数字
    private final static short MAX_PAGE_SIZE=50;
    @Autowired
    private ApArticleMapper apArticleMapper;
    @Value("${fdf.url}")
    private String fileServerUrl;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short loadType) {
        //1.校验参数
        Integer size = dto.getSize();
        if (size==null ||size==0){
            size=10;
        }
        size=Math.min(size,MAX_PAGE_SIZE);
        dto.setSize(size);
        //类型参数校验
        if (!loadType.equals(ArticleConstants.LOADTYPE_LOAD_MORE)&&!loadType.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            loadType=ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        //文章频道校验
        if (StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        //时间校验
        if (dto.getMaxBehotTime()==null){
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime()==null){
            dto.setMinBehotTime(new Date());
        }
        //查询数据
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, loadType);
        //结果封装
        ResponseResult responseResult=ResponseResult.okResult(apArticles);
        responseResult.setHost(fileServerUrl);
        return responseResult;
    }
}
