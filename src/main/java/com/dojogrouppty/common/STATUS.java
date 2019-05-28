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
	ALL_STUDENTS(new Short((short)0)),
    ACTIVE(new Short((short)1)),
    INACTIVE(new Short((short)2)),
    DELETED(new Short((short)3));
    private Short v;
    STATUS(short v){
        this.v=v;
    }
  public  Short getStatus(){
     return this.v;
    }
  public String getString() {
	  return this.v.toString();
  }
}
