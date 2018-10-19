/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lrodriguezn
 */
@Repository
public interface SystemParametersRepository  extends JpaRepository<SystemParameters, Long> {
 @Query(nativeQuery = true, value="SELECT PARAMETER_ID,\n" +
" DESCRIPTION,\n" +
" DATE_CREATION,\n" +
" EXPIRATION_DATE,\n" +
"  _KEY,\n" +
"  _VALUE "+
" FROM SYSTEM_PARAMETERS "+ 
 "WHERE DATE_FORMAT(EXPIRATION_DATE,'%Y%m%d')  ='29991231' AND _KEY=:key LIMIT 1")   
  SystemParameters getParameter(@Param("key")String key);

}
