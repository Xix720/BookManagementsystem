package com.example.dmsserver.commons.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(value = "PageParam", description = "分页参数封装")
public class PageParam {
    @ApiParam(name="current",value = "分页参数，当前页码，默认为1")
    private Long current = 1L;
    @ApiParam(name="size",value = "分页参数，每页显示行数，默认为10")
    private Long size = 10L;
}