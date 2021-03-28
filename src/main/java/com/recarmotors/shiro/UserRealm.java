package com.recarmotors.shiro;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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

import java.util.*;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
		}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}
}
