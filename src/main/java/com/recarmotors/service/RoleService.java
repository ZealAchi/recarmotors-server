package com.recarmotors.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.recarmotors.pojo.Role;

import java.util.Map;

public interface RoleService extends IService<Role> {
    Map getList();
}
