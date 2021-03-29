package com.recarmotors.service;

import com.alibaba.fastjson.JSONObject;
import com.recarmotors.code.CodeEnum;
import com.recarmotors.pojo.User;
import com.recarmotors.util.Result;
import com.recarmotors.vo.UserVo;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación de inicio de sesión
 */
@Service
public class LoginServiceImpl implements LoginService {

   

    @Override
    public Result login(String userName, String passWord) {
        int code = 0;
        JSONObject loginInfo = new JSONObject();
        //1. Obtenga el asunto y almacene la información del usuario 
        Subject currenUser = SecurityUtils.getSubject();
        //2.Token inicio de sesión
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        try {
            currenUser.login(token);
            code=200;
            loginInfo.put("result","success");
        } catch(AuthenticationException e){
            loginInfo.put("result","fail");
        }
          //Puede obtener sesión
        Session session = SecurityUtils.getSubject().getSession();
        return Result.createByCodeSuccess(code,"¡Finalizado!",loginInfo);
    }

    @Override
    public Result getInfo() {
        //1. Obtenga el usuario que ha iniciado sesión actualmente
        Session session = SecurityUtils.getSubject().getSession();
          Subject currentUser = SecurityUtils.getSubject();
        User User  = (User) currentUser.getPrincipal();
        //Para asegurarse de iniciar sesión al mismo tiempo, debe obtener los permisos del usuario que ha iniciado sesión actualmente. 
        UserVo UserVo= new UserVo();
        if(User!=null&&!User.equals("")){

           UserVo.setId(User.getId());
           UserVo.setUpdateTime(User.getUpdateTime());
           UserVo.setIsDelete(User.getIsDelete());
           UserVo.setRealName(User.getRealName());
           UserVo.setCreateTime(User.getCreateTime());
           UserVo.setPassWord(User.getPassWord());
           UserVo.setRoleId(User.getRoleId());
                      
           UserVo.setUserName(User.getUserName());
            return Result.createBySuccess("Obtener información con éxito！",UserVo);

        }

           return Result.createByErrorCodeMessage(CodeEnum.ERROR_10001.getErrorCode(),"No se pudo obtener información");

    }

    /**
     * Salir del servicio de interfaz
     * @return
     */
    @Override
    public Result loginOut() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        }catch (Exception e) {
            return  Result.createByErrorMessage("¡La salida falló!");
        }

        return Result.createBySuccess("salir con éxito!");
    }
}
