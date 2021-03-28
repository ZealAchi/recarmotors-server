package com.recarmotors.code;

public enum CodeEnum {
	/*
	 * Codigo de error
	 */
	ERROR_400(400, "Solicite una excepción de procesamiento, espere a que el hermano de I + D se encargue ... "),
	ERROR__500(500, "Error interno, ¿es un puntero nulo? ,Por favor, compruebe "),
	ERROR__404(404, "El recurso no existe"), ERROR__502(502, "Permisos insuficientes"),
	ERROR__10000(10000, "la cuenta ya existe"), ERROR__000(000, "El token ha caducado, vuelve a iniciar sesión"),
	ERROR_10001(10001, "¡No se pudo obtener información!");

	private int errorCode;
	private String errorMsg;

	CodeEnum(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
