/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.students;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lrodriguezn
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM STUDENT WHERE STATUS=1")
    List<Student> getStudentEnable();

    @Query(nativeQuery = true, value = "SELECT COUNT(1) FROM STUDENT WHERE  ADDDATE(DAY_OF_INCOME,(SELECT _VALUE FROM  SYSTEM_PARAMETERS WHERE _KEY ='DAYS_REG_NOTIFICATION')) > NOW(); ")
    int accountLatestInscriptions();

    @Query(nativeQuery = true, value = "select IF(count(1) > 0,1,0) from STUDENT a where a.MAIL_CONTACT = :email")
    int exists(@Param("email") String email);

    @Query(nativeQuery = true, value = "select IF(count(1) > 0,1,0) from STUDENT a where a.MAIL_CONTACT = :email AND a.ID_STUDENT <>:id")
    int exists(@Param("email") String email, @Param("id") Long id);

    //@Query("select s.first_name,s.last_name ,s.mail_contact,s.sex ,s.weight,s.birthdate ,s.day_of_income ,s.date_of_low ,s.status,s.category,s.telephone_contact ,s.address ,s.doc_id,s.id_student,s.id_teacher,s.surname ,s.surname2,s.cellphone ,s.cellphone2,s.telephone_contact ,s.blood_type,s.allergy ,s.size,s.height,s.photo ,s.name_of_guardian,s.guardian_last_name,s.mail_guardian ,s.phone_guardian,s.phone_guardian2 ,s.doc_id_guardian ,s.id_employment, s.telephone_contact2 from Student s where s.status=:status")
    @Query("select s from Student s where s.status=:status")
    List<Student> findByStatus(@Param("status") Short status);

}
