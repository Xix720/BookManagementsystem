package com.example.dmsserver.commons.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(value = "EbookBO", description = "分页列表请求参数封装")
public class EbookBO extends PageParam{
    @ApiParam(name="name",value = "图书名称")
    private String name;

    @ApiParam(name="category2Id",value = "二级分类id")
    private Integer category2Id;
}
