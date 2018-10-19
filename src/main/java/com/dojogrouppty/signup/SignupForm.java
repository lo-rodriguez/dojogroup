package com.dojogrouppty.signup;

import org.hibernate.validator.constraints.*;

import com.dojogrouppty.account.Account;
import com.dojogrouppty.common.ParentControllerService;

public class SignupForm extends ParentControllerService{

	
        @NotBlank(message = NOT_BLANK_MESSAGE)
	@Email(message = EMAIL_MESSAGE)
	@EmailExists(message =EMAIL_EXISTS_MESSAGE)
	private String email;
        private String firtName;
        private String lastName;
        private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

        @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;

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
/**
 * Crear cuenta por defecto
 * @return 
 */
	public Account createAccount() {
        return new Account(password,firtName, lastName, email,role);
	}
}
