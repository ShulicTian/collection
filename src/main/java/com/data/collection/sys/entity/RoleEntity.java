package com.data.collection.sys.entity;

import com.data.collection.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sys_role")
public class RoleEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "en_name")
    private String enname;

    @Column(name = "role_type")
    private String roleType;

    @Column(name = "useable")
    private String useable;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role_menu",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<MenuEntity> menuList;
}
