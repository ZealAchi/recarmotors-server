package com.recarmotors.shiro;

import com.alibaba.fastjson.JSONObject;
import com.recarmotors.code.CodeEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @description: 强制登录
 */
public class ApiAuthorizationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", CodeEnum.ERROR__000.getErrorCode());
		jsonObject.put("msg", CodeEnum.ERROR__000.getErrorMsg());
		PrintWriter out = null;
		HttpServletResponse res = (HttpServletResponse) response;
		try {
			res.setCharacterEncoding("UTF-8");
			res.setContentType("application/json");
			out = response.getWriter();
			out.println(jsonObject);
		} catch (Exception e) {
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return false;
	}

	@Bean
	public FilterRegistrationBean registration(ApiAuthorizationFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}
}
