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
public enum ERROR_REPORTS {
    ERROR_INITIAL_RECEIPT("error.report.Receipt.initialReceipt"),
    ERROR_FINAL_RECEIPT("error.report.Receipt.finalReceipt"),
    ERROR_TYPE_PAYMENT("error.report.Receipt.typePayment"),
    ERROR_INITIAL_DATE("error.report.reportForDownload.initialDate"),   
    ERROR_FINAL_DATE("error.report.reportForDownload.finalDate"),
    ERROR_THE_INITIAL_DATE_IS_GREATER_THAN_FINAL_DATE("error.report.reportForDownload.TheInitialDateIsGreaterThanTheFinal"),
 
    ;
    private final String v;
    ERROR_REPORTS(String v) {
        this.v = v;
    }
     public String getValue() {
        return this.v;
    }
}
