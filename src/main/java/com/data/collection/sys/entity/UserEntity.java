package com.data.collection.sys.entity;

import com.data.collection.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "sys_user")
public class UserEntity extends BaseEntity {

    @Column(name = "nike_name")
    private String nikeName;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String passWord;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile", nullable = false, unique = true)
    private String mobile;

    @Column(name = "id_card", nullable = false, unique = true)
    private String idCard;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "login_date")
    private Date loginDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roleList;

    @Transient
    private String token;

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Integer id) {
        return id != null && id == 1;
    }

}
