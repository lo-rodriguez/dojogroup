USE dbbzk;
CREATE TABLE ACCESS_PROFILE (ID_ACCESS_PROFILE int(10) NOT NULL AUTO_INCREMENT, ID_PROFILE int(10) NOT NULL, ID_SYS_DASHBOARD int(10) NOT NULL, PRIMARY KEY (ID_ACCESS_PROFILE));
CREATE TABLE ACCOUNT (ID_ACCOUNT int(10) NOT NULL AUTO_INCREMENT, NIC varchar(20) comment 'NOMBRE DE PILA', ID_APP_PROFILE int(10) NOT NULL, PASSWORD VARCHAR(255) comment 'PASSWORD OF APP', FIRST_NAME varchar(40), LAST_NAME varchar(40), LAST_NAME2 varchar(40), EMAIL varchar(40), DISCHARGE_DATE DATE, DATE_OF_LOW date,  PRIMARY KEY (ID_ACCOUNT));
CREATE TABLE APP_PROFILE (NAME varchar(80), DESCRIPTION varchar(255), ID_PROFILE int(10) NOT NULL AUTO_INCREMENT, PRIMARY KEY (ID_PROFILE)) comment='APP ROLES';
CREATE TABLE STUDENT (FIRST_NAME varchar(40) NOT NULL, LAST_NAME varchar(40) NOT NULL, LAST_NAME2 varchar(40) NOT NULL, MAIL_CONTACT varchar(60), SEX smallint(1), WEIGHT int(3) NOT NULL, BIRTHDATE date NOT NULL, DAY_OF_INCOME date, DATE_OF_LOW date NOT NULL, STATUS smallint(1), CATEGORY int(3), TELEPHONE_CONTACT varchar(30), ADDRESS varchar(424), DOC_ID varchar(60) NOT NULL, ID_STUDENT int(10) NOT NULL AUTO_INCREMENT, ID_TEACHER int(10) NOT NULL, PRIMARY KEY (ID_STUDENT), UNIQUE INDEX (ID_STUDENT));
CREATE TABLE SYSTEM_DASHBOARD (ID_SYS_DASHBOARD int(10) NOT NULL AUTO_INCREMENT, NAME varchar(40), DESCRIPTION varchar(255), PRIMARY KEY (ID_SYS_DASHBOARD));
CREATE TABLE TEACHER (FIRST_NAME varchar(40) NOT NULL, LAST_NAME varchar(40) NOT NULL, LAST_NAME2 varchar(40), CATEGORY int(3) NOT NULL, TELEPHONE_CONTACT varchar(30), MAIL varchar(50), DOC_ID varchar(60) NOT NULL, ID_TEACHER int(10) NOT NULL AUTO_INCREMENT, PRIMARY KEY (ID_TEACHER), UNIQUE INDEX (ID_TEACHER));

/*ALTER TABLE `STUDENT
` ADD INDEX `FKSTUDENT
` (ID_TEACHER), ADD CONSTRAINT `FKSTUDENT
` FOREIGN KEY (ID_TEACHER) REFERENCES TEACHER (ID_TEACHER);*/
ALTER TABLE STUDENT ADD INDEX `INDEX_TEACHER`(`ID_TEACHER`); 


ALTER TABLE ACCESS_PROFILE ADD INDEX ACCES_PROFILE_VS_DASHBOARD (ID_SYS_DASHBOARD), ADD CONSTRAINT ACCES_PROFILE_VS_DASHBOARD FOREIGN KEY (ID_SYS_DASHBOARD) REFERENCES SYSTEM_DASHBOARD (ID_SYS_DASHBOARD);
ALTER TABLE ACCESS_PROFILE ADD INDEX APP_PROFILE_VS_ACCESS (ID_PROFILE), ADD CONSTRAINT APP_PROFILE_VS_ACCESS FOREIGN KEY (ID_PROFILE) REFERENCES APP_PROFILE (ID_PROFILE);
ALTER TABLE ACCOUNT ADD INDEX USER_VS_APP_PROFILE (ID_APP_PROFILE), ADD CONSTRAINT USER_VS_APP_PROFILE FOREIGN KEY (ID_APP_PROFILE) REFERENCES APP_PROFILE (ID_PROFILE);

# COMENTADO PORQUE SE HIZO DROP A LA TABLA
#ALTER TABLE ACCOUNT MODIFY DISCHARGE_DATE date;
#ALTER TABLE ACCOUNT MODIFY MAIL_APP VARCHAR(40);
#ALTER TABLE ACCOUNT MODIFY ID_ACCOUNT  AUTO_INCREMENT;
#ALTER TABLE ACCOUNT AUTO_INCREMENT = 100000;


show tables;
describe ACCOUNT;
describe SYSTEM_DASHBOARD;
describe ACCESS_PROFILE;
drop table ACCOUNT;
#select * from access_profile;
#ALTER TABLE ACCOUNT DROP APP_PROFILEID_PROFILE , ADD APP_PROFILE_ID int(10) NOT NULL;

ALTER TABLE ACCOUNT DROP APP_PROFILE_ID;

SELECT * FROM USER;

DESCRIBE USER;
#ALTER TABLE t1 ADD x INT;
ALTER TABLE USER ADD FIRST_NAME VARCHAR(40);
ALTER TABLE USER ADD LAST_NAME VARCHAR(40);
ALTER TABLE USER ADD DAY_GIVEN_LOW DATE;

-- tabla de codigos
CREATE TABLE `dbbzk`.`system_codes` (
  `idsystem_codes` int(10) NOT NULL AUTO_INCREMENT,
  `system_code` VARCHAR(10) NOT NULL,
  `system_cod_description` VARCHAR(80) NULL,
  `system_cod_group` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idsystem_codes`))
COMMENT = 'system codes';
-- AGREGRAR LAS COLUMNAS RESTANTE DE LA TABLA DE ESTUDIANTE
ALTER TABLE STUDENT ADD SURNAME VARCHAR(40) NOT NULL;
ALTER TABLE STUDENT ADD SURNAME2 VARCHAR(40) NOT NULL;
ALTER TABLE STUDENT ADD CELLPHONE VARCHAR(20) NOT NULL;
ALTER TABLE STUDENT ADD CELLPHONES2 VARCHAR(20);
ALTER TABLE STUDENT ADD TELEPHONE_CONTACT2  VARCHAR(20);
ALTER TABLE STUDENT ADD BLOOD_TYPE INT(5) NOT NULL;
ALTER TABLE STUDENT ADD ALLERGY  VARCHAR(40) NOT NULL;
ALTER TABLE STUDENT ADD SIZE INT(5) ;
ALTER TABLE STUDENT ADD HEIGHT INT(5) ;
ALTER TABLE STUDENT ADD PHOTO BLOB ; 
ALTER TABLE STUDENT ADD NAME_OF_GUARDIAN VARCHAR(40) ;
ALTER TABLE STUDENT ADD GUARDIAN_LAST_NAME VARCHAR(40) ;
ALTER TABLE STUDENT ADD MAIL_GUARDIAN   VARCHAR(60); 
ALTER TABLE STUDENT ADD PHONE_GUARDIAN VARCHAR(20) ;
ALTER TABLE STUDENT ADD PHONE_GUARDIAN2 VARCHAR(20) ;
ALTER TABLE STUDENT ADD DOC_ID_GUARDIAN2 VARCHAR(60) ;

ALTER TABLE STUDENT DROP LAST_NAME2;
ALTER TABLE STUDENT DROP DOC_ID_GUARDIAN2;
ALTER TABLE STUDENT  ADD DOC_ID_GUARDIAN  VARCHAR(60) ;
ALTER TABLE STUDENT DROP CELLPHONES2;
ALTER TABLE STUDENT ADD CELLPHONE2 VARCHAR(20);
ALTER TABLE STUDENT MODIFY HEIGHT FLOAT(5,2) ;
ALTER TABLE STUDENT MODIFY  WEIGHT FLOAT(5,2) ;
ALTER TABLE STUDENT DROP DATE_OF_LOW;
ALTER TABLE STUDENT ADD DATE_OF_LOW DATE;
ALTER TABLE STUDENT DROP LAST_NAME;
ALTER TABLE STUDENT ADD LAST_NAME VARCHAR(20);
describe student;
#product
CREATE TABLE `dbbzk`.`products` (
  `id_product` int(10) NOT NULL AUTO_INCREMENT,
  `type` INT NOT NULL,
  `date_registration` DATE NOT NULL,
  `expiration date` DATE NULL,
  `user_register` VARCHAR(40) NULL,
  `user_expiration` VARCHAR(40) NULL,
  `unit_price` DECIMAL(7,2) NOT NULL,
  `tax` DECIMAL(7,2) NULL,
  PRIMARY KEY (`id_product`))
COMMENT = 'table to register the products';

#payments
CREATE TABLE `dbbzk`.`payments` (
  `id_payment` int(10) NOT NULL AUTO_INCREMENT,
  `id_product` INT(10) NOT NULL,
  `id_student` INT(10) NOT NULL,
  `date_registration` DATETIME NOT NULL,
  `payday` DATETIME NULL,
  `user_register` VARCHAR(40) NOT NULL,
  `total_payment` DECIMAL(7,2) NOT NULL,
  `total_tax` DECIMAL(7,2) NULL,
  `NUMBER_OF_TRANSFER` VARCHAR(40)  NULL,
  `NAME_OF_BNK` VARCHAR(40)  NULL,
  PRIMARY KEY (`id_payment`))
COMMENT = 'table to register the payments';
describe payments;

CREATE TABLE `dbbzk`.`calendar_activities` (
  `id_Calendar_activities` INT NOT NULL AUTO_INCREMENT,
  `activity_name` VARCHAR(40) NOT NULL,
  `start_day_activity` DATE NOT NULL,
  `end_ activity_day` DATE NOT NULL,
  `activity_star_ time` INT(3) NULL,
  `activity_end_time` INT(3) NULL,
  `type` INT(1) NOT NULL COMMENT 'Type of activity (internal 1/external 2)',
  `publishing_social_networks` INT NOT NULL DEFAULT 0 COMMENT 'Publish on social networks. Not 0, if 1',
  `description` VARCHAR(220) NULL,
  `notice_compliance` INT(2) NULL,
  `users_ notify` VARCHAR(400) NULL COMMENT 'usuarios a notificar, los usuarios son email que estan separados por coma.Ejemplo:user@mail.com;user1@mail.com',
  PRIMARY KEY (`id_Calendar_activities`))
COMMENT = 'BZK Activities Calendars';

describe calendar_activities;

ALTER TABLE `dbbzk`.`payments` 
CHANGE COLUMN `id_payment` `id_payment` INT(10) NOT NULL ,
CHANGE COLUMN `id_product` `id_product` INT(10) NOT NULL AUTO_INCREMENT ,
ADD INDEX `FK_PRODUCTS_idx` (`id_product` ASC);
ALTER TABLE `dbbzk`.`payments` 
ADD CONSTRAINT `FK_PRODUCTS`
  FOREIGN KEY (`id_product`)
  REFERENCES `dbbzk`.`products` (`id_product`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `dbbzk`.`payments` 
ADD INDEX `FK_STUDENT_idx` (`id_student` ASC);
ALTER TABLE `dbbzk`.`payments` 
ADD CONSTRAINT `FK_STUDENT`
  FOREIGN KEY (`id_student`)
  REFERENCES `dbbzk`.`student` (`ID_STUDENT`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `dbbzk`.`user` 
ADD COLUMN `system_signature_name` VARCHAR(45) NULL AFTER `date_registration`;
ALTER TABLE `dbbzk`.`user` 
CHANGE COLUMN `system_signature_name` `system_signature_name` VARCHAR(40) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL ;


ALTER TABLE `dbbzk`.`user` 
CHANGE COLUMN `system_signature_name` `system_signature_name` VARCHAR(40) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL DEFAULT ' ' ;
ALTER TABLE `dbbzk`.`user` 
ADD INDEX `INDEX_SIGNATURE` (`system_signature_name` ASC);

ALTER TABLE `dbbzk`.`products` 
CHANGE COLUMN `tax` `tax` DECIMAL(7,2) NOT NULL DEFAULT 0 ;
ALTER TABLE `dbbzk`.`payments` 
CHANGE COLUMN `total_tax` `total_tax` DECIMAL(7,2) NOT NULL DEFAULT 0 ;
--
ALTER TABLE `dbbzk`.`student` 
ADD COLUMN `ID_EMPLOYMENT` INT(5) NULL ;
-------------------------------
-- NUEVA TABLA DE DETALLE DEL PAGO
CREATE TABLE `detail_payments` (
	`id_detail_payments` INT(10) NULL,
	`id_product` INT(10) NULL,
	`tax` DECIMAL(7,2) NOT NULL,
	`id_student` INT(10) NOT NULL,
	`discount` SMALLINT(1) NOT NULL DEFAULT '1' COMMENT '1 has no discount / 2 has a discount',
	`total_paid_producto` DECIMAL(7,2) NULL
)
COMMENT='Detail of payments made in the BZK system'
COLLATE='latin1_bin'
ENGINE=InnoDB
;
-- MODIFICACION DE LA TABLA SEGUN LO PEDIDO
ALTER TABLE `payments`
	ADD COLUMN `commentary` VARCHAR(250) NOT NULL AFTER `date_registration`,
	ADD COLUMN `subtotal` DECIMAL(7,2) NOT NULL DEFAULT '0' AFTER `total_tax`;
	ALTER TABLE `payments`
-- MODIFICACIONES DEL 21/06/2018	
	DROP FOREIGN KEY `FK_PRODUCTS`,
	DROP FOREIGN KEY `FK_STUDENT`;
---modificacion de la tabla de detalle del pago
ALTER TABLE `detail_payments`
	ADD COLUMN `comment` CHAR(80) NULL AFTER `total_paid_producto`;	
-- SE AGREGA LA LLAVE FORANEA DE PAGOS A LA DETALLE
ALTER TABLE `detail_payments`
	ADD COLUMN `comment` VARCHAR(80) NULL DEFAULT NULL AFTER `total_paid_producto`,
	ADD COLUMN `id_payment` INT NULL DEFAULT NULL AFTER `comment`,
	ADD CONSTRAINT `FK_PAYMENT` FOREIGN KEY (`id_payment`) REFERENCES `payments` (`id_payment`);
	ALTER TABLE `detail_payments`
	CHANGE COLUMN `total_paid_producto` `payment` DECIMAL(7,2) NULL DEFAULT NULL AFTER `discount
-- MODIFICACIONES DE LA TABLA PAYMENT 21/06/2018
ALTER TABLE `payments`
	ALTER `id_student` DROP DEFAULT,
	ALTER `commentary` DROP DEFAULT,
	ALTER `payday` DROP DEFAULT,
	ALTER `total_payment` DROP DEFAULT,
	ALTER `type_payment` DROP DEFAULT;
ALTER TABLE `payments`
	CHANGE COLUMN `id_product` `id_product` INT(10) NULL AUTO_INCREMENT AFTER `id_payment`,
	CHANGE COLUMN `id_student` `id_student` INT(10) NULL AFTER `id_product`,
	CHANGE COLUMN `commentary` `commentary` VARCHAR(250) NULL COLLATE 'latin1_bin' AFTER `date_registration`,
	CHANGE COLUMN `payday` `payday` DATETIME NOT NULL AFTER `commentary`,
	CHANGE COLUMN `total_payment` `total_payment` DECIMAL(7,2) NULL AFTER `user_register`,
	CHANGE COLUMN `total_tax` `total_tax` DECIMAL(7,2) NULL DEFAULT '0' AFTER `total_payment`,
	CHANGE COLUMN `type_payment` `type_payment` INT(11) NOT NULL AFTER `name_of_bank`;	
ALTER TABLE `payments`
	DROP INDEX `FK_PRODUCTS_idx`,
	DROP INDEX `FK_STUDENT_idx`;
------------------------------------------------
ALTER TABLE `detail_payments`
	ALTER `id_detail_payments` DROP DEFAULT,
	ALTER `id_product` DROP DEFAULT,
	ALTER `payment` DROP DEFAULT,
	ALTER `comment` DROP DEFAULT,
	ALTER `id_payment` DROP DEFAULT;
ALTER TABLE `detail_payments`
	CHANGE COLUMN `id_detail_payments` `id_detail_payments` INT(10) NOT NULL FIRST,
	CHANGE COLUMN `id_product` `id_product` INT(10) NOT NULL AFTER `id_detail_payments`,
	CHANGE COLUMN `discount` `discount` SMALLINT(1) NULL DEFAULT NULL AFTER `id_student`,
	CHANGE COLUMN `payment` `payment` DECIMAL(7,2) NOT NULL AFTER `discount`,
	CHANGE COLUMN `comment` `comment` VARCHAR(80) NULL COLLATE 'latin1_bin' AFTER `payment`,
	CHANGE COLUMN `id_payment` `id_payment` INT(11) NOT NULL AFTER `comment`;
--------------------------------------------
-- TABLA RESULTANTES
CREATE TABLE `detail_payments` (
	`id_detail_payments` INT(10) NOT NULL,
	`id_product` INT(10) NOT NULL,
	`tax` DECIMAL(7,2) NOT NULL,
	`id_student` INT(10) NOT NULL,
	`discount` SMALLINT(1) NULL DEFAULT NULL,
	`payment` DECIMAL(7,2) NOT NULL,
	`comment` VARCHAR(80) NULL DEFAULT NULL COLLATE 'latin1_bin',
	`id_payment` INT(11) NOT NULL,
	INDEX `FK_PAYMENT` (`id_payment`),
	CONSTRAINT `FK_PAYMENT` FOREIGN KEY (`id_payment`) REFERENCES `payments` (`id_payment`)
)
COMMENT='Detail of payments made in the BZK system'
COLLATE='latin1_bin'
ENGINE=InnoDB
;
---
CREATE TABLE `payments` (
	`id_payment` INT(10) NOT NULL,
	`id_product` INT(10) NOT NULL AUTO_INCREMENT,
	`id_student` INT(10) NULL DEFAULT NULL,
	`date_registration` DATETIME NULL DEFAULT NULL,
	`commentary` VARCHAR(250) NULL DEFAULT NULL COLLATE 'latin1_bin',
	`payday` DATETIME NOT NULL,
	`user_register` VARCHAR(40) NOT NULL COLLATE 'latin1_bin',
	`total_payment` DECIMAL(7,2) NOT NULL,
	`total_tax` DECIMAL(7,2) NOT NULL DEFAULT '0',
	`subtotal` DECIMAL(7,2) NULL DEFAULT '0',
	`number_of_transfer` VARCHAR(40) NULL DEFAULT NULL COLLATE 'latin1_bin',
	`name_of_bank` VARCHAR(40) NULL DEFAULT NULL COLLATE 'latin1_bin',
	`type_payment` INT(11) NOT NULL,
	PRIMARY KEY (`id_payment`),
	INDEX `FK_PRODUCTS_idx` (`id_product`),
	INDEX `FK_STUDENT_idx` (`id_student`),
	INDEX `FK_USER_idx` (`user_register`)
)
COMMENT='table to register the payments'
COLLATE='latin1_bin'
ENGINE=InnoDB
AUTO_INCREMENT=6
;
--------------------------------------------------------------
CREATE TABLE `detail_payments` (
	`id_detail_payments` INT(10) NOT NULL,
	`id_product` INT(10) NOT NULL,
	`tax` DECIMAL(7,2) NOT NULL,
	`id_student` INT(10) NOT NULL,
	`discount` SMALLINT(1) NULL DEFAULT NULL,
	`payment` DECIMAL(7,2) NOT NULL,
	`comment` VARCHAR(80) NULL DEFAULT NULL COLLATE 'latin1_bin',
	`id_payment` INT(11) NOT NULL,
	PRIMARY KEY (`id_detail_payments`),
	INDEX `FK_PAYMENT` (`id_payment`),
	INDEX `FK_PRODUC` (`id_product`),
	INDEX `FK_STUDENT` (`id_student`),
	CONSTRAINT `FK_PAYMENT` FOREIGN KEY (`id_payment`) REFERENCES `payments` (`id_payment`),
	CONSTRAINT `FK_PRODUC` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
	CONSTRAINT `FK_STUDENT` FOREIGN KEY (`id_student`) REFERENCES `student` (`ID_STUDENT`)
)
COMMENT='Detail of payments made in the BZK system'
COLLATE='latin1_bin'
ENGINE=InnoDB
;
	
	
	
	
