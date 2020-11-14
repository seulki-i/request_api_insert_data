package com.example.demo.common.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class JsonInfoDTO<T> {

	private Long code;
	private String message;
	private List<T> data;

	public JsonInfoDTO(){
		code = 0L;
	}

	public JsonInfoDTO(Long code, String message) {
		this.code = code;
		this.message = message;
	}

	public JsonInfoDTO(Long code, String message, List<T> data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonInfoDTO [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
}
