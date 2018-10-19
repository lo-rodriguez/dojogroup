/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lrodriguezn
 */
@Repository
public interface DetailPaymentRepository extends JpaRepository<DetailPayment, Long>  {
    
}
