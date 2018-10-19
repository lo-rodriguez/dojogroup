/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lrodriguezn
 */
@Entity
@Table(name = "app_profile")
public class AppProfile implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    @Column(name = "id_profile")
    private Long idProfile;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "VALIDATE_HOST")
    Short validateHost;
    
    public AppProfile() {
    }

    @Override
    public String toString() {
        return "AppProfile{" + "idProfile=" + idProfile + ", name=" + name + ", description=" + description + '}';
    }

    public AppProfile(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getValidateHost() {
        return validateHost;
    }

    public void setValidateHost(Short validateHost) {
        this.validateHost = validateHost;
    }
    
}
