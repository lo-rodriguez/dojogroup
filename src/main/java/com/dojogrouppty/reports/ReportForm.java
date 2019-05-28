/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.reports;

/**
 *
 * @author lrodriguezn
 */
public class ReportForm {
   private String initialDate;
   private String finalDate;
   private Integer idStudent;
   private String typeProduct;
   private String typePayment;
   private String active;
   private  String initialReceipt;
   private String finalReceipt;
   
   

    public ReportForm() {
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }




    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }


   

    public String getInitialReceipt() {
        return initialReceipt;
    }

    public void setInitialReceipt(String initialReceipt) {
        this.initialReceipt = initialReceipt;
    }

    public String getFinalReceipt() {
        return finalReceipt;
    }

    public void setFinalReceipt(String finalReceipt) {
        this.finalReceipt = finalReceipt;
    }

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
    
}
