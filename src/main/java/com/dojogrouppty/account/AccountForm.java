/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.account;

import java.util.List;

/**
 *
 * @author lrodriguezn
 */
public class AccountForm {
   private String role;
   private String firtname;
   private String lastname;
   private String email; //user
   private String password;
   private String repeatPassword;
   private String registrationDate;
   private List <AccountDTO> listAccountDto;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirtname() {
        return firtname;
    }

    public void setFirtname(String firtname) {
        this.firtname = firtname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public List<AccountDTO> getListAccountDto() {
        return listAccountDto;
    }

    public void setListAccountDto(List<AccountDTO> listAccountDto) {
        this.listAccountDto = listAccountDto;
    }
   
    
}
