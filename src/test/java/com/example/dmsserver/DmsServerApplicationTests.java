package com.example.dmsserver;

import com.example.dmsserver.commons.util.CopyUtil;
import com.example.dmsserver.commons.vo.LoginUserVO;
import com.example.dmsserver.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DmsServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testBeanUtils(){
        User user1 = new User(1L,"aaa","sfsfdsf","1322344");
        User user2 = new User(2L,"ccc","dfsdf","1342324");
        User user3 = new User(3L,"aaxxxa","csdfsdfcc","2341324");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        LoginUserVO userVO = new LoginUserVO();
        List<LoginUserVO> userVOList = new ArrayList<>();

        BeanUtils.copyProperties(user1,userVO);
        System.out.println(userVO);

        BeanUtils.copyProperties(userList,userVOList);

        System.out.println(userVOList);

        userVOList.forEach(System.out::println);
    }

    @Test
    public void testCopyUtil(){
        User user1 = new User(1L,"aaa","sfsfdsf","1322344");
        User user2 = new User(2L,"ccc","dfsdf","1342324");
        User user3 = new User(3L,"aaxxxa","csdfsdfcc","2341324");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        LoginUserVO userVO = CopyUtil.copy(user1, LoginUserVO.class);
//        System.out.println(userVO);

        List<LoginUserVO> userVOList = CopyUtil.copyList(userList, LoginUserVO.class);

        System.out.println(userVOList);

        userVOList.forEach(System.out::println);
    }
}
