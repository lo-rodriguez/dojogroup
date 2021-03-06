select * from system_codes where system_cod_group ='PRODUCTS' ;
#and system_code like 'CLAS%';
select * from products where ID_PRODUCT IN (1,2,3,4,8);

/*SELECT CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,','),S.SURNAME),','),S.SURNAME2) AS NAME,
DATE_FORMAT(MAX(PM.PAYDAY), '%d/%m/%Y') AS PAYDAY_1
 FROM STUDENT S LEFT OUTER JOIN DETAIL_PAYMENTS DM ON  S.ID_STUDENT = DM.ID_STUDENT ,
 PAYMENTS PM,SYSTEM_CODES C,PRODUCTS PROD
WHERE DM.ID_PAYMENT= PM.ID_PAYMENT 
AND DM.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('CLAS00001','CLAS00004','CLAS00005')
GROUP BY NAME */

#LEFT OUTER JOIN
#RIGHT OUTER JOIN
/*SELECT CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,','),S.SURNAME),','),S.SURNAME2) AS NAME,
DM.ID_STUDENT,DATE_FORMAT(MAX(PM.PAYDAY), '%d/%m/%Y')
FROM STUDENT S ,DETAIL_PAYMENTS DM,
 PAYMENTS PM
WHERE DM.ID_PAYMENT= PM.ID_PAYMENT 
AND S.ID_STUDENT = DM.ID_STUDENT
GROUP BY NAME,DM.ID_STUDENT
*/
/*
SELECT P.ID_PAYMENT,D.ID_STUDENT 
FROM PAYMENTS P,DETAIL_PAYMENTS D
WHERE P.ID_PAYMENT = D.ID_PAYMENT
*/
/*
SELECT ID_STUDENT FROM DETAIL_PAYMENTS D
WHERE  EXISTS (SELECT 1 FROM STUDENT S WHERE S.ID_STUDENT = D.ID_STUDENT) */
/*
SELECT DATE_FORMAT(MAX(P.PAYDAY), '%d/%m/%Y') AS  PAYDAY_CLASS,D.ID_STUDENT 
FROM PAYMENTS P, DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE P.ID_PAYMENT = D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('CLAS00001','CLAS00004','CLAS00005')
GROUP BY D.ID_STUDENT
S LEFT OUTER JOIN DETAIL_PAYMENTS DM */
/*
SELECT DATE_FORMAT(MAX(P.PAYDAY), '%d/%m/%Y') AS  LAST_PAID_MONTHY,D.ID_STUDENT 
FROM PAYMENTS P, DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE P.ID_PAYMENT = D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('MAT2018','MATR00001')
GROUP BY D.ID_STUDENT
*/

/*
SELECT CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,','),S.SURNAME),','),S.SURNAME2) AS NAME,S.ID_STUDENT, LAST_PAID_MONTHY,ANNUITY_DATE
FROM STUDENT S LEFT OUTER JOIN ( (
SELECT DATE_FORMAT(MAX(P.PAYDAY), '%d/%m/%Y') AS  LAST_PAID_MONTHY,D.ID_STUDENT 
FROM PAYMENTS P, DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE P.ID_PAYMENT = D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('CLAS00001','CLAS00004','CLAS00005')
GROUP BY D.ID_STUDENT
) T ,(
SELECT DATE_FORMAT(MAX(P.PAYDAY), '%d/%m/%Y') AS ANNUITY_DATE,D.ID_STUDENT 
FROM PAYMENTS P, DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE P.ID_PAYMENT = D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('MAT2018','MATR00001')
GROUP BY D.ID_STUDENT) T2 )ON (S.ID_STUDENT =T.ID_STUDENT AND S.ID_STUDENT =T2.ID_STUDENT)
*/
/*
SELECT ADDDATE(DAY_OF_INCOME,((YEAR(NOW()) -YEAR(DAY_OF_INCOME))*365)),
		 DAY_OF_INCOME,
		 ((YEAR(NOW()) -YEAR(DAY_OF_INCOME))*365),
		 DATEDIFF(DAY_OF_INCOME,((YEAR(NOW()) -YEAR(DAY_OF_INCOME))*365)),
		 YEAR(NOW()) -YEAR(DAY_OF_INCOME),
 		 ADDDATE(DAY_OF_INCOME,DATEDIFF(NOW(),DAY_OF_INCOME)),
		 NOW(),
		 DAY_OF_INCOME,
      if(MONTH(DAY_OF_INCOME) - MONTH(NOW())=0,'SI','NO'), ADDDATE(DAY_OF_INCOME,3) 
from student
WHERE ID_STUDENT=98;
*/
/*
SELECT CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,','),S.SURNAME),','),S.SURNAME2) AS NAME,
S.ID_STUDENT, 
DATE_FORMAT(S.DAY_OF_INCOME, '%d/%m/%Y') AS DAY_OF_INCOME,
LAST_PAID_MONTHY,
ANNUITY_DATE,
IF(S.STATUS=1,'SI','NO') AS ACTIVE,
IF(MONTH(S.DAY_OF_INCOME) - MONTH(NOW())=0,'SI','NO') AS PENDING_PAYMENT_TUITIOM,
#mensualidad
IF(DATEDIFF(ADDDATE(S.DAY_OF_INCOME,((YEAR(NOW()) -YEAR(S.DAY_OF_INCOME))*365)),LAST_PAID_NO_FORMAT)>=-30 AND (DATEDIFF(ADDDATE(S.DAY_OF_INCOME,((YEAR(NOW()) -YEAR(S.DAY_OF_INCOME))*365)),LAST_PAID_NO_FORMAT)<=0),'NO','SI') AS BOOLEAN_MONTHY_PAYMENT,
#anualidad
IF(

(DATEDIFF(ADDDATE(S.DAY_OF_INCOME,((YEAR(NOW()) -YEAR(S.DAY_OF_INCOME))*365)),ANNUITY_DATE_NO_FORMAT)>=-335
 AND DATEDIFF(ADDDATE(S.DAY_OF_INCOME,((YEAR(NOW()) -YEAR(S.DAY_OF_INCOME))*365)),ANNUITY_DATE_NO_FORMAT)<=0
 AND (
 (SELECT COUNT(P.PAYDAY)
FROM PAYMENTS P , DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE D.ID_STUDENT=S.ID_STUDENT
AND P.ID_PAYMENT=D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('MAT2018','MATR00001') 
 ) >= (YEAR(NOW()) -YEAR(S.DAY_OF_INCOME)) )
 ) 
 
 
 ,'NO','SI'
 ) AS BOOLEAN_ANNUITY_DATE,
 
(YEAR(NOW()) -YEAR(S.DAY_OF_INCOME)) AS PAYMENTS_VS_YEARS, 
 (SELECT COUNT(P.PAYDAY)
FROM PAYMENTS P , DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE D.ID_STUDENT=S.ID_STUDENT
AND P.ID_PAYMENT=D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('MAT2018','MATR00001') 
 ) AS COUNT_PAYDAY,
DATEDIFF(ADDDATE(S.DAY_OF_INCOME,((YEAR(NOW()) -YEAR(S.DAY_OF_INCOME))*365)),LAST_PAID_NO_FORMAT) AS MONTHLY_PAYMENT,
DATEDIFF(ADDDATE(S.DAY_OF_INCOME,((YEAR(NOW()) -YEAR(S.DAY_OF_INCOME))*365)),ANNUITY_DATE_NO_FORMAT) AS ANNUITY
FROM STUDENT S LEFT OUTER JOIN (
SELECT DATE_FORMAT(MAX(P.PAYDAY), '%d/%m/%Y') AS  LAST_PAID_MONTHY,D.ID_STUDENT,MAX(P.PAYDAY) AS LAST_PAID_NO_FORMAT 
FROM PAYMENTS P, DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE P.ID_PAYMENT = D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('CLAS00001','CLAS00004','CLAS00005')
GROUP BY D.ID_STUDENT
) T ON (S.ID_STUDENT =T.ID_STUDENT ),
STUDENT S2 LEFT OUTER JOIN  (SELECT DATE_FORMAT(MAX(P.PAYDAY), '%d/%m/%Y') AS ANNUITY_DATE,D.ID_STUDENT ,MAX(P.PAYDAY) AS ANNUITY_DATE_NO_FORMAT
FROM PAYMENTS P, DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE P.ID_PAYMENT = D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('MAT2018','MATR00001')
GROUP BY D.ID_STUDENT) T2 ON (S2.ID_STUDENT =T2.ID_STUDENT )
WHERE S2.ID_STUDENT = S.ID_STUDENT
ORDER BY NAME;

SELECT P.PAYDAY,C.system_cod_description,D.* FROM PAYMENTS P , DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE D.ID_STUDENT=93
AND P.ID_PAYMENT=D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('CLAS00001','CLAS00004','CLAS00005')
;

#modificar day_of_income 292 to 
SELECT COUNT(1),(YEAR(NOW()) -YEAR(P.PAYDAY))
FROM PAYMENTS P , DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE D.ID_STUDENT=93
AND P.ID_PAYMENT=D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('MAT2018','MATR00001') 
;
*/
/*
SELECT P.PAYDAY,C.system_cod_description,D.* FROM PAYMENTS P , DETAIL_PAYMENTS D,SYSTEM_CODES C,PRODUCTS PROD
WHERE D.ID_STUDENT=93
AND P.ID_PAYMENT=D.ID_PAYMENT
AND D.ID_PRODUCT = PROD.ID_PRODUCT
AND PROD.TYPE = C.IDSYSTEM_CODES 
AND C.SYSTEM_CODE IN ('CLAS00001','CLAS00004','CLAS00005')
*/
/**
SELECT  
DATE_FORMAT(P.PAYDAY, '%d/%m/%Y') AS DAY_PAYMENT, 
P.NUMBER_OF_TRANSFER AS RECEIPT_NUMBER,
C.SYSTEM_COD_DESCRIPTION AS TYPE_PRODUCT, 
TP.SYSTEM_COD_DESCRIPTION AS TYPE_PAYMENT,
FORMAT((D.TAX+D.PAYMENT),2,'en_US') AS PAYMENT_VIEW,
SUM(D.TAX+D.PAYMENT) AS  PAYMENT
FROM STUDENT S, DETAIL_PAYMENTS D,PAYMENTS P, SYSTEM_CODES C,PRODUCTS PR,SYSTEM_CODES TP
WHERE S.ID_STUDENT = D.ID_STUDENT
AND D.ID_PAYMENT = P.ID_PAYMENT
AND D.ID_PRODUCT = PR.ID_PRODUCT 
AND PR.TYPE = C.IDSYSTEM_CODES
AND P.TYPE_PAYMENT = TP.IDSYSTEM_CODES
AND P.PAYDAY >= :parm1
AND P.PAYDAY <= :parm2 
AND (PR.ID_PRODUCT = :parm3 OR :parm3 =0)
AND (S.ID_STUDENT =:parm4)
**/
SELECT CONCAT(CONCAT(CONCAT(CONCAT(S.FIRST_NAME,','),S.SURNAME),','),S.SURNAME2) AS NAME, 
DOC_ID AS DOC_ID,
ADDRESS AS ADDRESS,
c.SYSTEM_COD_DESCRIPTION AS CATEGORY,
S.MAIL_CONTACT,
S.TELEPHONE_CONTACT
FROM STUDENT S,SYSTEM_CODES C
WHERE S.ID_STUDENT=93
AND S.CATEGORY = C.IDSYSTEM_CODES