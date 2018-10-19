/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import com.dojogrouppty.common.ParentControllerService;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lrodriguezn
 * +--------------------+--------------+------+-----+---------+----------------+
 * | Field | Type | Null | Key | Default | Extra |
 * +--------------------+--------------+------+-----+---------+----------------+
 * | id_payment | int(10) | NO | PRI | NULL | auto_increment | | id_product |
 * int(10) | NO | | NULL | | | id_student | int(10) | NO | | NULL | | |
 * date_registration | datetime | NO | | NULL | | | payday | datetime | YES | |
 * NULL | | | user_register | varchar(40) | NO | | NULL | | | total_payment |
 * decimal(7,2) | NO | | NULL | | | total_tax | decimal(7,2) | YES | | NULL | |
 * | number_of_transfer | varchar(40) | YES | | NULL | | | name_of_bank |
 * varchar(40) | YES | | NULL | |
 * +--------------------+--------------+------+-----+---------+----------------+
 */
public class PaymentForm extends ParentControllerService{

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idProduct);
        hash = 83 * hash + Objects.hashCode(this.datePay);        
        hash = 83 * hash + Objects.hashCode(this.totalPayment);
        hash = 83 * hash + Objects.hashCode(this.totalTax);
        hash = 83 * hash + Objects.hashCode(this.numberOfTransfer);
        hash = 83 * hash + Objects.hashCode(this.nameBank);
        hash = 83 * hash + Objects.hashCode(this.typePayment);
        hash = 83 * hash + Objects.hashCode(this.idStudent);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaymentForm other = (PaymentForm) obj;
        if (!Objects.equals(this.idProduct, other.idProduct)) {
            return false;
        }       
        if (!Objects.equals(this.datePay, other.datePay)) {
            return false;
        }       
        if (!Objects.equals(this.totalPayment, other.totalPayment)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.numberOfTransfer, other.numberOfTransfer)) {
            return false;
        }
        if (!Objects.equals(this.nameBank, other.nameBank)) {
            return false;
        }
        if (!Objects.equals(this.typePayment, other.typePayment)) {
            return false;
        }
        if (!Objects.equals(this.idStudent, other.idStudent)) {
            return false;
        }
        return true;
    }

    private String idProduct;
    private Date dateRegister;
    @NotNull(message=VALID_DAY_PAYMENT)
    private String datePay;
    private String userRegister;
    @DecimalMax(value="99999.99",message=VALID_TOTAL_PAYMENT_MAX)
    @DecimalMin(value="1",message=VALID_TOTAL_PAYMENT_MIN)
    private Float totalPayment;
    @DecimalMax(value="99999.99",message=VALID_TOTAL_FAX_MAX)
    @DecimalMin(value="0",message=VALID_TOTAL_FAX_MIN)
    private Float totalTax;
    @Size(min=0, max=40, message=VALID_NUMBER_OF_TRANSFER)
    private String numberOfTransfer;
    @Size(min=0, max=40, message=VALID_NAME_BANK)
    private String nameBank;
    private String typePayment;
    private Integer idStudent;

    public PaymentForm(Date dateRegister, Float totalPayment, String numberOfTransfer) {
        this.dateRegister = dateRegister;
        this.totalPayment = totalPayment;
        this.numberOfTransfer = numberOfTransfer;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public PaymentForm() {
        idProduct = "";
        dateRegister = new Date();
        datePay = "";
        userRegister = "";
        totalPayment = 0F;
        totalTax = 0F;
        numberOfTransfer = "";
        nameBank = "";
        typePayment = "";
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getDatePay() {
        return datePay;
    }

    public void setDatePay(String datePay) {
        this.datePay = datePay;
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

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

}
