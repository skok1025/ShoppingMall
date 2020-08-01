package com.cafe24.mall.dto;

public class JSONResult {
	private String result; // success,fail
	private String message;//if fail, set message
	private Object data;// if success, set Object
	
	public static JSONResult success(String message,Object data) {
		return new JSONResult("success", message, data);
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult("fail",message, null);
	}
	
	//생성자를 블라인드
	private JSONResult(String result,String message,Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}
