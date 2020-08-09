package com.data.collection.sys.dao;

import com.data.collection.sys.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 日志DAO接口
 * @author ShulicTian
 * @date 2020/01/16
 */
public interface LogDao extends JpaRepository<Log, String> {

}
