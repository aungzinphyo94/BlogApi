package com.azp.blog.model;

import com.google.gson.annotations.SerializedName;

public class RegisterData{

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("user")
	private User user;

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}