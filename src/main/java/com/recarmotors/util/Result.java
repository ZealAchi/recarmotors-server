package com.recarmotors.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.recarmotors.code.CodeEnum;
import com.recarmotors.util.ResponseCode;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Enumeration;

import static com.sun.xml.internal.ws.api.message.Packet.State.ServerResponse;

public class Result<T> implements Serializable {
	// 1. Definir atributos 
	private int status;

	private String msg;

	private T data;

	// 2. Defina el constructor 
	private Result(int status) {
		this.status = status;
	}

	private Result(int status, T data) {
		this.status = status;
		this.data = data;
	}

	private Result(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private Result(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	// 3.método de getter 
	public int getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	@JsonIgnore
	// Manténgalo fuera del resultado de serialización json
	// 4. Determine si esta respuesta es una respuesta correcta 
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}

	// 5. Defina el método para devolver el objeto
	public static <T> Result<T> createBySuccess() {
		return new Result<T>(ResponseCode.SUCCESS.getCode());
	}

	public static <T> Result<T> createBySuccessMessage(String msg) {
		return new Result<T>(ResponseCode.SUCCESS.getCode(), msg);
	}

	public static <T> Result<T> createBySuccess(T data) {
		return new Result<T>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> Result<T> createByCodeSuccess(int status, String msg, T data) {
		return new Result<T>(status, msg, data);
	}

	public static <T> Result<T> createBySuccess(String msg, T data) {
		return new Result<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	public static <T> Result<T> createByError() {
		return new Result<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
	}

	public static <T> Result<T> createByErrorMessage(String errorMessage) {
		return new Result<T>(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static <T> Result<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new Result<T>(errorCode, errorMessage);
	}

	/**
	* Convertir el valor del parámetro de solicitud a json
	*/ 
	public static JSONObject request2Json(HttpServletRequest request) {
		JSONObject requestJson = new JSONObject();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] pv = request.getParameterValues(paramName);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < pv.length; i++) {
				if (pv[i].length() > 0) {
					if (i > 0) {
						sb.append(",");
					}
					sb.append(pv[i]);
				}
			}
			requestJson.put(paramName, sb.toString());
		}
		return requestJson;
	}
	/**
	* Convierta la solicitud a JSON y verifique los campos que no estén vacíos
	*/
	public static JSONObject convert2JsonAndCheckRequiredColumns(HttpServletRequest request, String requiredColumns) {
		JSONObject jsonObject = request2Json(request);
		hasAllRequired(jsonObject, requiredColumns);
		return jsonObject;
	}

	/**
	* Verifique que los campos obligatorios estén incluidos
	*
	* @param requiredColumns Nombres de campos de parámetros obligatorios separados por comas como "userId, name, telephone"
	*/ 
	public static void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
		if (!isNullOrEmpty(requiredColumns)) {
			// Verifica que el campo no esté vacío
			String[] columns = requiredColumns.split(",");
			String missCol = "";
			for (String column : columns) {
				Object val = jsonObject.get(column.trim());
				if (isNullOrEmpty(val)) {
					missCol += column + "  ";
				}
			}
			if (!isNullOrEmpty(missCol)) {
				jsonObject.clear();
				jsonObject.put("code", CodeEnum.ERROR_400);
				jsonObject.put("msg", "Faltan parámetros obligatorios:" + missCol.trim());
				jsonObject.put("info", new JSONObject());

			}
		}
	}

	public static boolean isNullOrEmpty(String str) {
		return null == str || "".equals(str) || "null".equals(str);
	}

	public static boolean isNullOrEmpty(Object obj) {
		return null == obj || "".equals(obj);
	}
}
