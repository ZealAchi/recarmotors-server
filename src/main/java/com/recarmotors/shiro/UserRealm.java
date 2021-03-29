package com.recarmotors.shiro;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.recarmotors.pojo.User;
import com.recarmotors.service.LoginService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import  com.recarmotors.service.UserService;

import java.util.*;
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService UserService;

	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//shiro puede almacenar el nombre de usuario, la contraseña, el rol y el menú en la colección PrincipalCollection para su uso posterior.
		User  User = (User) principalCollection.getPrimaryPrincipal();//Iniciar sesión como usuario
		//Obtener información de usuario de la sesión
		Session session = SecurityUtils.getSubject().getSession();
		//Utilice el rol r_id para consultar el menú que se muestra a la izquierda
		// 1. Obtenga el r_id del usuario que ha iniciado sesión actualmente
		Long rId = User.getRoleId();

		// 2. Vaya a la base de datos para consultar a través de r_id, la autoridad del usuario y el menú de visualización 
		//session.setAttribute("menuList",mList);
		//Agregue los permisos adquiridos a la Lista para guardar
		List<String> authorizationList = new ArrayList<String>();

		

		//Obtenga una lista de permisos de usuario
		Set<String> authorizationSet = new HashSet<String>();

		//session.setAttribute("permissionList",authorizationList);
		for (String author : authorizationList) {
			if(StringUtils.isNotBlank(author)) {

				authorizationSet.addAll(Arrays.asList(author.trim().split(",")));
			}
		}
		/*SecurityUtils.getSubject().getSession().setAttribute("menuList", mList);
		SecurityUtils.getSubject().getSession().setAttribute("permissionList", authorizationList);*/
		//Guárdalo en el objeto de Shiro.
		SimpleAuthorizationInfo   sa = new SimpleAuthorizationInfo();
		sa.setStringPermissions(authorizationSet);
		return sa;
	}

	/**
	 * Verificar el asunto actualmente conectado
	 * LoginController.login()Este método se ejecuta cuando Subject.login () se ejecuta en el método
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {


		// Consultar información de usuario
		String loginName = (String) authcToken.getPrincipal();
		// contraseña de usuario
		String loginPassWord = new String((char[]) authcToken.getCredentials());

		// Consultar otra información del usuario, consultar si el usuario existe
		User User = new User();
		User.setUserName(loginName);
		User.setPassWord(loginPassWord);
		//Admin.setAdminPassword(loginPassWord);

		// Para facilitar el uso directo del método de consulta mybatisplus, si está utilizando mybatis, el método aquí debe ser definido y desarrollado por usted mismo.
		QueryWrapper qw = new QueryWrapper();
		qw.setEntity(User);
		User user = UserService.getOne(qw);
		if (user == null) {
			throw new UnknownAccountException("账号不存在！");

		}
		//Verifique la contraseña, verifique después de usar la conversión MD5 
		/*loginPassWord = new Md5Hash(loginPassWord).toString();

		if(!loginPassWord.equals(user.getPassword())) {
			throw new UnknownAccountException("¡contraseña incorrecta!");
		} *///Autenticación
		// En casos especiales, algunos usuarios son los usuarios iniciales y no se les permite iniciar sesión sin permiso. 
		if (user.getRoleId() == null || user.getRoleId() == 0 || "".equals(user.getRoleId())) {
			throw new UnknownAccountException("La cuenta no está asignada a una autoridad de función específica, ¡póngase en contacto con el administrador!");
		}
		SimpleAuthenticationInfo sac = new SimpleAuthenticationInfo(user, loginPassWord, getName());
		return sac;
	}
}