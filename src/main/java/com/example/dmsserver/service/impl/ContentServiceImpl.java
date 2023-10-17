package com.example.dmsserver.service.impl;

import com.example.dmsserver.entity.Content;
import com.example.dmsserver.mapper.ContentMapper;
import com.example.dmsserver.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

}
