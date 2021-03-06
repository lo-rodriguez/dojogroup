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
public class ReportDetailDTO {
  private  String date;
  private Integer intDate;
  private Long longDate;
  private  String name;
  private  String number;
  private  String product;
  private  String paymentMethod;
  private  String amount;
  private  String totalPerDay;
  private  String dateSubTotal;
  private String comment; 
  private Long dateOrder;

    public ReportDetailDTO() {
        this.date = "";
        this.name = "";
        this.product = "";
        this.paymentMethod = "";
        this.amount = "";
        this.totalPerDay = "";
        this.dateSubTotal  = "";
        this.comment="";
        this.intDate=0;
        this.dateOrder=0L;
    }

    public String getDateSubTotal() {
        return dateSubTotal;
    }

    public void setDateSubTotal(String dateSubTotal) {
        this.dateSubTotal = dateSubTotal;
    }


  
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotalPerDay() {
        return totalPerDay;
    }

    public void setTotalPerDay(String totalPerDay) {
        this.totalPerDay = totalPerDay;
    }

	public Integer getIntDate() {
		return intDate;
	}

	public void setIntDate(Integer intDate) {
		this.intDate = intDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Long dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Long getLongDate() {
		return longDate;
	}

	public void setLongDate(Long longDate) {
		this.longDate = longDate;
	}
    
    
}
