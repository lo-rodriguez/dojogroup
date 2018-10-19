/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.config;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lrodriguezn
 *  Se realizaron cambios a la tabla de parametros 19/07/2018
 * PARAMETER_ID
 * DESCRIPTION
 * DATE_CREATION
 * EXPIRATION_DATE
 * _KEY
 * _VALUE
 *
 */
@Entity
@Table(name = "system_parameters")
public class SystemParameters {
    @Id
    @Column(name = "PARAMETER_ID")
    private Long id;
    @Column(name = "DESCRIPTION")    
    private String decription;
    @Column(name = "DATE_CREATION")    
    private Date dateCreaction;
    @Column(name = "EXPIRATION_DATE")    
    private Date expirationDate;
    @Column(name = "_KEY")
    private String key;
    @Column(name = "_VALUE")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Date getDateCreaction() {
        return dateCreaction;
    }

    public void setDateCreaction(Date dateCreaction) {
        this.dateCreaction = dateCreaction;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
