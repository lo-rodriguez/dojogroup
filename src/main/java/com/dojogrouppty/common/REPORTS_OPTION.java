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
public enum REPORTS_OPTION {
    STATE_STUDENT_ACCOUNT((short)0),
    ADMINISTRATIVE_REPORT((short)1),
    PAYMENT_HISTORY_BY_STUDENT((short)2),
    RECEIPTS_REPORT((short)3),
    REPORT_FOR_DOWNLOAD((short)4),
    REPORT_NULL((short)99);
    private Short v;
    REPORTS_OPTION(short v){
        this.v=v;
    }
  public  Short getOption(){
     return this.v;
    }
   public static REPORTS_OPTION convertOption(short v){
        switch(v){
            case 0: return STATE_STUDENT_ACCOUNT;
            case 1: return ADMINISTRATIVE_REPORT;                
            case 2: return PAYMENT_HISTORY_BY_STUDENT;
            case 3: return RECEIPTS_REPORT;
            case 4: return REPORT_FOR_DOWNLOAD;                      
        }
       return REPORT_NULL; 
    } 
}
