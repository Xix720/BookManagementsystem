package com.example.dmsserver.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dmsserver.commons.bo.EbookBO;
import com.example.dmsserver.commons.vo.EbookVO;
import com.example.dmsserver.commons.vo.PageVO;
import com.example.dmsserver.entity.Ebook;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
public interface EbookService extends IService<Ebook> {
    public PageVO<EbookVO> listByParam(EbookBO ebookBO);
}
