package com.recarmotors.set;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * <p>
	 * Excepción comercial REST personalizada
	 * <p>
	 *
	 * @param e tipo de excepción
	 * @regreso
	 */
	@ExceptionHandler(value = Exception.class)
	public R<Object> handleBadRequest(Exception e) {
		/*
		 * La lógica empresarial es anormal
		 */
		if (e instanceof ApiException) {
			IErrorCode errorCode = ((ApiException) e).getErrorCode();
			if (null != errorCode) {
				logger.debug("Rest request error, {}", errorCode.toString());
				return R.failed(errorCode);
			}
			logger.debug("Rest request error, {}", e.getMessage());
			return R.failed(e.getMessage());
		}

		/*
		 * La verificación de parámetros es anormal
		 */
		if (e instanceof BindException) {
			BindingResult bindingResult = ((BindException) e).getBindingResult();
			if (null != bindingResult && bindingResult.hasErrors()) {
				List jsonList = new ArrayList<>();
				bindingResult.getFieldErrors().stream().forEach(fieldError -> {
					Map<String, Object> jsonObject = new HashMap<>(2);
					jsonObject.put("name", fieldError.getField());
					jsonObject.put("msg", fieldError.getDefaultMessage());
					jsonList.add(jsonObject);
				});
				return R.restResult(jsonList, ApiErrorCode.FAILED);
			}
		}

		/**
		 * Excepción del sistema interno, pila de excepciones de impresión
		 */
		logger.error("Error: handleBadRequest StackTrace : {}", e);
		return R.failed(ApiErrorCode.FAILED);
	}
}
