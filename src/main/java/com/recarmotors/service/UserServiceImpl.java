package com.recarmotors.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.recarmotors.mapper.UserMapper;
import com.recarmotors.pojo.User;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements  UserService{

}

