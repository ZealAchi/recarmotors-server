package com.recarmotors.exception;

import javax.servlet.http.HttpServletRequest;

import com.recarmotors.util.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public Result defaultExceptionHandler(HttpServletRequest req, Exception e) {

		return Result.createByErrorMessage("¡Lo siento, no tienes derechos de acceso!");
	}
}