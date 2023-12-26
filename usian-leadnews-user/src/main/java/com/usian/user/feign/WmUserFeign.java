package com.usian.user.feign;

import com.usian.model.common.dtos.ResponseResult;
import com.usian.model.media.pojos.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("leadnews-wemedia")
public interface WmUserFeign {
    @PostMapping("/api/v1/user/save")
    public ResponseResult save(@RequestBody WmUser wmUser);

    @GetMapping("/api/v1/user/findByName/{name}")
    public WmUser findByName(@PathVariable("name")String name);
}
