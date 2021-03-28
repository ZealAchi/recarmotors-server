package com.recarmotors.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recarmotors.pojo.Role;
import com.recarmotors.vo.RoleVo;

public interface RoleMapper  extends BaseMapper<Role> {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

/*    @Results({
            @Result(property="User",column="roleId",many=@Many(select="com.recarmotors.mapper.UserMapper.getUserByRoleId"))
    })*/
    @Select("SELECT *,id roleId from role")
    List<RoleVo> getList();
}