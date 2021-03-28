package com.recarmotors.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.recarmotors.pojo.Role;
import com.recarmotors.pojo.User;

import java.util.List;
import java.util.Set;

public class UserVo extends User {
    private Set<String> menuList;
    private Set<String> permissionList;
    @TableField(exist = false)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public void setMenuList(Set<String> menuList) {
        this.menuList = menuList;
    }

    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }

    public Set<String> getMenuList() {
        return menuList;
    }

    public Set<String> getPermissionList() {
        return permissionList;
    }
}
