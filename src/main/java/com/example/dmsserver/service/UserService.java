package com.example.dmsserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dmsserver.commons.bo.LoginUserBO;
import com.example.dmsserver.commons.bo.UserBO;
import com.example.dmsserver.commons.vo.LoginUserVO;
import com.example.dmsserver.commons.vo.PageVO;
import com.example.dmsserver.commons.vo.UserVO;
import com.example.dmsserver.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
public interface UserService extends IService<User> {
    public LoginUserVO login(LoginUserBO loginUserBO);
    public PageVO<UserVO> listByParam(UserBO userBO);
}
