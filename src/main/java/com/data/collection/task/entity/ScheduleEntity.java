package com.data.collection.task.entity;

import com.data.collection.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_schedule")
public class ScheduleEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "token", columnDefinition = "varchar(1000) default ''")
    private String token;

    @Column(name = "user_id")
    private Integer userId;
}
