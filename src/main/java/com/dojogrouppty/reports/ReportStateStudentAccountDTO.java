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
public class ReportStateStudentAccountDTO {
private String lastMonthlyPayment;
private Integer intLastMonthlyPayment;
private String previousAnnuityDate;
private Integer intPreviousAnnuityDate;
private String active;
private String enrollmentInTheCurrentMonth;
private String enrollment;
private String contractMonth;
private String name;

    public ReportStateStudentAccountDTO() {
        this.lastMonthlyPayment ="";
        this.previousAnnuityDate="";
        this.active="";
        this.enrollmentInTheCurrentMonth="";
        this.enrollment="";
        this.contractMonth="";
        this.name="";
        this.intPreviousAnnuityDate=0;
        this.intLastMonthlyPayment=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
           
    public String getLastMonthlyPayment() {
        return lastMonthlyPayment;
    }

    public void setLastMonthlyPayment(String lastMonthlyPayment) {
        this.lastMonthlyPayment = lastMonthlyPayment;
    }

    public String getPreviousAnnuityDate() {
        return previousAnnuityDate;
    }

    public void setPreviousAnnuityDate(String previousAnnuityDate) {
        this.previousAnnuityDate = previousAnnuityDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getEnrollmentInTheCurrentMonth() {
        return enrollmentInTheCurrentMonth;
    }

    public void setEnrollmentInTheCurrentMonth(String enrollmentInTheCurrentMonth) {
        this.enrollmentInTheCurrentMonth = enrollmentInTheCurrentMonth;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getContractMonth() {
        return contractMonth;
    }

    public void setContractMonth(String contractMonth) {
        this.contractMonth = contractMonth;
    }

	public Integer getIntLastMonthlyPayment() {
		return intLastMonthlyPayment;
	}

	public void setIntLastMonthlyPayment(Integer intLastMonthlyPayment) {
		this.intLastMonthlyPayment = intLastMonthlyPayment;
	}

	public Integer getIntPreviousAnnuityDate() {
		return intPreviousAnnuityDate;
	}

	public void setIntPreviousAnnuityDate(Integer intPreviousAnnuityDate) {
		this.intPreviousAnnuityDate = intPreviousAnnuityDate;
	}
    
}
