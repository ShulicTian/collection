package com.data.collection.sys.web;

import com.data.collection.common.entity.ResponseEntity;
import com.data.collection.common.utils.StringUtils;
import com.data.collection.sys.entity.UserEntity;
import com.data.collection.sys.service.UserService;
import com.data.collection.sys.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 系统基础信息Controller
 *
 * @author ShulicTian
 * @date 2020/01/10
 */
@RestController
@RequestMapping("/collection/sys")
public class SystemController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;


    /**
     * 登录
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResponseEntity login(HttpServletResponse response) {
        ResponseEntity responseEntity = new ResponseEntity();
        response.setStatus(401);
        return responseEntity;
    }

    /**
     * 登录
     *
     * @param userEntity
     * @param rememberMe
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity userEntity, boolean rememberMe) {
        ResponseEntity<UserEntity> result = new ResponseEntity();

        if (StringUtils.isEmpty(userEntity.getUserName()) || StringUtils.isEmpty(userEntity.getPassWord())) {
            result.setMsg("账号密码不能为空");
        }

        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUserName(), userEntity.getPassWord().toCharArray(), rememberMe);
            try {
                currentUser.login(token);
                UserEntity user = UserUtils.getByUserName(userEntity.getUserName());
                user.setToken(String.valueOf(currentUser.getSession().getId()));
                result.setMsg("登录成功");
                result.setResult(user);
                result.setCode(ResponseEntity.OK);
            } catch (UnknownAccountException ex) {
                ex.printStackTrace();
                logger.debug("账号错误");
                result.setMsg("账号错误");
            } catch (IncorrectCredentialsException ex) {
                ex.printStackTrace();
                logger.debug("密码错误");
                result.setMsg("密码错误");
            } catch (LockedAccountException ex) {
                ex.printStackTrace();
                logger.debug("账号已被锁定，请与系统管理员联系");
                result.setMsg("账号已被锁定，请与系统管理员联系");
            } catch (AuthenticationException ex) {
                ex.printStackTrace();
                logger.debug("您没有授权!");
                result.setMsg("您没有授权");
            }
        } else {
            result.setMsg("请勿重复登入");
            result.setResult(UserUtils.getByUserName(userEntity.getUserName()));
            result.setCode(ResponseEntity.OK);
        }

        return result;
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "logout")
    public ResponseEntity<Boolean> logout() {

        ResponseEntity<Boolean> result = new ResponseEntity();
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            currentUser.logout();
            result.setMsg("登出成功");
            result.setCode(ResponseEntity.OK);
            result.setResult(true);
        } else {
            result.setMsg("您还未登录");
            result.setCode(ResponseEntity.OK);
            result.setResult(false);
        }

        return result;
    }

    /**
     * 注册用户、更改用户信息
     *
     * @return
     */
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {

        ResponseEntity<UserEntity> result = new ResponseEntity();
        try {
            user = userService.save(user);
            result.setCode(ResponseEntity.OK);
            result.setResult(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("用户：" + user.getUserName() + user.getName() + "保存异常");
        }
        return result;
    }

    /**
     * @return
     */
    @RequestMapping(value = "unauthorized")
    public ResponseEntity unauthorized(HttpServletResponse response) {
        ResponseEntity responseEntity = new ResponseEntity();
        response.setStatus(-401);
        return responseEntity;
    }

}
