/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import java.math.BigDecimal;

/**
 *
 * @author lrodriguezn
 */
public class DetailPaymentDTO {
   private BigDecimal total;
   private BigDecimal tax;
   private String comment;
   private Long idStudent;
   private Long idProduct;

    public DetailPaymentDTO(BigDecimal total, BigDecimal tax, String comment, Long idStudent, Long idProduct) {
        this.total = total;
        this.tax = tax;
        this.comment = comment;
        this.idStudent = idStudent;
        this.idProduct = idProduct;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
   
}
