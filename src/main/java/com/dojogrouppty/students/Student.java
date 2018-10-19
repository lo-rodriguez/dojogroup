/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.students;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author lrodriguezn
 */
/**
 * Estructura de la tabla de estudiante al 11/03/2018
 * MariaDB [dbbzk]> describe student;
+--------------------+--------------+------+-----+---------+----------------+
| Field              | Type         | Null | Key | Default | Extra          |
+--------------------+--------------+------+-----+---------+----------------+
| FIRST_NAME         | varchar(40)  | NO   |     | NULL    |                |
| LAST_NAME          | varchar(40)  | NO   |     | NULL    |                |
| MAIL_CONTACT       | varchar(60)  | YES  |     | NULL    |                |
| SEX                | smallint(1)  | YES  |     | NULL    |                |
| WEIGHT             | int(3)       | NO   |     | NULL    |                |
| BIRTHDATE          | date         | NO   |     | NULL    |                |
| DAY_OF_INCOME      | date         | YES  |     | NULL    |                |
| DATE_OF_LOW        | date         | NO   |     | NULL    |                |
| STATUS             | smallint(1)  | YES  |     | NULL    |                |
| CATEGORY           | int(3)       | YES  |     | NULL    |                |
| TELEPHONE_CONTACT  | varchar(30)  | YES  |     | NULL    |                |
| ADDRESS            | varchar(424) | YES  |     | NULL    |                |
| *DOC_ID             | varchar(60)  | NO   |     | NULL    |                |
| *ID_STUDENT         | int(10)      | NO   | PRI | NULL    | auto_increment |
| *ID_TEACHER         | int(10)      | NO   | MUL | NULL    |                |
| *SURNAME            | varchar(40)  | NO   |     | NULL    |                |
|* SURNAME2           | varchar(40)  | NO   |     | NULL    |                |
|* CELLPHONE          | varchar(20)  | NO   |     | NULL    |                |
|* CELLPHONE2        | varchar(20)  | YES  |     | NULL    |                |
|* TELEPHONE_CONTACT2 | varchar(20)  | YES  |     | NULL    |                |
|* BLOOD_TYPE         | int(5)       | NO   |     | NULL    |                |
|* ALLERGY            | varchar(40)  | NO   |     | NULL    |                |
|* SIZE               | int(5)       | YES  |     | NULL    |                |
|* HEIGHT             | int(5)       | YES  |     | NULL    |                |
|* PHOTO              | blob         | YES  |     | NULL    |                |
|* NAME_OF_GUARDIAN   | varchar(40)  | YES  |     | NULL    |                |
|* GUARDIAN_LAST_NAME | varchar(40)  | YES  |     | NULL    |                |
|* MAIL_GUARDIAN      | varchar(60)  | YES  |     | NULL    |                |
|* PHONE_GUARDIAN     | varchar(20)  | YES  |     | NULL    |                |
|* PHONE_GUARDIAN2    | varchar(20)  | YES  |     | NULL    |                |
|* DOC_ID_GUARDIAN   | varchar(60)  | YES  |     | NULL    |                |
+--------------------+--------------+------+-----+---------+----------------+
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "student")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student  implements java.io.Serializable{
    @Id
    @GeneratedValue
    @Column(name = "ID_STUDENT")
    private Long idStudent;
    @Column(name = "ID_TEACHER")
    private Long idTeacher;
    @Column(name = "DOC_ID")
    private String docId;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "TELEPHONE_CONTACT")
    private String telephoneContact;
    @Column(name = "CATEGORY")
    private int category;
    @Column(name = "STATUS")
    private short status;
    @Column(name = "DATE_OF_LOW")
    private Date dateOfLow;
    @Column(name = "DAY_OF_INCOME")
    private Date dayOfIncome;
    @Column(name = "BIRTHDATE")
    private Date birthdate;
    @Column(name = "WEIGHT")
    private float weight;
    @Column(name = "SEX")
    private short sex;
    @Column(name = "MAIL_CONTACT")
    private String mailContact;
    @Column(name = "MAIL_CONTACT2")
    private String mailContact2;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "SURNAME2")
    private String surname2;
    @Column(name = "CELLPHONE")
    private String cellPhone;
    @Column(name = "CELLPHONE2")
    private String cellPhone2;
    @Column(name = "TELEPHONE_CONTACT2")
    private String telephoneContact2;
    @Column(name = "BLOOD_TYPE")
    private short bloodtype;
    @Column(name = "ALLERGY")
    private String allergy;
    @Column(name = "SIZE")
    private short size;
    @Column(name = "HEIGHT")
    private float height;
    @Column(name = "PHOTO")
    private byte [] photo;
    @Column(name = "NAME_OF_GUARDIAN")
    private String nameGuardian;         
    @Column(name = "GUARDIAN_LAST_NAME")
    private String guardianSurname; 
    @Column(name = "MAIL_GUARDIAN")
    private String mailGuardian;   
    @Column(name = "PHONE_GUARDIAN")
    private String phoneGuardian; 
    @Column(name = "PHONE_GUARDIAN2")
    private String phoneGuardian2; 
    @Column(name = "DOC_ID_GUARDIAN")
    private String docIdGuardian;
    @Column(name = "ID_EMPLOYMENT")
    private Short idEmployment;

    public Student() {
    }

  public Student(Long idTeacher, String docId, String address, String telephoneContact, int category, Date birthdate, int weight, short sex, String mailContact, String lastName2, String lastName, String firstName) {
        this.idTeacher = idTeacher;
        this.docId = docId;
        this.address = address;
        this.telephoneContact = telephoneContact;
        this.category = category;
        this.birthdate = birthdate;
        this.weight = weight;
        this.sex = sex;
        this.mailContact = mailContact;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Short getIdEmployment() {
        return idEmployment;
    }

    public void setIdEmployment(Short idEmployment) {
        this.idEmployment = idEmployment;
    }


    
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCellPhone2() {
        return cellPhone2;
    }

    public void setCellPhone2(String cellPhone2) {
        this.cellPhone2 = cellPhone2;
    }

    public String getTelephoneContact2() {
        return telephoneContact2;
    }

    public void setTelephoneContact2(String telephoneContact2) {
        this.telephoneContact2 = telephoneContact2;
    }

    public short getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(short bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }



    public String getNameGuardian() {
        return nameGuardian;
    }

    public void setNameGuardian(String nameGuardian) {
        this.nameGuardian = nameGuardian;
    }

    public String getGuardianSurname() {
        return guardianSurname;
    }

    public void setGuardianSurname(String guardianSurname) {
        this.guardianSurname = guardianSurname;
    }

    public String getMailGuardian() {
        return mailGuardian;
    }

    public void setMailGuardian(String mailGuardian) {
        this.mailGuardian = mailGuardian;
    }

    public String getPhoneGuardian() {
        return phoneGuardian;
    }

    public void setPhoneGuardian(String phoneGuardian) {
        this.phoneGuardian = phoneGuardian;
    }

    public String getPhoneGuardian2() {
        return phoneGuardian2;
    }

    public void setPhoneGuardian2(String phoneGuardian2) {
        this.phoneGuardian2 = phoneGuardian2;
    }

    public String getDocIdGuardian() {
        return docIdGuardian;
    }

    public void setDocIdGuardian(String docIdGuardian) {
        this.docIdGuardian = docIdGuardian;
    }
        

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public Long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneContact() {
        return telephoneContact;
    }

    public void setTelephoneContact(String telephoneContact) {
        this.telephoneContact = telephoneContact;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getDateOfLow() {
        return dateOfLow;
    }

    public void setDateOfLow(Date dateOfLow) {
        this.dateOfLow = dateOfLow;
    }

    public Date getDayOfIncome() {
        return dayOfIncome;
    }

    public void setDayOfIncome(Date dayOfIncome) {
        this.dayOfIncome = dayOfIncome;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public short getSex() {
        return sex;
    }

    public void setSex(short sex) {
        this.sex = sex;
    }

    public String getMailContact() {
        return mailContact;
    }

    public String getMailContact2() {
        return mailContact2;
    }

    public void setMailContact2(String mailContact2) {
        this.mailContact2 = mailContact2;
    }

    
    
    public void setMailContact(String mailContact) {
        this.mailContact = mailContact;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" + "idStudent=" + idStudent + ", idTeacher=" + idTeacher + ", docId=" + docId + ", address=" + address + ", telephoneContact=" + telephoneContact + ", category=" + category + ", status=" + status + ", dateOfLow=" + dateOfLow + ", dayOfIncome=" + dayOfIncome + ", birthdate=" + birthdate + ", weight=" + weight + ", sex=" + sex + ", mailContact=" + mailContact + ", lastName=" + lastName + ", firstName=" + firstName + ", surname=" + surname + ", surname2=" + surname2 + ", cellPhone=" + cellPhone + ", cellPhone2=" + cellPhone2 + ", telephoneContact2=" + telephoneContact2 + ", bloodtype=" + bloodtype + ", allergy=" + allergy + ", size=" + size + ", height=" + height + ", photo=" + photo + ", nameGuardian=" + nameGuardian + ", guardianSurname=" + guardianSurname + ", mailGuardian=" + mailGuardian + ", phoneGuardian=" + phoneGuardian + ", phoneGuardian2=" + phoneGuardian2 + ", docIdGuardian=" + docIdGuardian + ", idEmployment=" + idEmployment + '}';
    }

 
  
}
