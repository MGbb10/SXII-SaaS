package com.usian.model.admin.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdUserDto {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "登陆用户名")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "登陆密码")
    private String password;
}
