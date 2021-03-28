package com.recarmotors.util;

public enum ResponseCode {
	// 1. Definir valores de enumeración
	ERROR(0, "ERROR"),

	SUCCESS(1, "SUCCESS"),

	NEED_LOGIN(10, "NEED_LOGIN"),

	ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

	// 2. Definir propiedades de enumeración
	private final int code;

	private final String desc;

	// 3. Definir el constructor
	ResponseCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	// 4. Definir el método get
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
