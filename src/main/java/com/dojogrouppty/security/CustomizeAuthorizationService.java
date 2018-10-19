/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * @author lrodriguezn
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomizeAuthorizationService {
    
}
