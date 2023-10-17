package com.example.dmsserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("doc")
@ApiModel(value = "Doc对象", description = "")
public class Doc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("ebookId")
    private Long ebookId;

    @TableField("name")
    private String name;

    @TableField("sort")
    private Integer sort;

    @TableField("viewCount")
    private Integer viewCount;

    @TableField("voteCount")
    private Integer voteCount;

    @TableField("parent")
    private Long parent;
}
