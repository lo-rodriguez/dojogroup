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
public enum HOST_AUTHORIZED {
    WITHOUT_VALIDATING((short)1),
    VALIDATE_IP((short)2),
    VALIDATING_MAC_ADDRESS((short)3);    
    HOST_AUTHORIZED(short v){
    } 
}
