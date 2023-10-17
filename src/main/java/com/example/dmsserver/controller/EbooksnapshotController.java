package com.example.dmsserver.controller;


import com.example.dmsserver.commons.bo.VoteBO;
import com.example.dmsserver.entity.Ebooksnapshot;
import com.example.dmsserver.service.EbooksnapshotService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@RestController
@RequestMapping("/ebooksnapshot")
public class EbooksnapshotController {
    @Autowired
    EbooksnapshotService ebooksnapshotService;
    //新增点赞数量
    @ApiOperation(value = "addLike", notes = "点赞", httpMethod = "POST")
    @PostMapping("/addLike")
    public void addVoteCount(Long ebookId){

        System.out.println("收到的id:"+ebookId);
        ebooksnapshotService.addlike(ebookId);

    }

    //新增阅读数量
    @ApiOperation(value = "addView", notes = "阅读", httpMethod = "POST")
    @PostMapping("/addView")
    public void addViewCount(Long ebookId){

        System.out.println("收到的id:"+ebookId);
        ebooksnapshotService.addview(ebookId);

    }

    //查询点赞数量
    @ApiOperation(value = "getLike", notes = "查询点赞", httpMethod = "GET")
    @GetMapping("/getLike")
    public int getLike(Long ebookId){
//        如果数据库中没有该书的点赞记录，则创建一条记录
        if(ebooksnapshotService.exist(ebookId)==0){
            System.out.println("没有点赞记录，创建一条记录");
            Ebooksnapshot ebooksnapshot =new Ebooksnapshot();
            ebooksnapshot.setEbookId(ebookId);
            ebooksnapshot.setViewCount(0);
            ebooksnapshot.setVoteCount(1);
            //将id设置为记录数+1
//            long newid = (ebooksnapshotService.getMaxId()+1);
            ebooksnapshot.setDate(LocalDateTime.now());
//            ebooksnapshot.setId(newid);
            ebooksnapshotService.save(ebooksnapshot);
            return 0;
        }

        System.out.println("收到的id:"+ebookId);
        return ebooksnapshotService.likecountById(ebookId);

    }

    //查询阅读数量
    @ApiOperation(value = "getView", notes = "查询阅读", httpMethod = "GET")
    @GetMapping("/getView")
    public int getView(Long ebookId){
        if(ebooksnapshotService.exist(ebookId)==0){
            return 1;
        }
        System.out.println("收到的id:"+ebookId);
        return ebooksnapshotService.viewcountById(ebookId);

    }


}

