package com.data.collection.task.entity;

import com.data.collection.common.entity.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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
    private boolean haveTimes;

    @Column(name = "task_type")
    private boolean taskType;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getComminityId() {
        return comminityId;
    }

    public void setComminityId(String comminityId) {
        this.comminityId = comminityId;
    }

    public String getComminityName() {
        return comminityName;
    }

    public void setComminityName(String comminityName) {
        this.comminityName = comminityName;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getNowDoctorName() {
        return nowDoctorName;
    }

    public void setNowDoctorName(String nowDoctorName) {
        this.nowDoctorName = nowDoctorName;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isHaveTimes() {
        return haveTimes;
    }

    public void setHaveTimes(boolean haveTimes) {
        this.haveTimes = haveTimes;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(String scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public boolean isTaskType() {
        return taskType;
    }

    public void setTaskType(boolean taskType) {
        this.taskType = taskType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
