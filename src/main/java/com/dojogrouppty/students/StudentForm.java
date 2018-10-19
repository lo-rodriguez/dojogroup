/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.students;

/**
 *
 * @author lrodriguezn
 */
import com.dojogrouppty.common.ParentControllerService;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.*;
import org.springframework.web.multipart.MultipartFile;
public class StudentForm extends ParentControllerService {
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String firstName;
    private String lastName;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String surname;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String surname2;
    @NotEmpty
    private String birthdate;
    //DAY_OF_INCOME
    private String dayOfIncome;
    private short gender;
    private short bloodGroup;
    private String allergy;
    @Range(min=1,max=300,message=VALID_WEIGTH)
    private float weight;
    @DecimalMax(value="5.0",message=VALID_MAX_HEIGTH)
    @DecimalMin(value="0.0",message=VALID_MIN_HEIGTH)
    private float height;
    private short size;
    private int category;
    private short employment;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String identificationCard;
//   @NotBlank(message = NOT_BLANK_MESSAGE)
//   @Email(message =EMAIL_MESSAGE)
    private String email;
   @Email(message =VALID_FORMAT_MAIL)
    private String email2;
    private String phone1;
    private String phone2;
    private String cellPhone1;
    private String cellPhone2;
//    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String address;
    MultipartFile photo;
    private String nameOfTheGuard;
    private String lastNameOfTheGuardian;      
    @Email(message =VALID_FORMAT_MAIL)
    private String emailOfTheGuardian;
    private String telephonOfTheGuardian;
    private String telephonOfTheGuardian2;
    private String parentsCertificate;
    private String status;
    private Long id;
    private byte []data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentForm(String firstName, String lastName, String surname, String surname2, String birthdate, short gender, short bloodGroup, short category, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surname = surname;
        this.surname2 = surname2;
        this.birthdate = birthdate;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.category = category;
        this.email = email;
    }

   public StudentForm() {
     firstName="";
     lastName="";
     surname="";
     surname="";
     birthdate="";
     gender=0;    
     bloodGroup=0;
     allergy="";
     weight=0;    
     height=0;
     size=0;
     category=0;
     employment=0;    
     identificationCard="";
     email="";
     email2="";
     phone1="";
     phone2="";
     cellPhone1="";
     cellPhone2="";    
     address="";
     photo=null;
     nameOfTheGuard="";
     lastNameOfTheGuardian="";      
     emailOfTheGuardian="";
     telephonOfTheGuardian="";
     telephonOfTheGuardian2="";
     parentsCertificate="";
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "StudentForm{" + "firstName=" + firstName + ", lastName=" + lastName + ", surname=" + surname + ", surname2=" + surname2 + ", birthdate=" + birthdate + ", gender=" + gender + ", bloodGroup=" + bloodGroup + ", allergy=" + allergy + ", weight=" + weight + ", height=" + height + ", size=" + size + ", category=" + category + ", employment=" + employment + ", identificationCard=" + identificationCard + ", email=" + email + ", email2=" + email2 + ", phone1=" + phone1 + ", phone2=" + phone2 + ", cellPhone1=" + cellPhone1 + ", cellPhone2=" + cellPhone2 + ", address=" + address + ", nameOfTheGuard=" + nameOfTheGuard + ", lastNameOfTheGuardian=" + lastNameOfTheGuardian + ", emailOfTheGuardian=" + emailOfTheGuardian + ", telephonOfTheGuardian=" + telephonOfTheGuardian + ", telephonOfTheGuardian2=" + telephonOfTheGuardian2 + ", parentsCertificate=" + parentsCertificate + '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public short getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(short bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

 

    public short getEmployment() {
        return employment;
    }

    public void setEmployment(short employment) {
        this.employment = employment;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getCellPhone1() {
        return cellPhone1;
    }

    public void setCellPhone1(String cellPhone1) {
        this.cellPhone1 = cellPhone1;
    }

    public String getCellPhone2() {
        return cellPhone2;
    }

    public void setCellPhone2(String cellPhone2) {
        this.cellPhone2 = cellPhone2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile  getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile  photo) {
        this.photo = photo;
    }

    public String getNameOfTheGuard() {
        return nameOfTheGuard;
    }

    public void setNameOfTheGuard(String nameOfTheGuard) {
        this.nameOfTheGuard = nameOfTheGuard;
    }

    public String getLastNameOfTheGuardian() {
        return lastNameOfTheGuardian;
    }

    public void setLastNameOfTheGuardian(String lastNameOfTheGuardian) {
        this.lastNameOfTheGuardian = lastNameOfTheGuardian;
    }

    public String getEmailOfTheGuardian() {
        return emailOfTheGuardian;
    }

    public void setEmailOfTheGuardian(String emailOfTheGuardian) {
        this.emailOfTheGuardian = emailOfTheGuardian;
    }

    public String getTelephonOfTheGuardian() {
        return telephonOfTheGuardian;
    }

    public void setTelephonOfTheGuardian(String telephonOfTheGuardian) {
        this.telephonOfTheGuardian = telephonOfTheGuardian;
    }

    public String getTelephonOfTheGuardian2() {
        return telephonOfTheGuardian2;
    }

    public void setTelephonOfTheGuardian2(String telephonOfTheGuardian2) {
        this.telephonOfTheGuardian2 = telephonOfTheGuardian2;
    }

    public String getParentsCertificate() {
        return parentsCertificate;
    }

    public void setParentsCertificate(String parentsCertificate) {
        this.parentsCertificate = parentsCertificate;
    }
    public String getDayOfIncome() {
        return dayOfIncome;
    }

    public void setDayOfIncome(String dayOfIncome) {
        this.dayOfIncome = dayOfIncome;
    }
    
}
