package com.usian.article.controller.v1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.usian.apis.artical.AuthorControllerApi;
import com.usian.article.service.AuthorService;
import com.usian.model.article.pojos.ApAuthor;
import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.common.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController implements AuthorControllerApi {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/findByUserId/{id}")
    @Override
    public ApAuthor findByUserId(@PathVariable("id") Integer id) {
        List<ApAuthor> list = authorService.list(Wrappers.<ApAuthor>lambdaQuery().eq(ApAuthor::getUserId, id));
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody ApAuthor apAuthor) {
        apAuthor.setCreatedTime(new Date());
        authorService.save(apAuthor);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @GetMapping("/findByName/{name}")
    @Override
    public ApAuthor findByName(@PathVariable("name") String name) {
        ApAuthor apAuthor = authorService.getOne(Wrappers.<ApAuthor>lambdaQuery().eq(ApAuthor::getName, name));
        return apAuthor;
    }

    @GetMapping("/one/{id}")
    @Override
    public ApAuthor findById(@PathVariable("id") Integer id) {
        return authorService.getById(id);
    }
}
