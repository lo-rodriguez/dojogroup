/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

/**
 *
 * @author lrodriguezn
 */
public class AmountPayableDTO {
    private Float unitPrice;
    private Float tax;
    private Float amoutPayable;
    private Long idProduct;    

    public AmountPayableDTO(Float unitPrice, Float amoutPayable,Float tax, Long idProduct) {
        this.unitPrice = unitPrice;
        this.amoutPayable = amoutPayable;
        this.idProduct = idProduct;
        this.tax=tax;
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

    public Float getAmoutPayable() {
        return amoutPayable;
    }

    public void setAmoutPayable(Float amoutPayable) {
        this.amoutPayable = amoutPayable;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
    
}
