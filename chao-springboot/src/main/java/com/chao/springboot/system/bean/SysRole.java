package com.chao.springboot.system.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "t_sys_role")
@Entity
public class SysRole implements Serializable {

    @Id  @GeneratedValue
    private Long id;
    @Column(name="role",columnDefinition="varchar(60) not null COMMENT '角色标识程序中判断使用,如admin,这个是唯一的'")
    private String role;
    @Column(name="description",columnDefinition="varchar(128) COMMENT '角色描述,UI界面显示使用'")
    private String description;
    @Column(name="available",columnDefinition="int(2) default 0 COMMENT '是否可用,如果不可用将不会添加给用户,0=可用，1=不可用'")
    private Boolean available = Boolean.FALSE;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="T_SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="userId")})
    private List<SysUser> sysUsers;// 一个角色对应多个用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="T_SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
