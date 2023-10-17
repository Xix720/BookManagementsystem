package com.example.dmsserver;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import com.example.dmsserver.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
class Book{
    private Integer id;
    private String name;
//    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime publishDate;
}

@SpringBootTest
public class TestFastJson {

    @Test
    public void test1(){
        User user = new User();
        user.setId(null);
        user.setLoginName("aaa");
        user.setPassword(null);
        user.setName("小王");

        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);

        String jsonString2 = JSON.toJSONString(user, JSONWriter.Feature.WriteMapNullValue);
        System.out.println(jsonString2);

        String jsonString3 = JSON.toJSONString(user, JSONWriter.Feature.NullAsDefaultValue);
        System.out.println(jsonString3);

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
//        filter.getExcludes().add("password");
        filter.getIncludes().add("name");

        String jsonString4 = JSON.toJSONString(user,filter, JSONWriter.Feature.WriteNullNumberAsZero);
        System.out.println(jsonString4);

//        JSON.toJSONString(user,)


        Book book = new Book();
        book.setId(111);
        book.setName("abc");
        book.setPublishDate(LocalDateTime.now());

        String bookJson = JSON.toJSONString(book);
        System.out.println(bookJson);
        Book book2 = JSON.parseObject(bookJson, Book.class);
        System.out.println(book2);
    }

}
