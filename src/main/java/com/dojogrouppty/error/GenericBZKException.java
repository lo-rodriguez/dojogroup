/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.error;

/**
 *
 * @author lrodriguezn
 */
public class GenericBZKException extends Exception {
private static final long serialVersionUID = 1l;
    /**
     * Creates a new instance of <code>GenericBZKException</code> without detail
     * message.
     */
    public GenericBZKException() {
    }

    /**
     * Constructs an instance of <code>GenericBZKException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GenericBZKException(String msg) {
        super(msg);
    }
}
