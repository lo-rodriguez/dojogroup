/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lrodriguezn
 */
@Repository
public interface PaymentsRepository  extends JpaRepository<Payments, Long> {
  @Query(nativeQuery = true, value="SELECT COUNT(1) FROM PAYMENTS WHERE  ADDDATE(PAYDAY,(SELECT _VALUE FROM  SYSTEM_PARAMETERS WHERE _KEY ='PAYMENT_NOTIFICATION')) > NOW(); ")     
  int amountOfPaymentsMade();
}
