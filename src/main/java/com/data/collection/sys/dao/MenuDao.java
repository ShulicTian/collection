package com.data.collection.sys.dao;

import com.data.collection.sys.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 系统菜单持久层
 *
 * @author ShulicTian
 * @date 2020/01/16
 */
@Repository
public interface MenuDao extends JpaRepository<MenuEntity, String> {

}
