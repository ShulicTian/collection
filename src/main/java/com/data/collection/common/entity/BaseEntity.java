package com.data.collection.common.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @CreatedBy
    @Column(name = "create_by", updatable = false, nullable = false)
    protected String createBy;

    @CreatedDate
    @Column(name = "create_date", updatable = false, nullable = false)
    protected Date createDate;

    @LastModifiedBy
    @Column(name = "update_by", nullable = false)
    protected String updateBy;

    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    protected Date updateDate;

    @Column(name = "flag", columnDefinition = "char(2) not null default 0")
    protected String flag = "0";

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

}
