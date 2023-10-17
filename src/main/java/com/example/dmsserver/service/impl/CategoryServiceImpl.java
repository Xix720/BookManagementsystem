package com.example.dmsserver.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dmsserver.commons.util.CopyUtil;
import com.example.dmsserver.commons.vo.CategoryVO;
import com.example.dmsserver.entity.Category;
import com.example.dmsserver.mapper.CategoryMapper;
import com.example.dmsserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> getAll() {
        List<Category> categoryList = categoryMapper.selectList(null);

        List<CategoryVO> categoryVOList = CopyUtil.copyList(categoryList, CategoryVO.class);

        return categoryVOList;
    }

    @Override
    public int ChildrenCount(Integer id) {
        return categoryMapper.ChildrenCount(id);
    }
}
