package com.recarmotors.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recarmotors.pojo.User;


public interface UserMapper extends BaseMapper<User>{
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}