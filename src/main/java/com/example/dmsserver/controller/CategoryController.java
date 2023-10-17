package com.example.dmsserver.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dmsserver.commons.bo.EbookBO;
import com.example.dmsserver.commons.result.JSONResult;
import com.example.dmsserver.commons.vo.CategoryVO;
import com.example.dmsserver.commons.vo.EbookVO;
import com.example.dmsserver.commons.vo.PageVO;
import com.example.dmsserver.entity.Category;
import com.example.dmsserver.entity.Ebook;
import com.example.dmsserver.service.CategoryService;
import com.example.dmsserver.service.EbookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@Api(value = "CategoryController", tags = {"CategoryController"})
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "list", notes = "分类列表", httpMethod = "GET")
    @GetMapping("/list")
    public JSONResult list(){

        List<CategoryVO> categoryVOList = categoryService.getAll();

        return JSONResult.ok(categoryVOList);
    }

    @ApiOperation(value = "save", notes = "分类保存与更新", httpMethod = "POST")
    @PostMapping("/save")
    public JSONResult save(@RequestBody Category category){

        boolean hasSave = categoryService.saveOrUpdate(category);
        if(hasSave){
            return JSONResult.ok();
        }
        else{
            return JSONResult.errorMsg("数据保存失败");
        }
    }

    @ApiOperation(value = "delete", notes = "分类删除", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public JSONResult delete(Integer id){
        //如果有子类别，则不能删除
        int ChildrenCount = categoryService.ChildrenCount(id);
        if(ChildrenCount > 0){
            return JSONResult.errorMsg("该分类下有子类别，不能删除");
        }

        boolean hasDelete = categoryService.removeById(id);
        if(hasDelete){
            return JSONResult.ok();
        }
        else{
            return JSONResult.errorMsg("删除失败");
        }
    }
}

