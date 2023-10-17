package com.example.dmsserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dmsserver.commons.bo.LoginUserBO;
import com.example.dmsserver.commons.bo.PageParam;
import com.example.dmsserver.commons.bo.UserBO;
import com.example.dmsserver.commons.exception.GraceException;
import com.example.dmsserver.commons.result.ResponseStatusEnum;
import com.example.dmsserver.commons.util.CopyUtil;
import com.example.dmsserver.commons.vo.LoginUserVO;
import com.example.dmsserver.commons.vo.PageVO;
import com.example.dmsserver.commons.vo.UserVO;
import com.example.dmsserver.entity.User;
import com.example.dmsserver.mapper.UserMapper;
import com.example.dmsserver.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginUserVO login(LoginUserBO loginUserBO) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("loginName",loginUserBO.getLoginName());

        User user = userMapper.selectOne(userQueryWrapper);

        if(user == null || ! user.getPassword().equals(loginUserBO.getPassword())){
            GraceException.display(ResponseStatusEnum.LOGIN_FAILED);
        }

        LoginUserVO userVO = CopyUtil.copy(user, LoginUserVO.class);

        return userVO;
    }

    @Override
    public PageVO<UserVO> listByParam(UserBO userBO) {
        IPage<User> userPage = null;
        if(userBO!=null) {
            IPage<User> pageParam = new Page<>(userBO.getCurrent(), userBO.getSize());
            QueryWrapper<User> ebookQueryWrapper = new QueryWrapper<>();
            if( ! StringUtils.isEmpty(userBO.getLoginName())) {
                ebookQueryWrapper.like("loginName", userBO.getLoginName());
            }
            userPage = userMapper.selectPage(pageParam, ebookQueryWrapper);
        }else{
            PageParam pageParam = new PageParam();
            IPage<User> ebookPageParam = new Page<>(pageParam.getCurrent(),pageParam.getSize());
            userPage = userMapper.selectPage(ebookPageParam, null);
        }

        PageVO userPageVO = CopyUtil.copyPage(userPage, UserVO.class);

        return userPageVO;
    }
}
