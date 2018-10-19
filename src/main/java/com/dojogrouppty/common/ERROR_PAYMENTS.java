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
public enum ERROR_PAYMENTS {
    ERROR_TAX("dashboard.payment.form.valid.rule1.TotalFax"),
    ERROR_DAY_PAYMENT("error.payment.dayPayment"),
    ERROR_TOTAL("error.payment.total"),
    ERROR_PRODUCT("error.payment.productid"),
    ERROR_TYPE_PAYMEN("error.payment.typePayment"),
    ERROR_STUDENT("error.payment.student"),
    ERROR_TAX_TOTAL("error.payment.totalTax"),
    ERROR_SAVE_PAYMENT("error.payment.savePayment"),
    ERROR_SAVE_PAYMENT_I("error.payment.savePaymentI"),
    ERROR_NAME_BANK(""),
    EROOR_NUMBER_OF_TRANSFER(""),
    ;
    private final String v;
    ERROR_PAYMENTS(String v) {
        this.v = v;
    }
     public String getValue() {
        return this.v;
    }
}
