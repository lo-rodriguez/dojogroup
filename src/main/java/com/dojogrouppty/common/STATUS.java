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
public enum STATUS {
    PENDING((short)0),
    ACTIVE((short)1),
    INACTIVE((short)2),
    DELETED((short)3);
    private Short v;
    STATUS(short v){
        this.v=v;
    }
  public  Short getStatus(){
     return this.v;
    }
}
