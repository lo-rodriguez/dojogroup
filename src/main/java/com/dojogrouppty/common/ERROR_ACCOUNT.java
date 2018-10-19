/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.common;

/**
 *
 * @author lrodriguezn
 */
public enum ERROR_ACCOUNT {
    ERROR_EMAIL("dashboard.account.error.email"),
    ERROR_PASSWORD_SIZE("dashboard.account.error.password"),    
    ERROR_PASSWORD_CONFIRM("dashboard.account.error.password2"),
    ERROR_INSERT_ACCOUNT("dashboard.account.error.insertAccount"),
    ERROR_PASSWORD_RULES("dashboard.account.error.passwordFinal")
    ,
    ;
    private final String v;
    ERROR_ACCOUNT(String v) {
        this.v = v;
    }
     public String getValue() {
        return this.v;
    }
}
