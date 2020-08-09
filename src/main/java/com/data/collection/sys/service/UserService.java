package com.data.collection.sys.service;

import com.data.collection.common.utils.AuthUtils;
import com.data.collection.sys.dao.UserDao;
import com.data.collection.sys.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserEntity getUserByUserName(String userName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        Example<UserEntity> example = Example.of(userEntity);
        Optional<UserEntity> optional = userDao.findOne(example);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Transactional(readOnly = false)
    public UserEntity save(UserEntity user) {
        user.setCreateBy("0");
        user.setCreateDate(new Date());
        user.setUpdateBy("0");
        user.setUpdateDate(new Date());
        user.setPassWord(AuthUtils.entryptPassword(user.getPassWord()));
        user = userDao.saveAndFlush(user);
        return user;
    }

    public UserEntity getById(Integer id) {
        return userDao.getOne(id);
    }
}
