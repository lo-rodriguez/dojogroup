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
public enum MODULES {

    STUDENT("student"),
    PAYMENT("paymentStudent"),
    REPORTS("reports"),
    PRODUCTS("products"),
    OPTION_NOTFOUND("notFound"),
    HOME("home"),
    ACTIVITIES("activities");
    private final String v;

    MODULES(String v) {
        this.v = v;
    }

    public String moduleOption() {
        return this.v;
    }

    public static MODULES convertOption(short v) {
        switch (v) {
            case 1:
                return STUDENT;
            case 2:
                return PAYMENT;
            case 3:
                return REPORTS;
            case 4:
                return PRODUCTS;
            case 5:
                return ACTIVITIES;
            default:
                return OPTION_NOTFOUND;
        }
    }
}
