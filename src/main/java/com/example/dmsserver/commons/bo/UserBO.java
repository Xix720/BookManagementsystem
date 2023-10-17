package com.example.dmsserver.commons.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "UserBO", description = "用户列表查询参数封装")
public class UserBO extends PageParam{
    private String loginName;
}
