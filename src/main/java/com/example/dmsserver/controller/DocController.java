package com.example.dmsserver.controller;


import com.example.dmsserver.commons.bo.DocQueryBO;
import com.example.dmsserver.commons.bo.DocSaveBO;
import com.example.dmsserver.commons.result.JSONResult;
import com.example.dmsserver.commons.result.ResponseStatusEnum;
import com.example.dmsserver.commons.vo.DocVO;
import com.example.dmsserver.entity.Doc;
import com.example.dmsserver.service.DocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@Api(value = "DocController", tags = {"DocController"})
@RestController
@RequestMapping("/doc")
public class DocController {
    @Autowired
    private DocService docService;

    @ApiOperation(value = "文档查询", notes = "文档查询", httpMethod = "GET")
    @GetMapping("/list")
    public JSONResult<List> list(DocQueryBO docBO){
        List<DocVO> docVOList = docService.listByParam(docBO);
        return new JSONResult(ResponseStatusEnum.SUCCESS,docVOList);
    }

    @ApiOperation(value = "文档保存修改", notes = "文档保存修改", httpMethod = "POST")
    @PostMapping("/save")
    public JSONResult save(@RequestBody DocSaveBO docSaveBO){
        boolean hasSave = docService.saveOrUpdate(docSaveBO);
        if(hasSave){
            return JSONResult.ok();
        }
        else{
            return JSONResult.error();
        }
    }

    @ApiOperation(value = "删除文档", notes = "删除文档", httpMethod = "DELETE")
    @DeleteMapping("/delete/{ids}")
    public JSONResult delete(@PathVariable String ids){
        String[] idArray = ids.split(",");

        boolean hasDelete = docService.removeByIds(Arrays.asList(idArray));

        if(hasDelete){
            return JSONResult.ok();
        }
        else{
            return JSONResult.error();
        }
    }
}

