/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import java.util.List;

/**
 *
 * @author lrodriguezn
 */
public class PaymentDTO {
    private List <DetailPaymentDTO> details;
    private String datePay;
    private Long typePayment;

    public List<DetailPaymentDTO> getDetails() {
        return details;
    }

    public void setDetails(List<DetailPaymentDTO> details) {
        this.details = details;
    }

    public String getDatePay() {
        return datePay;
    }

    public void setDatePay(String datePay) {
        this.datePay = datePay;
    }

    public Long getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(Long typePayment) {
        this.typePayment = typePayment;
    }
    
}
