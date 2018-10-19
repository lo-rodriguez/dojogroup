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
public class TypesPaymentVsYearsDTO {
    private String year;
    private String ach;
    private String check;
    private String cash;
   
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAch() {
        return ach;
    }

    public void setAch(String ach) {
        this.ach = ach;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

       
}
