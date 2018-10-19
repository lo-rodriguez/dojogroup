/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.products;

import java.math.BigDecimal;

/**
 *
 * @author lrodriguezn
 */
public class ProductsDTO {
    private String idProduct;
    private BigDecimal price;
    private BigDecimal tax;
    private String description;
    private String dateRegistration;
    private Integer accion;

    public ProductsDTO() {
     idProduct="0";
     price=new BigDecimal("0");
     tax=new BigDecimal("0");
     description="";
     accion =0;
    }

    public Integer getAccion() {
        return accion;
    }

    public void setAccion(Integer accion) {
        this.accion = accion;
    }
   
    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
    
}
