package com.recarmotors.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recarmotors.code.CodeEnum;

@Controller
public class PageErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	/*
	 * error 404
	 */
	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public CodeEnum handleError() {
		return CodeEnum.ERROR__404;
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}

