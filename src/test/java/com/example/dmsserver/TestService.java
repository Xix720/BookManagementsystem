package com.example.dmsserver;

import com.example.dmsserver.commons.bo.DocQueryBO;
import com.example.dmsserver.commons.bo.EbookBO;
import com.example.dmsserver.commons.vo.DocVO;
import com.example.dmsserver.commons.vo.EbookVO;
import com.example.dmsserver.commons.vo.PageVO;
import com.example.dmsserver.service.DocService;
import com.example.dmsserver.service.EbookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestService {
    @Autowired
    private EbookService ebookService;
    @Autowired
    private DocService docService;

    @Test
    public void testEbookService(){
        EbookBO ebookBO = new EbookBO();
        ebookBO.setName(null);
        PageVO<EbookVO> ebookIPage = ebookService.listByParam(ebookBO );
        System.out.println(ebookIPage);
    }

    @Test
    public void testDocService(){
        DocQueryBO docBO = new DocQueryBO();
        docBO.setEbookId("192796389208625152");
        List<DocVO> docVOS = docService.listByParam(docBO);
        docVOS.forEach(System.out::println);
    }
}
