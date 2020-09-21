package com.data.collection.task.dao;

import com.data.collection.task.entity.ScheduleEntity;
import com.data.collection.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDao extends JpaRepository<ScheduleEntity, Integer> {
}
