package com.example.dmsserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dmsserver.commons.bo.VoteBO;
import com.example.dmsserver.entity.Ebooksnapshot;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
public interface EbooksnapshotService extends IService<Ebooksnapshot> {

    void addlike(Long id);

    void addview(Long ebookId);

    int likecountById(Long ebookId);

    int viewcountById(Long ebookId);

    Long getMaxId();

    int exist(Long ebookId);
}
