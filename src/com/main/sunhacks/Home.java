package com.main.sunhacks;

import com.opensymphony.xwork2.Action;

public class Home {

	private String message;
	private String json;

	public String printHome() {
		System.out.println("Printing home");
		return Action.SUCCESS;
	}

	public String getMsg() {
		message = "Hey how are u";
		return Action.SUCCESS;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
