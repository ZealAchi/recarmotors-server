package com.recarmotors.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.recarmotors.mapper.RoleMapper;
import com.recarmotors.pojo.Role;
import com.recarmotors.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements  RoleService{
    @Autowired
    private  RoleMapper RoleMapper;
    @Override
    public Map getList() {
         //Preguntar
    	List <RoleVo> RoleVoList=RoleMapper.getList();
    	
        for (RoleVo Rolevo:RoleVoList) {

        	// El menú obtiene el nodo raíz 

           
            List<Long> checkIdsList = new ArrayList<>();
           
            Rolevo.setCheckedIds(checkIdsList);
        }
        Map map = new HashMap();
        map.put("list",RoleVoList);
        return map;
    }

   
}
