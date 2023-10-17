package com.example.dmsserver.service.impl;

import com.example.dmsserver.commons.bo.VoteBO;
import com.example.dmsserver.entity.Ebooksnapshot;
import com.example.dmsserver.mapper.EbooksnapshotMapper;
import com.example.dmsserver.service.EbooksnapshotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@Service
public class EbooksnapshotServiceImpl extends ServiceImpl<EbooksnapshotMapper, Ebooksnapshot> implements EbooksnapshotService {
    @Autowired
    EbooksnapshotMapper ebooksnapshotMapper;
    @Override
    public void addlike(Long id) {
        ebooksnapshotMapper.addlike(id);
    }

    @Override
    public void addview(Long ebookId) {
        ebooksnapshotMapper.addview(ebookId);
    }

    @Override
    public int likecountById(Long ebookId) {
        return ebooksnapshotMapper.likecountById(ebookId);
    }

    @Override
    public int viewcountById(Long ebookId) {
        return ebooksnapshotMapper.viewcountById(ebookId);
    }

    @Override
    public Long getMaxId() {
        return ebooksnapshotMapper.getMaxId();
    }

    @Override
    public int exist(Long ebookId) {
        return ebooksnapshotMapper.exist(ebookId);
    }
}
