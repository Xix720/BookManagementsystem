package com.example.dmsserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dmsserver.commons.bo.VoteBO;
import com.example.dmsserver.entity.Ebooksnapshot;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
public interface EbooksnapshotMapper extends BaseMapper<Ebooksnapshot> {

    void addlike(Long id);

    void addview(Long ebookId);

    int likecountById(Long ebookId);

    int viewcountById(Long ebookId);

    Long getMaxId();

    Integer exist(Long ebookId);
}
