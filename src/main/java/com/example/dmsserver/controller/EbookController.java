package com.example.dmsserver.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dmsserver.commons.bo.EbookBO;
import com.example.dmsserver.commons.result.JSONResult;
import com.example.dmsserver.commons.vo.EbookVO;
import com.example.dmsserver.commons.vo.PageVO;
import com.example.dmsserver.entity.Ebook;
import com.example.dmsserver.service.EbookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@Api(value = "EbookController", tags = {"EbookController"})
@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @ApiOperation(value = "list", notes = "图书列表", httpMethod = "GET")
    @GetMapping("/list")
    public JSONResult<Page<EbookVO>> list(EbookBO ebookBO){

        PageVO<EbookVO> ebookPage = ebookService.listByParam(ebookBO);

        return JSONResult.ok(ebookPage);
    }

    @ApiOperation(value = "save", notes = "图书保存与更新", httpMethod = "POST")
    @PostMapping("/save")
    public JSONResult save(@RequestBody Ebook ebook){

        boolean hasSave = ebookService.saveOrUpdate(ebook);
        if(hasSave){
            return JSONResult.ok();
        }
        else{
            return JSONResult.errorMsg("数据保存失败");
        }
    }

    @ApiOperation(value = "delete", notes = "图书删除", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public JSONResult delete(Long id){

        boolean hasDelete = ebookService.removeById(id);
        if(hasDelete){
            return JSONResult.ok();
        }
        else{
            return JSONResult.errorMsg("删除失败");
        }
    }
}

