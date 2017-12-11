package com.chao.springboot.system.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "t_sys_user")
@Entity
public class SysUser implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @Column(name="user_code",columnDefinition="varchar(128) not null COMMENT '用户登录编码'",unique = true)
    private String userCode;

    @Column(name="user_name",columnDefinition="varchar(128) not null COMMENT '用户名称'")
    private String userName;

    @Column(name="salt",columnDefinition="varchar(128) COMMENT '加密密码的盐'")
    private String salt;

    @Column(name="password",columnDefinition="varchar(128) not null COMMENT '用户登录的密码'")
    private String password;

    @Column(name="address",columnDefinition="varchar(255) COMMENT '用户地址'")
    private String address;

    @Column(name="memo",columnDefinition="varchar(255) COMMENT '备注'")
    private String memo;

    @Column(name="age",columnDefinition="int(2) default 0 COMMENT '年龄'")
    private int age;

    @Column(name="sex",columnDefinition="int(2) default 0 COMMENT '0=男，1=女'")
    private int sex;

    @Column(name="status",columnDefinition="int(2) default 0 COMMENT '0=使用，1=禁用，2=逻辑删除'")
    private int status;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name="create_time",columnDefinition="TIMESTAMP COMMENT '创建时间'")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name="update_time",columnDefinition="TIMESTAMP COMMENT '更新时间'")
    private Date updateTime;

    @Column(name="create_by",columnDefinition="varchar(60) COMMENT '创建人'")
    private String createBy;

    @Column(name="update_by",columnDefinition="varchar(60) COMMENT '更新人'")
    private String updateBy;

    @Column(name = "email", nullable = true, columnDefinition="varchar(128) COMMENT '邮箱'")
    private String email;

    @Column(name = "mobile", nullable = true, columnDefinition="varchar(20) COMMENT '电话'")
    private String mobile;

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "T_SysUserRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.userCode+this.salt;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", memo='" + memo + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
