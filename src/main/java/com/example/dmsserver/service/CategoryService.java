package com.example.dmsserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dmsserver.commons.vo.CategoryVO;
import com.example.dmsserver.entity.Category;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
public interface CategoryService extends IService<Category> {
    public List<CategoryVO> getAll();

    int ChildrenCount(Integer id);
}
