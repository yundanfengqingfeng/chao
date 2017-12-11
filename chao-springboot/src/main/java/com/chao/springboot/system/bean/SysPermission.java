package com.chao.springboot.system.bean;

import javax.persistence.*;
import java.util.List;

@Table(name = "t_sys_permission")
@Entity
public class SysPermission {

    @Id @GeneratedValue
    private Integer id;//主键.
    @Column(name="permission_name",columnDefinition="varchar(60) COMMENT '权限名称.'")
    private String permissionName;
    @Column(name ="resource_type" ,columnDefinition="varchar(60) not null COMMENT '资源类型,[menu|button]'")
    private String resourceType;//资源类型，[menu|button]
    @Column(name ="url" ,columnDefinition="varchar(256) not null COMMENT '资源路径'")
    private String url;//资源路径.
    @Column(name ="permission" ,columnDefinition="varchar(60) not null COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view'")
    private String permission;
    @Column(name ="parent_id" ,columnDefinition="varchar(30) COMMENT '父编号'")
    private Long parentId;
    @Column(name ="parent_ids" ,columnDefinition="varchar(30) not null COMMENT '父编号列表'")
    private String parentIds;
    @Column(name="available",columnDefinition="int(2) default 0 COMMENT '是否可用,如果不可用将不会添加给用户,0=可用，1=不可用'")
    private Boolean available = Boolean.FALSE;
    @ManyToMany
    @JoinTable(name="T_SysRolePermission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<SysRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
