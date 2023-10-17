package com.example.dmsserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dmsserver.commons.bo.DocQueryBO;
import com.example.dmsserver.commons.bo.DocSaveBO;
import com.example.dmsserver.commons.util.CopyUtil;
import com.example.dmsserver.commons.vo.DocVO;
import com.example.dmsserver.entity.Content;
import com.example.dmsserver.entity.Doc;
import com.example.dmsserver.mapper.DocMapper;
import com.example.dmsserver.service.ContentService;
import com.example.dmsserver.service.DocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {

    @Autowired
    private DocMapper docMapper;
    @Autowired
    private ContentService contentService;

    @Override
    public List<DocVO> listByParam(DocQueryBO docBO) {
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (docBO != null) {
            if (docBO.getEbookId() != null && docBO.getEbookId().trim() != "") {
                queryWrapper.eq("ebookId", docBO.getEbookId().trim());
            }
        }

        List<Doc> docList = docMapper.selectList(queryWrapper);

        List<DocVO> docVOList = CopyUtil.copyList(docList, DocVO.class, "id", "parent", "ebookId");

        return docVOList;
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(DocSaveBO docSaveBO) {
        Doc doc = CopyUtil.copy(docSaveBO, Doc.class);
        boolean hasDocSave = this.saveOrUpdate(doc);

        Content content = CopyUtil.copy(docSaveBO, Content.class);
        content.setId(doc.getId());
        boolean hasContentSave = contentService.saveOrUpdate(content);
        return hasDocSave && hasContentSave;
    }
}
