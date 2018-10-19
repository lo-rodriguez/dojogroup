/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.products;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lrodriguezn
 */
@Repository
public interface ProductsRepository  extends JpaRepository<Products, Long> {
 @Query(nativeQuery = true, value="SELECT COUNT(1) FROM PRODUCTS WHERE DATE_FORMAT(EXPIRATION_DATE,'%Y%m%d')  ='29991231'")   
  int currentProductAccount();
  @Query(nativeQuery = true, value="SELECT P.ID_PRODUCT,C.SYSTEM_COD_DESCRIPTION  \n" +
"  FROM PRODUCTS P,SYSTEM_CODES C \n" +
"  WHERE DATE_FORMAT(P.EXPIRATION_DATE,'%Y%m%d')  ='29991231' AND C.IDSYSTEM_CODES=P.TYPE ORDER BY C.SYSTEM_COD_DESCRIPTION ASC")   
  List<Object[]> getProductsEnable();
    @Query(nativeQuery = true, value="SELECT P.ID_PRODUCT,C.SYSTEM_COD_DESCRIPTION,  \n" +
" DATE_FORMAT(P.DATE_REGISTRATION,'%d/%m/%Y'),P.UNIT_PRICE ,P.TAX "
            + "FROM PRODUCTS P,SYSTEM_CODES C \n" +
"  WHERE DATE_FORMAT(P.EXPIRATION_DATE,'%Y%m%d')  ='29991231' AND C.IDSYSTEM_CODES=P.TYPE ORDER BY C.SYSTEM_COD_DESCRIPTION ASC")   
  List<Object[]> getProductsEnableForEdit();
}
