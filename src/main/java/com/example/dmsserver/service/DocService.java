package com.example.dmsserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dmsserver.commons.bo.DocQueryBO;
import com.example.dmsserver.commons.bo.DocSaveBO;
import com.example.dmsserver.commons.vo.DocVO;
import com.example.dmsserver.entity.Doc;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
public interface DocService extends IService<Doc> {
    public List<DocVO> listByParam(DocQueryBO docBO);
    public boolean saveOrUpdate(DocSaveBO docSaveBO);
}
