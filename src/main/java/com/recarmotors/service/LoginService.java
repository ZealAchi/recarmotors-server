package com.recarmotors.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.recarmotors.util.Result;

public interface LoginService{

    /**
     *Autenticación de inicio de sesión
     */
    Result login(String userName,String passWord);

    /**
     *Obtener información de inicio de sesión actual
     */
    Result getInfo();

    /**
     *desconectar
     *
     */
    Result loginOut();
}
