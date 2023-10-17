package com.example.dmsserver.commons.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "DocSaveBO", description = "文档保存参数封装")
public class DocSaveBO {
    private Long id;

    private Long ebookId;

    private String name;

    private Integer sort;

    private Long parent;

    private String content;
}
