package com.example.dmsserver.commons.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "PageVO", description = "分页结果封装对象")
@Data
public class PageVO<T> {
    @ApiModelProperty("分页数据集")
    private List<T> records;
    @ApiModelProperty("总行数")
    private Long total;
    @ApiModelProperty("页码显示行数")
    private Long size;
    @ApiModelProperty("当前页码")
    private Long current;
    @ApiModelProperty("总页码数")
    private Long pages;
}
