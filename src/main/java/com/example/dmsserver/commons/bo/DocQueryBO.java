package com.example.dmsserver.commons.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "Doc对象", description = "文档查询参数封装")
public class DocQueryBO {
    private String ebookId;
}
