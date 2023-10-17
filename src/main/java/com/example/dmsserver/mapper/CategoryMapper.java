package com.example.dmsserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dmsserver.entity.Category;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
public interface CategoryMapper extends BaseMapper<Category> {

    int ChildrenCount(Integer id);
}
