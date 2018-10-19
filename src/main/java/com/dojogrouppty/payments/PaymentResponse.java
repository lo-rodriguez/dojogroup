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
public class PaymentResponse {
   private String message;
   private Float totalPayment;
   private Float totalTax;
   private Float subTotal;
   private Long numPayment;
   private String ok;

    public PaymentResponse() {
    }

    public PaymentResponse(String message, Float totalPayment, Float totalTax, Long numPayment, String ok) {
        this.message = message;
        this.totalPayment = totalPayment;
        this.totalTax = totalTax;
        this.numPayment = numPayment;
        this.ok = ok;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Float totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Float getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Float totalTax) {
        this.totalTax = totalTax;
    }

    public Long getNumPayment() {
        return numPayment;
    }

    public void setNumPayment(Long numPayment) {
        this.numPayment = numPayment;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
    
}
