package com.data.collection.task.service;

import com.data.collection.sys.utils.UserUtils;
import com.data.collection.task.dao.TaskDao;
import com.data.collection.task.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public List<TaskEntity> getCommunityList(Integer userId) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUserId(userId);
        Example<TaskEntity> example = Example.of(taskEntity);
        return taskDao.findAll(example);
    }

    @Modifying
    @Transactional(readOnly = false)
    public void addTask(TaskEntity taskEntity) {
        taskEntity.setUserId(UserUtils.getCurrentUserId());
        taskDao.saveAndFlush(taskEntity);
    }

    @Modifying
    @Transactional(readOnly = false)
    public void removeTask(Integer id) {
        taskDao.deleteById(id);
    }
}
