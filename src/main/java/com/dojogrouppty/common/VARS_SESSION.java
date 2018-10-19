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
public enum VARS_SESSION {
    ID_STUDENT("idStudent"),
    REGISTER_USER("registerUser"),
    OBJECT_PAYMENT("lastPayment");
    private final String v;
     VARS_SESSION(String v){
      this.v=v;  
    }
      public  String varOption(){
     return this.v;
    }
}
