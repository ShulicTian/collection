package com.data.collection.sys.service;

import com.data.collection.sys.dao.MenuDao;
import com.data.collection.sys.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统菜单相关操作
 *
 * @author ShulicTian
 * @date 2020/01/16
 */
@Service
public class MenuService{

    @Autowired
    private MenuDao menuDao;

    public List<MenuEntity> findAllList() {
        return menuDao.findAll();
    }
}
