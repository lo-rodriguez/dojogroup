package com.dojogrouppty.account;

import javax.persistence.*;

import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "user")
public class Account implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firtName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "role")
    private String role;
    @Column(name = "system_signature_name")
    private String signatureName;
    @Column(name = "host_authorized")
    private String hostAuthorized;
    @Column(name = "date_registration")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRegistration;

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    
    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDategivenLow() {
        return dategivenLow;
    }

    public void setDategivenLow(Date dategivenLow) {
        this.dategivenLow = dategivenLow;
    }
    
    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "date_given_low")
    private Date dategivenLow;
    
    public Account() {
    }

    public Account(Long idAccount, String password, String email) {
        this.password = password;
        this.email = email;
    }

    public Account(String password, String firtName, String lastName, String email, String role) {

        this.password = password;
        this.firtName = firtName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;

    }

    @Override
    public String toString() {
        return "Account{" + "role=" + role + ", firtName=" + firtName + ", lastName=" + lastName + ", email=" + email +"}";
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostAuthorized() {
        return hostAuthorized;
    }

    public void setHostAuthorized(String hostAuthorized) {
        this.hostAuthorized = hostAuthorized;
    }



}
