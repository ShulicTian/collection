package com.data.collection.sys.dao;

import com.data.collection.sys.entity.RoleEntity;
import com.data.collection.sys.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Integer> {
}
