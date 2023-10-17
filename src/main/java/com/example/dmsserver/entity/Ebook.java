package com.example.dmsserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
@Accessors(chain = true)
@TableName("ebook")
@ApiModel(value = "Ebook对象", description = "")
public class Ebook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("category1Id")
    private Long category1Id;

    @TableField("category2Id")
    private Long category2Id;

    @TableField("description")
    private String description;

    @TableField("cover")
    private String cover;

    @TableField("docCount")
    private Integer docCount;

    @TableField("viewCount")
    private Integer viewCount;

    @TableField("voteCount")
    private Integer voteCount;


}
