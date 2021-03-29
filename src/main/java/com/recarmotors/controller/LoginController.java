package com.recarmotors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.recarmotors.service.LoginService;
import com.recarmotors.util.Result;


@RequestMapping("/api/login")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     *Método de inicio de sesión 1, paso de parámetros 
     */
    @RequestMapping("/auth1")
    public Result login(String userName,String passWord){

        Result result = loginService.login(userName, passWord);

        return  result;

    }

    /**
     * Método de inicio de sesión 2: cadena json o transferencia de objeto json 
     * @param jsonObject
     * @return
     */
    @RequestMapping("/auth")
    public Result  loginReact(@RequestBody JSONObject jsonObject){
      if(jsonObject!=null&&!jsonObject.equals("")){
          String userName = jsonObject.getString("userName");
          String passWord = jsonObject.getString("passWord");
          Result result = loginService.login(userName, passWord);
          return  result;
      }
           return  Result.createByError();
    }

    /**
     * Interfaz para obtener la información del usuario actualmente conectado 
     */
    @RequestMapping("/getInfo")
    public Result getInfo(){
        return  loginService.getInfo();
    }

    /**
     * Cerrar sesión del usuario actual
     */
    @RequestMapping("/logout")
    public Result loginOut(){
        Result result = loginService.loginOut();
        return  result;
    }
}
