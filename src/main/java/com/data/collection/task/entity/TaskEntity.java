package com.data.collection.task.entity;

import com.data.collection.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "user_task")
public class TaskEntity extends BaseEntity {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "hospital_id")
    private String hospitalId;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "comminity_id")
    private String comminityId;

    @Column(name = "comminity_name")
    private String comminityName;

    @Column(name = "dept_id")
    private String depId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "doctor_name")
    private String nowDoctorName;

    @Column(name = "work_date")
    private Date workDate;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "time_unit")
    private String timeUnit;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "fee_id")
    private String feeId;

    @Column(name = "schedule_no")
    private String scheduleNo;

    @Column(name = "is_have_times")
    private Boolean haveTimes;

    @Column(name = "task_type",columnDefinition = "int(4) default null")
    private String taskType;

}
