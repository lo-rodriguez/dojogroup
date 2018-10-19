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
public enum TYPES_ACTIVITIES {
    INTERNAL(1),EXTERNAL(2),DEFAULT(7);
    TYPES_ACTIVITIES(Integer type){
    }
  public String _getClass(){
      String _class="";
      if(this.equals(INTERNAL)){
          _class="success";
      }
      else if(this.equals(EXTERNAL)){
         _class="important"; 
        }
      else{
        _class="info";   
      }
      return _class;
  }
 public static TYPES_ACTIVITIES getOption(Integer op){
      switch(op){
          case 1: return  INTERNAL;
          case 2: return EXTERNAL;    
      }
     return DEFAULT;
  }
}
