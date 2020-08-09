package com.data.collection.sys.utils;

import com.data.collection.common.entity.Principal;
import com.data.collection.common.utils.CacheUtils;
import com.data.collection.common.utils.SpringContextHolder;
import com.data.collection.sys.entity.MenuEntity;
import com.data.collection.sys.entity.RoleEntity;
import com.data.collection.sys.entity.UserEntity;
import com.data.collection.sys.service.MenuService;
import com.data.collection.sys.service.UserService;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 用户相关工具类
 *
 * @author ShulicTian
 * @date 2020/01/13
 */
public class UserUtils {

    public static final String USER_CACHE = "userCache";
    public static final String CACHE_MENU_LIST = "menuList";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "loginName_";
    private static Logger logger = LoggerFactory.getLogger(UserUtils.class);
    private static UserService userService = SpringContextHolder.getBean(UserService.class);
    private static MenuService menuService = SpringContextHolder.getBean(MenuService.class);

    /**
     * 获取当前用户授权菜单
     *
     * @return
     */
    public static List<MenuEntity> getMenuList() {
        @SuppressWarnings("unchecked")
        List<MenuEntity> menuList = (List<MenuEntity>) getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            UserEntity user = getCurrentUser();
            if (user.isAdmin()) {
                menuList = menuService.findAllList();
            } else {
                List<MenuEntity> finalMenuList = Lists.newArrayList();
                List<RoleEntity> roleList = getCurrentUser().getRoleList();
                roleList.forEach(role -> finalMenuList.addAll(role.getMenuList()));
                menuList = finalMenuList;
            }
            putCache(CACHE_MENU_LIST, menuList);
        }
        logger.debug("【UserUtils】获取菜单列表");
        return menuList;
    }

    /**
     * 根据ID获取用户
     *
     * @param id
     * @return 取不到返回null
     */
    public static UserEntity get(Integer id) {
        UserEntity user = (UserEntity) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
        if (user == null) {
            user = userService.getById(id);
            if (user == null) {
                return null;
            }
//            user.setRoleList(roleDao.findList(new RoleEntity(user)));
            CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
//            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    public static UserEntity getByUserName(String loginName) {
        UserEntity user = (UserEntity) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
        if (user == null) {
            user = userService.getUserByUserName(loginName);
            if (user == null) {
                return null;
            }
            CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName, user);
        }
        return user;
    }

    public static UserEntity getCurrentUser() {
        Principal principal = getPrincipal();
        if (principal != null) {
            UserEntity user = get(principal.getId());
            if (user != null) {
                return user;
            }
            return new UserEntity();
        }
        return new UserEntity();
    }

    public static Integer getCurrentUserId() {
        UserEntity user = getCurrentUser();
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal;
            }
        } catch (UnavailableSecurityManagerException e) {
            e.printStackTrace();
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    // ============== UserEntity Cache ==============

    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
        Object obj = getSession().getAttribute(key);
        return obj == null ? defaultValue : obj;
    }

    public static void putCache(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key) {
        getSession().removeAttribute(key);
    }
}
