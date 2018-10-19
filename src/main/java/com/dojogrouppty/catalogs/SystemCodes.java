/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.catalogs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lrodriguezn
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "system_codes")
public class SystemCodes  implements java.io.Serializable,Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "idsystem_codes")
    private Long id;
    @Column(name = "system_code")
    private String code;
    @Column(name = "system_cod_description")
    private String description;
    @Column(name = "system_cod_group")
    private String group;
    @Column(name = "sub_group_description")
    private String subGrupDescription;

    @Override
    public String toString() {
        return "SystemCodes{" + "id=" + id + ", code=" + code + ", description=" + description + ", group=" + group + '}';
    }

    public SystemCodes() {
    }
    public SystemCodes clone() throws CloneNotSupportedException {
    	SystemCodes  syscloneable = (SystemCodes)super.clone();
    	syscloneable.subGrupDescription = this.getSubGrupDescription();
    	syscloneable.group = this.group;
    	syscloneable.code=null;
    	syscloneable.id=null;
		return syscloneable;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

	public String getSubGrupDescription() {
		return subGrupDescription;
	}

	public void setSubGrupDescription(String subGrupDescription) {
		this.subGrupDescription = subGrupDescription;
	}
    
}
