package com.example.dmsserver.commons.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "LoginUserBO", description = "登录请求参数封装")
public class LoginUserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录账号")
    @NotNull(message = "用户名不能未空")
    private String loginName;

    @ApiModelProperty(value = "登录密码")
    @NotNull(message = "密码不能未空")
    private String password;
}