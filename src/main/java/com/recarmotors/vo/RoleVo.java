package com.recarmotors.vo;

import com.recarmotors.pojo.Role;
import com.recarmotors.pojo.User;

import java.util.List;

public class RoleVo extends Role {
	private List<User> User;
	private List<Long> checkedIds;

	public List<Long> getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(List<Long> checkedIds) {
		this.checkedIds = checkedIds;
	}

	public List<User> getUser() {
		return User;
	}

	public void setUser(List<User> User) {
		this.User = User;
	}
}
