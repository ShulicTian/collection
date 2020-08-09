package com.data.collection.common.entity;

import com.data.collection.sys.entity.UserEntity;
import com.data.collection.sys.utils.UserUtils;

import java.io.Serializable;

/**
 * 授权用户信息
 *
 * @author ShulicTian
 */
public class Principal implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id; // 编号
    private String loginName; // 登录名
    private String name; // 姓名


//		private Map<String, Object> cacheMap;

    public Principal(UserEntity user) {
        this.id = user.getId();
        this.loginName = user.getUserName();
        this.name = user.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }


    /**
     * 获取SESSIONID
     */
    public String getSessionid() {
        try {
            return (String) UserUtils.getSession().getId();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }


}
