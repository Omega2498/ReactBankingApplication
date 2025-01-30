package com.simple.banking.model;

import lombok.Data;


public class LoginRequestDTO {
    private String email;

    public LoginRequestDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	private String password;

    public LoginRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
