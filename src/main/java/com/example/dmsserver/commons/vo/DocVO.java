package com.example.dmsserver.commons.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "Doc对象", description = "")
public class DocVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String ebookId;

    private String name;

    private Integer sort;

    private Integer viewCount;

    private Integer voteCount;

    private String parent;

}
