package com.usian.user.feign;

import com.usian.model.article.pojos.ApAuthor;
import com.usian.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("leadnews-artical")
public interface ArticlelFeign {
    @GetMapping("/api/v1/author/fingByUserId/{id}")
    public ApAuthor findByUserId(@PathVariable("id") Integer id);

    @PostMapping("/api/v1/author/save")
    public ResponseResult save(@RequestBody ApAuthor apAuthor);

    @GetMapping("/api/v1/author/one/{id}")
    public ApAuthor selectByI(@PathVariable("id") Integer id);
}
