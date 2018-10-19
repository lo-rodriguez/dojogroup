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
public enum ERROR_PRODUCTS {
    ERROR_DESCRIPTION("dashboard.products.form.error.description"),
    ERROR_ID_PRODUCT("dashboard.products.form.error.idProduct"),
    ERROR_TAX("dashboard.products.form.error.tax"),
    ERROR_UNIT_PRICE("dashboard.products.form.error.unitprice"),  
    ERROR_GENERAL("dashboard.products.form.error.general")
    ;
    private final String v;
    ERROR_PRODUCTS(String v) {
        this.v = v;
    }
     public String getValue() {
        return this.v;
    }
}
