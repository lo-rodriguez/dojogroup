/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lrodriguezn field of table id_payment id_product id_student
 * date_registration payday user_register total_payment total_tax
 * number_of_transfer name_of_bank
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "payments")
public class Payments implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_payment")
   private int idPayment;
    @Column(name = "date_registration")
   private Date dateRegistration;
    @Column(name = "payday")
   private Date payday;
    @Column(name = "user_register")
   private String userRegister;
    @Column(name = "total_payment")
   private Float totalPayment;
    @Column(name = "total_tax")
   private Float totalTax;
    @Column(name = "number_of_transfer")
   private String numberOfTransfer;
    @Column(name = "name_of_bank")
   private String nameOfBank;
    @Column(name = "type_payment")
   private int typePayment;
    @Column(name = "subtotal")
    private Float subtotal;

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    
    
    public int getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(int typePayment) {
        this.typePayment = typePayment;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }

    public String getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(String userRegister) {
        this.userRegister = userRegister;
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

    public String getNumberOfTransfer() {
        return numberOfTransfer;
    }

    public void setNumberOfTransfer(String numberOfTransfer) {
        this.numberOfTransfer = numberOfTransfer;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }
    
}
