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
public enum DISABLED {

    DISABLED_OFF("true"),
    ENABLED("false");

    private String value;

    DISABLED(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
