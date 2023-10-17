package com.example.dmsserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dmsserver.commons.bo.EbookBO;
import com.example.dmsserver.commons.util.CopyUtil;
import com.example.dmsserver.commons.vo.EbookVO;
import com.example.dmsserver.commons.vo.PageVO;
import com.example.dmsserver.entity.Ebook;
import com.example.dmsserver.mapper.EbookMapper;
import com.example.dmsserver.service.EbookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements EbookService {
    @Autowired
    private EbookMapper ebookMapper;

    @Override
    public PageVO<EbookVO> listByParam(EbookBO ebookBO) {
        QueryWrapper<Ebook> ebookQueryWrapper = null;
        if(! StringUtils.isEmpty(ebookBO.getName())){
            ebookQueryWrapper = new QueryWrapper<>();
            ebookQueryWrapper.like("name",ebookBO.getName());
        }
        if(ebookBO.getCategory2Id()!=null && ebookBO.getCategory2Id()>0){
            ebookQueryWrapper = new QueryWrapper<>();
            ebookQueryWrapper.eq("category2Id",ebookBO.getCategory2Id());
        }

        Page<Ebook> pageParam = new Page<>(ebookBO.getCurrent(),ebookBO.getSize());
        Page<Ebook> ebookPage = ebookMapper.selectPage(pageParam, ebookQueryWrapper);

//        PageVO<EbookVO> ebookVoPage = CopyUtil.copy(ebookPage, PageVO.class);
//        List<EbookVO> ebookVOList = CopyUtil.copyList(ebookPage.getRecords(), EbookVO.class);
//        ebookVoPage.setRecords(ebookVOList);
//        for(int i =0;i<ebookPage.getRecords().size();i++){
//            ebookVoPage.getRecords().get(i).setId(ebookPage.getRecords().get(i).getId().toString());
//        }

        PageVO<EbookVO> ebookVoPage = CopyUtil.copyPage(ebookPage, EbookVO.class);

        return ebookVoPage;
    }
}
