package com.dojogrouppty.signin;

import com.dojogrouppty.signup.*;
import org.hibernate.validator.constraints.*;


public class SigninForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    private static final String EMAIL_MESSAGE = "{email.message}";
    private static final String EMAIL_EXISTS_MESSAGE = "{email-exists.message}";
    @NotBlank(message = SigninForm.NOT_BLANK_MESSAGE)
    @Email(message = SigninForm.EMAIL_MESSAGE)
    @EmailExists(message = SigninForm.EMAIL_EXISTS_MESSAGE)
    private String email;

    @NotBlank(message = SigninForm.NOT_BLANK_MESSAGE)
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

}
