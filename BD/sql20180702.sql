#SELECT DATE_FORMAT(p.payday, '%d/%m/%Y'),p.number_of_transfer,c.system_cod_description,FORMAT(p.total_payment,2,'en_US') 
#FROM payments p, system_codes c 
#WHERE ( p.type_payment =c.idsystem_codes ) 
#AND( CAST(number_of_transfer AS INTEGER) >= :parm1
#AND CAST(number_of_transfer AS INTEGER) <= :parm2 )
#AND ( P.type_payment =:parm3 OR :parm3 =0) 
#ORDER BY p.number_of_transfer,c.system_cod_description ASC

#SELECT 
#DATE_FORMAT(P.PAYDAY, '%d/%m/%Y') AS DAY_PAYMENT, 
#CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,' '),S.SURNAME),','),S.SURNAME2) AS NAME,
#P.NUMBER_OF_TRANSFER AS RECEIPT_NUMBER,
#C.SYSTEM_COD_DESCRIPTION AS TYPE_PRODUCT, TP.SYSTEM_COD_DESCRIPTION AS TYPE_PAYMENT,
#FORMAT(SUM(D.TAX+D.PAYMENT),2,'en_US') AS PAYMENT 
#FROM STUDENT S, DETAIL_PAYMENTS D,PAYMENTS P, SYSTEM_CODES C,PRODUCTS PR,SYSTEM_CODES TP
#WHERE S.ID_STUDENT = D.ID_STUDENT
#AND D.ID_PAYMENT = P.ID_PAYMENT
#AND D.ID_PRODUCT = PR.ID_PRODUCT 
#AND PR.TYPE = C.IDSYSTEM_CODES
#AND P.TYPE_PAYMENT = TP.IDSYSTEM_CODES
#AND P.PAYDAY >= '2018-06-18 00:00:00'
#AND P.PAYDAY <= '2018-07-02 00:00:00'
#AND PR.ID_PRODUCT = 1
#GROUP BY P.PAYDAY , 
#CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,' '),S.SURNAME),','),S.SURNAME2),
#P.NUMBER_OF_TRANSFER,C.SYSTEM_COD_DESCRIPTION,TP.SYSTEM_COD_DESCRIPTION
#ORDER BY DAY_PAYMENT,RECEIPT_NUMBER ASC

#SELECT 
#DATE_FORMAT(P.PAYDAY, '%d/%m/%Y') AS DAY_PAYMENT, 
#CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,','),S.SURNAME),','),S.SURNAME2) AS NAME,
#P.NUMBER_OF_TRANSFER AS RECEIPT_NUMBER,
#C.SYSTEM_COD_DESCRIPTION AS TYPE_PRODUCT, 
#TP.SYSTEM_COD_DESCRIPTION AS TYPE_PAYMENT,
#FORMAT(SUM(D.TAX+D.PAYMENT),2,'en_US') AS PAYMENT 
#FROM STUDENT S, DETAIL_PAYMENTS D,PAYMENTS P, SYSTEM_CODES C,PRODUCTS PR,SYSTEM_CODES TP
#WHERE S.ID_STUDENT = D.ID_STUDENT
#AND D.ID_PAYMENT = P.ID_PAYMENT
#AND D.ID_PRODUCT = PR.ID_PRODUCT 
#AND PR.TYPE = C.IDSYSTEM_CODES
#AND P.TYPE_PAYMENT = TP.IDSYSTEM_CODES
#AND P.PAYDAY >= '2018-06-26 00:00:00'
#AND P.PAYDAY <= '2018-07-03 00:00:00'
#GROUP BY P.PAYDAY , 
#CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,' '),S.SURNAME),','),S.SURNAME2),
#P.NUMBER_OF_TRANSFER,C.SYSTEM_COD_DESCRIPTION,TP.SYSTEM_COD_DESCRIPTION
#ORDER BY P.PAYDAY DESC

select * from detail_payment where id_payment=313;