/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lrodriguezn
 * field:
 * id_detail_payments
 * id_product
 * tax
 * id_student
 * discount
 * payment
 * comment
 * id_payment
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "detail_payments")
public class DetailPayment  implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id_detail_payments")
    private int idDetailPayments;
    @Column(name = "id_product")
    private int idProduct;
    @Column(name = "discount")
    private int discount;
    @Column(name = "payment")
    private BigDecimal payment;
     @Column(name = "tax")
    private BigDecimal tax;
    @Column(name = "comment")
    private String comment;
    @Column(name = "id_payment")
    private int idPayment;
    @Column(name = "id_student")
    private int idStudent;

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    
    public int getIdDetailPayments() {
        return idDetailPayments;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public void setIdDetailPayments(int idDetailPayments) {
        this.idDetailPayments = idDetailPayments;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }
  
}
