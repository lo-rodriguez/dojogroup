/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.products;

import java.util.List;

/**
 *
 * @author lrodriguezn
 */
 public class ProductsForm {
 private Float unitPrice;
 private Float tax;
 private String description;
 private List<ProductsDTO> listDto;
 private Integer typeProduct;

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductsDTO> getListDto() {
        return listDto;
    }

    public void setListDto(List<ProductsDTO> listDto) {
        this.listDto = listDto;
    }

	public Integer getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(Integer typeProduct) {
		this.typeProduct = typeProduct;
	}
    
 
}
