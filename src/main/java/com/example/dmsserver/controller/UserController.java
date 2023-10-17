package com.example.dmsserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.dmsserver.commons.bo.LoginUserBO;
import com.example.dmsserver.commons.bo.ResetPwdBO;
import com.example.dmsserver.commons.bo.UserBO;
import com.example.dmsserver.commons.result.JSONResult;
import com.example.dmsserver.commons.result.ResponseStatusEnum;
import com.example.dmsserver.commons.util.JwtUtils;
import com.example.dmsserver.commons.vo.LoginUserVO;
import com.example.dmsserver.commons.vo.PageVO;
import com.example.dmsserver.commons.vo.UserVO;
import com.example.dmsserver.entity.User;
import com.example.dmsserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-06-04
 */
@Api(value = "UserController", tags = {"UserController"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult<LoginUserVO> login(@Valid @RequestBody LoginUserBO loginUserBO, HttpServletResponse response){
        loginUserBO.setPassword(DigestUtils.md5DigestAsHex(loginUserBO.getPassword().getBytes()));
        LoginUserVO loginUserVO = userService.login(loginUserBO);

        String token = jwtUtils.generateToken(loginUserVO.getId());
        response.setHeader("Authorization", token);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        redisTemplate.opsForValue().set(loginUserVO.getId(),loginUserVO,3600*24, TimeUnit.SECONDS);

        return new JSONResult(ResponseStatusEnum.LOGIN_SUCCESS,loginUserVO);
    }

    @ApiOperation(value = "用户列表", notes = "用户列表", httpMethod = "GET")
    @GetMapping("/list")
    public JSONResult<PageVO<UserVO>> list(UserBO userBO){

        PageVO<UserVO> userPageVO = userService.listByParam(userBO);

        return new JSONResult(ResponseStatusEnum.SUCCESS,userPageVO);
    }

    @ApiOperation(value = "用户信息保存", notes = "用户信息保存", httpMethod = "POST")
    @PostMapping("/save")
    public JSONResult save(@RequestBody User user){
        System.out.println("收到的用户信息:"+user);
        if(user.getId()==null){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("loginName",user.getLoginName());
            int count = userService.count(queryWrapper);
            if(count>0){
                return new JSONResult(ResponseStatusEnum.LOGINNAME_IS_EXIST_ERROR);
            }
        }else{
            User user2 = userService.getById(user.getId());
            if(!user2.getLoginName().equals(user.getLoginName())){
                return JSONResult.errorMsg("登录名不允许修改");
            }
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        boolean hasSave = userService.saveOrUpdate(user);

        if(hasSave){
            return JSONResult.ok();
        }
        else{
            return JSONResult.error();
        }
    }

    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public JSONResult delete(Long id){

        boolean hasDelete = userService.removeById(id);

        if(hasDelete){
            return JSONResult.ok();
        }
        else{
            return JSONResult.error();
        }
    }

    @ApiOperation(value = "重置密码", notes = "重置密码", httpMethod = "PUT")
    @PutMapping("/resetPwd")
    public JSONResult resetPwd(@RequestBody ResetPwdBO resetPwdBO){
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("password",resetPwdBO.getPassword());
        updateWrapper.eq("id",resetPwdBO.getId());

        boolean hasUpdate = userService.update(updateWrapper);
        if(hasUpdate){
            return JSONResult.ok();
        }
        else{
            return JSONResult.errorMsg("密码更新失败");
        }
    }

    @ApiOperation(value = "退出登录", notes = "退出登录", httpMethod = "GET")
    @GetMapping("/logout/{id}")
    public JSONResult logout(@PathVariable String id){

        boolean hasDelete =redisTemplate.delete(id);

        if(hasDelete){
            return JSONResult.ok();
        }
        else{
            return JSONResult.error();
        }
    }
}



