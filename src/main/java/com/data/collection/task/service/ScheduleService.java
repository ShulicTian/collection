package com.data.collection.task.service;

import com.data.collection.sys.utils.UserUtils;
import com.data.collection.task.dao.ScheduleDao;
import com.data.collection.task.dao.TaskDao;
import com.data.collection.task.entity.ScheduleEntity;
import com.data.collection.task.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    public List<ScheduleEntity> getScheduleList() {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setUserId(UserUtils.getCurrentUserId());
        Example<ScheduleEntity> example = Example.of(scheduleEntity);
        List<ScheduleEntity> list = scheduleDao.findAll(example);
        return list;
    }

    @Modifying
    @Transactional(readOnly = false)
    public void saveSchedule(ScheduleEntity scheduleEntity) {
        scheduleEntity.setUserId(UserUtils.getCurrentUserId());
        scheduleDao.saveAndFlush(scheduleEntity);
    }

    @Modifying
    @Transactional(readOnly = false)
    public void removeSchedule(Integer id) {
        scheduleDao.deleteById(id);
    }
}
