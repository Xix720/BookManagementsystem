package com.example.dmsserver.commons.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "ResetPwdBO", description = "重置密码请求参数封装")
public class ResetPwdBO {
    private Long id;
    private String password;
}
