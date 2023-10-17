package com.example.dmsserver.controller;


import com.example.dmsserver.commons.result.JSONResult;
import com.example.dmsserver.entity.Content;
import com.example.dmsserver.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/getById/{id}")
    public JSONResult<String> getById(@PathVariable Long id){
        Content content = contentService.getById(id);
        return JSONResult.ok(content.getContent());
    }
}

