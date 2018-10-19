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
public enum STUDENT_SEARCH_OPTIONS {
     SEARCH_EDIT_STUDENT("searchEditStudent"),
    SEARCH_PAYMENT_STUDENT("searchPaymentStudent"),
    SEARCH_OPTION_NOTFOUND("notFound");
    private final String v;
    STUDENT_SEARCH_OPTIONS(String v){
        this.v=v;
    }
    public  String studentSearchOption(){
     return this.v;
    }
    public static STUDENT_SEARCH_OPTIONS convertOption(short v){
        switch(v){
            case 1: return SEARCH_EDIT_STUDENT;                
            case 2: return SEARCH_PAYMENT_STUDENT;
            default: return SEARCH_OPTION_NOTFOUND;                     
        }
    }  
}
