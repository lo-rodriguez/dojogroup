select count(*) from Account a where a.mail_app = ''

#select * from Account

select  IF(count(1) > 0,1,2) from Account a where a.email = 'lro.1971@yahoo.com';

select * from system_codes;


select count(1) from student;
select * from student where id_student=17;
select * from student where id_student=23;

select role from user;

SELECT ADDDATE('2008-01-02', INTERVAL 31 DAY);

select * from student where day_of_income between amd  ;

 select day_of_income,ADDDATE(Now(), -3) from student;
 
 SELECT COUNT(1) FROM STUDENT WHERE  ADDDATE(DAY_OF_INCOME,(SELECT _VALUE FROM  SYSTEM_PARAMETERS WHERE _KEY ='DAYS_REG_NOTIFICATION')) > NOW(); 
 
 SELECT * FROM SYSTEM_PARAMETERS;
 
  SELECT VALUE FROM  SYSTEM_PARAMETERS WHERE KEY ='DAYS_REG_NOTIFICATION';
  
  
  UPDATE `dbbzk`.`SYSTEM_PARAMETERS` SET `_VALUE`='7' WHERE `PARAMETER _ID`='1';
  COMMIT;
  
  select DATE_FORMAT(birthdate,'%D%M%Y'),DATE_FORMAT(day_of_income,'%D%M%Y') from student where id_student=26;
  
  SELECT COUNT(1) FROM PRODUCTS WHERE DATE_FORMAT(EXPIRATION_DATE,'%Y%m%d')  ='29991231';
  
  DESCRIBE PAYMENTS;
  
  
  /**
  4
5
6
7
  */
  INSERT INTO PAYMENTS (ID_PRODUCT,ID_STUDENT,DATE_REGISTRATION,PAYDAY,USER_REGISTER,TOTAL_PAYMENT) VALUES (1,4,'2018-03-27',NOW(),'JVEGA',15);
  INSERT INTO PAYMENTS (ID_PRODUCT,ID_STUDENT,DATE_REGISTRATION,PAYDAY,USER_REGISTER,TOTAL_PAYMENT) VALUES (2,4,'2018-03-27',NOW(),'JVEGA',17.6);
  INSERT INTO PAYMENTS (ID_PRODUCT,ID_STUDENT,DATE_REGISTRATION,PAYDAY,USER_REGISTER,TOTAL_PAYMENT) VALUES (3,5,'2018-03-27',NOW(),'JVEGA',17.6);
  INSERT INTO PAYMENTS (ID_PRODUCT,ID_STUDENT,DATE_REGISTRATION,PAYDAY,USER_REGISTER,TOTAL_PAYMENT) VALUES (4,6,'2018-03-27',NOW(),'JVEGA',19.6);
  INSERT INTO PAYMENTS (ID_PRODUCT,ID_STUDENT,DATE_REGISTRATION,PAYDAY,USER_REGISTER,TOTAL_PAYMENT) VALUES (4,10,'2018-03-27',NOW(),'JVEGA',19.6);
  
  SELECT * FROM PAYMENTS;
  
  SELECT COUNT(1) FROM PAYMENTS WHERE  ADDDATE(PAYDAY,(SELECT _VALUE FROM  SYSTEM_PARAMETERS WHERE _KEY ='PAYMENT_NOTIFICATION')) > NOW(); 
  COMMIT;
  
  select * from student where  id_student=27 and is null photo;
  
  select * from system_codes;
  SELECT * FROM PRODUCTS;
  
  SELECT P.ID_PRODUCT,C.SYSTEM_COD_DESCRIPTION 
  FROM PRODUCTS P,SYSTEM_CODES C 
  WHERE DATE_FORMAT(P.EXPIRATION_DATE,'%Y%m%d')  ='29991231' AND C.IDSYSTEM_CODES=P.TYPE;
  
  SELECT LAST_DAY(NOW());
  
  SELECT COUNT(1) FROM CALENDAR_ACTIVITIES WHERE  ADDDATE(END_ACTIVITY_DAY,(SELECT _VALUE FROM  SYSTEM_PARAMETERS WHERE _KEY ='NOTIFICATION_ACTIVITIES')) > NOW() AND END_ACTIVITY_DAY <=LAST_DAY(NOW()); 
  #WHERE END_ACTIVITY_DAY >=NOW();
  #AND END_ACTIVITY_DAY <=LAST_DAY(NOW()); 
  #SELECT COUNT(1) FROM CALENDAR_ACTIVITIES WHERE  END_ACTIVITY_DAY >  ADDDATE(NOW(),(SELECT _VALUE FROM  SYSTEM_PARAMETERS WHERE _KEY ='NOTIFICATION_ACTIVITIES')) AND  END_ACTIVITY_DAY <=;
  
  SELECT COUNT(1) FROM CALENDAR_ACTIVITIES WHERE  END_ACTIVITY_DAY > NOW() AND  END_ACTIVITY_DAY <= ADDDATE(NOW(),(SELECT _VALUE FROM  SYSTEM_PARAMETERS WHERE _KEY ='NOTIFICATION_ACTIVITIES')) ;
  
  SELECT * FROM PAYMENTS;
  
  SELECT * FROM USER;
  DELETE FROM  PAYMENTS;
  COMMIT;
  
  SELECT COUNT(1) FROM STUDENT WHERE PHOTO IS NOT NULL;
  
  SELECT P.ID_PRODUCT,C.SYSTEM_COD_DESCRIPTION
   FROM PRODUCTS P,SYSTEM_CODES C
WHERE DATE_FORMAT(P.EXPIRATION_DATE,'%Y%m%d')  ='29991231' AND C.IDSYSTEM_CODES=P.TYPE;

select * from student;

select IF(count(1) > 0,1,0) from STUDENT a where a.MAIL_CONTACT = 'lro.1971@yahoo.com' AND a.ID_STUDENT <>99;

delete from student;
delete from payment;
commit;