package com.recarmotors.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.recarmotors.pojo.Role;

public class User implements Serializable {
	@TableField(exist = false)
	private Role role;
	@TableField(exist = false)
	private String roleName;
	@TableId(value = "id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	private String userName;

	private String passWord;

	private String realName;
	@JsonSerialize(using = ToStringSerializer.class)
	private Long roleId;

	private Date createTime;

	private Date updateTime;

	private String isDelete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord == null ? null : passWord.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}