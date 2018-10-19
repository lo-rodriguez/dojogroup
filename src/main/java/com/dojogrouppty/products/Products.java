/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.products;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lrodriguezn
 */
/**
 * fields of the table id_product type date_registration expiration date
 * user_register user_expiration unit_price tax
 *
 * @author lrodriguezn
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "products")
public class Products implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_product")
    private Long idProduct;
    @Column(name = "type")
    private short type;
    @Column(name = "date_registration")
    private Date dateRegistration;
    @Column(name = "expiration_date")
    private Date expirationDate;
    @Column(name = "user_register")
    private String userRegister;
    @Column(name = "user_expiration")
    private String userExpiration;
    @Column(name = "unit_price")
    private Float unitPrice;
    @Column(name = "tax")
    private Float tax;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public String getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(String userRegister) {
        this.userRegister = userRegister;
    }

    public String getUserExpiration() {
        return userExpiration;
    }

    public void setUserExpiration(String userExpiration) {
        this.userExpiration = userExpiration;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
}
