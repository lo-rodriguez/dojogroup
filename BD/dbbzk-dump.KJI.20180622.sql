-- MySQL dump 10.16  Distrib 10.2.12-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: dbbzk
-- ------------------------------------------------------
-- Server version	10.2.12-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `access_profile`
--

DROP TABLE IF EXISTS `access_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_profile` (
  `ID_ACCESS_PROFILE` int(10) NOT NULL AUTO_INCREMENT,
  `ID_PROFILE` int(10) NOT NULL,
  `ID_SYS_DASHBOARD` int(10) NOT NULL,
  PRIMARY KEY (`ID_ACCESS_PROFILE`),
  KEY `ACCES_PROFILE_VS_DASHBOARD` (`ID_SYS_DASHBOARD`),
  KEY `APP_PROFILE_VS_ACCESS` (`ID_PROFILE`),
  CONSTRAINT `ACCES_PROFILE_VS_DASHBOARD` FOREIGN KEY (`ID_SYS_DASHBOARD`) REFERENCES `system_dashboard` (`ID_SYS_DASHBOARD`),
  CONSTRAINT `APP_PROFILE_VS_ACCESS` FOREIGN KEY (`ID_PROFILE`) REFERENCES `app_profile` (`ID_PROFILE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_profile`
--

LOCK TABLES `access_profile` WRITE;
/*!40000 ALTER TABLE `access_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id_account` bigint(20) NOT NULL,
  `date_of_low` datetime DEFAULT NULL,
  `discharge_date` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `first_name` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `id_app_profile` bigint(20) DEFAULT NULL,
  `last_name` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `last_name2` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `nic` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `password` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  PRIMARY KEY (`id_account`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_profile`
--

DROP TABLE IF EXISTS `app_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_profile` (
  `NAME` varchar(80) COLLATE latin1_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `ID_PROFILE` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID_PROFILE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_bin COMMENT='APP ROLES';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_profile`
--

LOCK TABLES `app_profile` WRITE;
/*!40000 ALTER TABLE `app_profile` DISABLE KEYS */;
INSERT INTO `app_profile` VALUES ('OPERATOR','OPERADOR/SECRETARIA/EMPLEADO',1),('STUDENTS','ESTUDIANTE - NO IMPLEMENTADO',2),('TEACHERS','MAESTRO DENTRO DEL DOJO',3),('ADMINISTRATORS','ADMINISTRADOR DE LA APP -TOTAL CONTROL',4),('CONSULTANT_AGENT','AGENTE DE CONSULTA - COMMUNITY MANAGER',5);
/*!40000 ALTER TABLE `app_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar_activities`
--

DROP TABLE IF EXISTS `calendar_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_activities` (
  `id_Calendar_activity` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(40) COLLATE latin1_bin NOT NULL,
  `start_day_activity` date NOT NULL,
  `end_activity_day` date NOT NULL,
  `activity_star_time` int(3) DEFAULT NULL,
  `activity_end_time` int(3) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT 'Type of activity (internal 1/external 2)',
  `publishing_social_networks` int(1) DEFAULT 1,
  `description` varchar(220) COLLATE latin1_bin DEFAULT NULL,
  `notice_compliance` int(2) DEFAULT 1,
  PRIMARY KEY (`id_Calendar_activity`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=latin1 COLLATE=latin1_bin COMMENT='BZK Activities Calendars';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar_activities`
--

--LOCK TABLES `calendar_activities` WRITE;
/*!40000 ALTER TABLE `calendar_activities` DISABLE KEYS */;
--INSERT INTO `calendar_activities` VALUES (00000000001,'ACTIVIDAD BZK ','2018-04-10','2018-04-10',15,17,1,0,NULL,NULL),(00000000002,'ACTIVIDAD BZK002 ','2018-04-16','2018-04-16',13,17,1,0,NULL,NULL),(00000000003,'ACTIVIDAD BZK003 ','2018-04-19','2018-04-19',13,17,1,0,NULL,NULL),(00000000004,'COMPETENCIA DISTRITO PMA ','2018-04-24','2018-04-24',8,17,2,1,NULL,NULL),(00000000005,'COMPETENCIA NACIONAL ','2018-04-27','2018-04-29',8,17,2,1,NULL,NULL),(00000000102,'evento 11','2018-06-10','2018-06-11',NULL,NULL,NULL,NULL,NULL,NULL),(00000000103,'EVENTO 20180613','2018-06-12','2018-06-13',NULL,NULL,NULL,NULL,NULL,NULL),(00000000104,'EVE 20180614','2018-06-13','2018-06-14',NULL,NULL,NULL,NULL,NULL,NULL),(00000000105,'EVE20180614','2018-06-13','2018-06-14',NULL,NULL,NULL,NULL,NULL,NULL),(00000000106,'EVE20180615','2018-06-14','2018-06-15',NULL,NULL,NULL,NULL,NULL,NULL),(00000000107,'EVEN20180621','2018-06-20','2018-06-21',NULL,NULL,NULL,NULL,NULL,NULL),(00000000205,'Ver el partido de Panama vs Inglaterra','2018-06-30','2018-07-01',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `calendar_activities` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `detail_payments`
--
--desde aqui se ejecuta esta sentencia
DROP TABLE IF EXISTS `detail_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_payments` (
  `id_detail_payments` int(10) NOT NULL  AUTO_INCREMENT,
  `id_product` int(10) NOT NULL,
  `tax` decimal(7,2) NOT NULL,
  `id_student` int(10) NOT NULL,
  `discount` smallint(1) DEFAULT NULL,
  `payment` decimal(7,2) NOT NULL,
  `comment` varchar(80) COLLATE latin1_bin DEFAULT NULL,
  `id_payment` int(11) NOT NULL,
  PRIMARY KEY (`id_detail_payments`),
  KEY `FK_PAYMENT` (`id_payment`),
  KEY `FK_PRODUC` (`id_product`),
  KEY `FK_STUDENT` (`id_student`),
  CONSTRAINT `FK_PAYMENT` FOREIGN KEY (`id_payment`) REFERENCES `payments` (`id_payment`),
  CONSTRAINT `FK_PRODUC` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`id_student`) REFERENCES `student` (`ID_STUDENT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin COMMENT='Detail of payments made in the BZK system' AUTO_INCREMENT=2000000;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_payments`
--

--LOCK TABLES `detail_payments` WRITE;
/*!40000 ALTER TABLE `detail_payments` DISABLE KEYS */;
--INSERT INTO `detail_payments` VALUES (119,2,3.15,89,0,45.00,'Un comentario',118),(120,2,3.15,90,0,45.00,'Un comentario2',118),(121,1,0.00,90,0,50.00,'Un comentario23',118),(124,1,4.00,89,0,3.00,'mi comentario',123),(126,1,4.00,89,0,3.00,'mi comentario',125),(137,1,4.00,90,0,23.00,'comentario',136),(138,2,0.00,90,0,0.00,'comentario',136),(141,1,0.00,89,0,50.00,'Un comentario',140),(142,3,3.50,89,0,50.00,'Un comentario',140),(143,3,3.15,90,0,45.00,'Rebaja por ser el hermano.',140),(145,1,0.00,89,0,50.00,'Comentario del pago',144),(147,1,0.00,89,0,50.00,'Comentario del pago',146),(149,1,0.00,89,0,50.00,'Comentario del pago',148),(151,1,0.00,89,0,50.00,'Este un gran comentario ',150),(153,1,0.00,89,0,50.00,'Este un gran comentario ',152),(155,1,0.00,89,0,50.00,'Mi primer pago serio',154),(157,2,3.15,89,0,45.00,'comentario',156),(159,1,0.00,92,0,50.00,'Pago del mes corriente.',158),(160,2,3.15,92,0,45.00,'Uniforme de la temporada',158),(161,1,0.00,92,0,45.00,'Pago del mes siguiente',158),(163,3,3.50,89,0,50.00,'El pago esta caro pero ni modo',162),(165,1,0.00,89,0,50.00,'Este un gran comentario ',164),(167,2,3.15,89,0,45.00,'Este un gran comentario ',166),(169,3,3.50,99,0,50.00,'Otro pago mas',168),(171,1,0.00,89,0,50.00,'Tengo un comentario',170),(172,2,3.15,89,0,45.00,'Tengo un comentario',170),(173,2,3.15,95,0,45.00,'Tengo un comentario',170),(176,1,0.00,89,0,50.00,'Este un gran comentario ',175),(177,2,3.15,89,0,45.00,'Este un gran comentario ',175),(178,2,3.15,92,0,45.00,'Este un gran comentario ',175),(180,1,1.02,89,0,50.00,'Este un gran comentario ',179),(181,1,1.02,92,0,50.00,'Este un gran comentario ',179),(182,1,1.02,99,0,50.00,'Este un gran comentario ',179),(183,2,3.15,99,0,45.00,'Este un gran comentario ',179),(184,2,3.15,89,0,45.00,'Este un gran comentario ',179),(185,2,3.15,95,0,45.00,'Este un gran comentario ',179),(186,1,0.00,95,0,50.00,'Este un gran comentario ',179),(187,1,0.00,98,0,50.00,'Este un gran comentario ',179),(189,1,0.00,89,0,50.00,'Este un gran comentario ',188),(190,1,0.00,90,0,50.00,'Este un gran comentario ',188),(192,1,0.00,89,0,50.00,'Gran comentario',191),(193,1,0.00,90,0,50.00,'Gran comentario',191),(194,1,0.00,92,0,50.00,'Gran comentario',191),(196,1,0.00,89,0,50.00,'Este un gran comentario ',195),(197,1,0.00,90,0,50.00,'Este un gran comentario ',195),(198,1,0.00,92,0,50.00,'Este un gran comentario ',195),(200,1,0.00,89,0,50.00,'Se pago la matricula de junio',199),(201,1,0.00,90,0,50.00,'Se pago la matricula de junio',199),(202,3,3.50,90,0,50.00,'Se pago la matricula de junio',199),(203,4,4.20,98,0,60.00,'Se pago la matricula de junio',199),(204,3,3.50,98,0,50.00,'Otro pago mas',199),(207,1,0.00,89,0,50.00,'Otro pago mas',206);
/*!40000 ALTER TABLE `detail_payments` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (209),(209);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `id_payment` int(10) NOT NULL  UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
  `date_registration` datetime DEFAULT NULL,
  `payday` datetime NOT NULL,
  `user_register` varchar(40) COLLATE latin1_bin NOT NULL,
  `total_payment` decimal(7,2) NOT NULL,
  `total_tax` decimal(7,2) NOT NULL DEFAULT 0.00,
  `subtotal` decimal(7,2) DEFAULT 0.00,
  `number_of_transfer` varchar(40) COLLATE latin1_bin DEFAULT NULL,
  `name_of_bank` varchar(40) COLLATE latin1_bin DEFAULT NULL,
  `type_payment` int(11) NOT NULL,
  PRIMARY KEY (`id_payment`),
  KEY `FK_USER_idx` (`user_register`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin COMMENT='table to register the payments' AUTO_INCREMENT=1000000;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

--LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
--INSERT INTO `payments` VALUES (91,'2018-05-29 14:23:14','2018-05-29 14:23:14','jvega@hotmail.com',1.33,0.00,0.00,'','',252),(94,'2018-06-01 21:18:02','2018-06-01 21:18:02','jvega@hotmail.com',50.00,0.00,0.00,'','',252),(96,'2018-06-01 22:12:42','2018-06-01 22:12:42','jvega@hotmail.com',400.00,7.00,0.00,'','',252),(97,'2018-06-01 22:13:07','2018-06-01 22:13:07','jvega@hotmail.com',50.00,0.00,0.00,'','',252),(110,'2018-06-21 12:37:00','2018-06-21 00:00:00','jvega@hotmail.com',122.00,5.04,0.00,NULL,NULL,252),(114,'2018-06-21 12:48:15','2018-06-21 00:00:00','jvega@hotmail.com',114.00,5.04,0.00,NULL,NULL,252),(118,'2018-06-21 13:55:40','2018-06-21 00:00:00','jvega@hotmail.com',140.00,6.30,0.00,NULL,NULL,252),(122,'2018-06-21 15:54:02','2018-06-21 00:00:00','jvega@hotmail.com',95.00,9.45,0.00,NULL,NULL,253),(123,'2018-06-21 16:17:14','2018-06-21 00:00:00','jvega@hotmail.com',3.00,4.00,0.00,NULL,NULL,252),(125,'2018-06-21 16:19:10','2018-06-21 00:00:00','jvega@hotmail.com',3.00,4.00,0.00,NULL,NULL,252),(127,'2018-06-21 16:33:15','2018-06-21 00:00:00','jvega@hotmail.com',50.00,51.00,0.00,NULL,NULL,252),(128,'2018-06-21 16:35:37','2018-06-21 00:00:00','jvega@hotmail.com',50.00,51.00,0.00,NULL,NULL,252),(129,'2018-06-21 16:36:35','2018-06-21 00:00:00','jvega@hotmail.com',50.00,51.00,0.00,NULL,NULL,252),(130,'2018-06-21 16:50:38','2018-06-21 00:00:00','jvega@hotmail.com',50.00,51.00,0.00,NULL,NULL,252),(131,'2018-06-21 17:08:41','2018-06-21 00:00:00','jvega@hotmail.com',140.00,78.00,0.00,NULL,NULL,252),(132,'2018-06-21 17:11:32','2018-06-21 00:00:00','jvega@hotmail.com',100.00,56.00,0.00,NULL,NULL,252),(133,'2018-06-21 17:27:13','2018-06-21 00:00:00','jvega@hotmail.com',50.00,51.00,0.00,NULL,NULL,252),(134,'2018-06-21 17:27:40','2018-06-21 00:00:00','jvega@hotmail.com',50.00,51.00,0.00,NULL,NULL,252),(135,'2018-06-21 22:16:18','2018-06-21 00:00:00','jvega@hotmail.com',100.00,55.00,0.00,NULL,NULL,252),(136,'2018-06-21 22:17:25','2018-06-21 00:00:00','jvega@hotmail.com',23.00,4.00,0.00,NULL,NULL,252),(139,'2018-06-21 22:36:40','2018-06-21 00:00:00','jvega@hotmail.com',50.00,0.00,0.00,NULL,NULL,252),(140,'2018-06-22 00:11:38','2018-06-22 00:00:00','jvega@hotmail.com',151.65,6.65,145.00,NULL,NULL,252),(144,'2018-06-22 00:15:32','2018-06-22 00:00:00','jvega@hotmail.com',50.00,0.00,50.00,NULL,NULL,252),(146,'2018-06-22 00:19:36','2018-06-22 00:00:00','jvega@hotmail.com',50.00,0.00,50.00,NULL,NULL,252),(148,'2018-06-22 00:22:33','2018-06-22 00:00:00','jvega@hotmail.com',50.00,0.00,50.00,NULL,NULL,252),(150,'2018-06-22 00:34:03','2018-06-22 00:00:00','jvega@hotmail.com',50.00,0.00,50.00,NULL,NULL,252),(152,'2018-06-22 00:40:11','2018-06-22 00:00:00','jvega@hotmail.com',50.00,0.00,50.00,NULL,NULL,252),(154,'2018-06-22 00:45:37','2018-06-22 00:00:00','jvega@hotmail.com',50.00,0.00,50.00,NULL,NULL,252),(156,'2018-06-22 00:57:48','2018-06-22 00:00:00','jvega@hotmail.com',48.15,3.15,45.00,NULL,NULL,253),(158,'2018-06-22 01:00:27','2018-06-22 00:00:00','jvega@hotmail.com',143.15,3.15,140.00,NULL,NULL,254),(162,'2018-06-22 01:10:04','2018-06-22 00:00:00','jvega@hotmail.com',53.50,3.50,50.00,NULL,NULL,253),(164,'2018-06-22 01:16:52','2018-06-22 00:00:00','jvega@hotmail.com',50.00,0.00,50.00,NULL,NULL,252),(166,'2018-06-22 01:19:01','2018-06-22 00:00:00','jvega@hotmail.com',48.15,3.15,45.00,NULL,NULL,252),(168,'2018-06-22 01:21:37','2018-06-22 00:00:00','jvega@hotmail.com',53.50,3.50,50.00,NULL,NULL,253),(170,'2018-06-22 11:12:47','2018-06-22 00:00:00','jvega@hotmail.com',146.30,6.30,140.00,NULL,NULL,252),(174,'2018-06-22 12:35:45','2018-06-22 00:00:00','jvega@hotmail.com',0.00,0.00,0.00,NULL,NULL,252),(175,'2018-06-22 12:36:52','2018-06-22 00:00:00','jvega@hotmail.com',146.30,6.30,140.00,NULL,NULL,252),(179,'2018-06-22 12:45:19','2018-06-22 00:00:00','jvega@hotmail.com',397.51,12.51,385.00,NULL,NULL,252),(188,'2018-06-22 12:51:40','2018-06-22 00:00:00','jvega@hotmail.com',100.00,0.00,100.00,NULL,NULL,252),(191,'2018-06-22 12:54:01','2018-06-22 00:00:00','jvega@hotmail.com',150.00,0.00,150.00,NULL,NULL,252),(195,'2018-06-22 12:56:32','2018-06-22 00:00:00','jvega@hotmail.com',150.00,0.00,150.00,NULL,NULL,252),(199,'2018-06-22 15:05:08','2018-06-22 00:00:00','jvega@hotmail.com',271.20,11.20,260.00,NULL,NULL,252),(206,'2018-06-22 15:32:28','2018-06-22 00:00:00','jvega@hotmail.com',50.00,0.00,50.00,NULL,NULL,252);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id_product` int(10) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `date_registration` date NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `user_register` varchar(40) COLLATE latin1_bin DEFAULT NULL,
  `user_expiration` varchar(40) COLLATE latin1_bin DEFAULT NULL,
  `unit_price` decimal(7,2) NOT NULL,
  `tax` decimal(7,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_bin COMMENT='table to register the products';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,248,'2018-03-30','2999-12-31','JVEGA',' ',50.00,0.00),(2,249,'2018-03-30','2999-12-31','JVEGA',' ',45.00,7.00),(3,250,'2018-03-30','2999-12-31','JVEGA',' ',50.00,7.00),(4,251,'2018-03-30','2999-12-31','JVEGA',' ',60.00,7.00);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `FIRST_NAME` varchar(40) COLLATE latin1_bin NOT NULL,
  `MAIL_CONTACT` varchar(60) COLLATE latin1_bin DEFAULT NULL,
  `SEX` smallint(1) DEFAULT NULL,
  `WEIGHT` float(5,2) DEFAULT NULL,
  `BIRTHDATE` date NOT NULL,
  `DAY_OF_INCOME` date DEFAULT NULL,
  `STATUS` smallint(1) DEFAULT NULL,
  `CATEGORY` int(3) DEFAULT NULL,
  `TELEPHONE_CONTACT` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `ADDRESS` varchar(424) COLLATE latin1_bin DEFAULT NULL,
  `DOC_ID` varchar(60) COLLATE latin1_bin NOT NULL,
  `ID_STUDENT` int(10) NOT NULL AUTO_INCREMENT,
  `ID_TEACHER` int(10) NOT NULL,
  `SURNAME` varchar(40) COLLATE latin1_bin NOT NULL,
  `SURNAME2` varchar(40) COLLATE latin1_bin NOT NULL,
  `CELLPHONE` varchar(20) COLLATE latin1_bin NOT NULL,
  `TELEPHONE_CONTACT2` varchar(20) COLLATE latin1_bin DEFAULT NULL,
  `BLOOD_TYPE` int(5) NOT NULL,
  `ALLERGY` varchar(40) COLLATE latin1_bin NOT NULL,
  `SIZE` int(5) DEFAULT NULL,
  `HEIGHT` float(5,2) DEFAULT NULL,
  `PHOTO` blob DEFAULT NULL,
  `NAME_OF_GUARDIAN` varchar(40) COLLATE latin1_bin DEFAULT NULL,
  `GUARDIAN_LAST_NAME` varchar(40) COLLATE latin1_bin DEFAULT NULL,
  `MAIL_GUARDIAN` varchar(60) COLLATE latin1_bin DEFAULT NULL,
  `PHONE_GUARDIAN` varchar(20) COLLATE latin1_bin DEFAULT NULL,
  `PHONE_GUARDIAN2` varchar(20) COLLATE latin1_bin DEFAULT NULL,
  `DOC_ID_GUARDIAN` varchar(60) COLLATE latin1_bin DEFAULT NULL,
  `CELLPHONE2` varchar(20) COLLATE latin1_bin DEFAULT NULL,
  `DATE_OF_LOW` date DEFAULT NULL,
  `LAST_NAME` varchar(20) COLLATE latin1_bin DEFAULT NULL,
  `ID_EMPLOYMENT` int(5) DEFAULT NULL,
  PRIMARY KEY (`ID_STUDENT`),
  UNIQUE KEY `ID_STUDENT` (`ID_STUDENT`),
  KEY `INDEX_TEACHER` (`ID_TEACHER`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('Lorenzo','lrodriguez71@gmail.com',1,77.00,'2018-05-28',NULL,1,3,'234-0011','samaria 2, casa 342','8-331-9233',89,0,'Rodriguez','Buffon','','',246,'NO',235,1.67,'','','','','','','','344-3411',NULL,'',18),('Jerry','jmina@farsa.es.kk',1,-88.00,'2018-05-29',NULL,1,4,'334-333','Colombia','8-331-9222',90,0,'Mina','Fernandez','','',245,'NO',237,1.90,'','','','','','','','',NULL,'',6),('Vivian','vivileti@mail.com',2,59.00,'2018-05-29',NULL,1,5,'782-2241','CDE-PH New Seal #444','8-2221-331',92,0,'Gonzales','Morales','','',244,'NO',234,1.56,'','Pedro','Gonzales','pedro.33-gonzales@mail.com','455-2234','','','',NULL,'Leticia',0),('Luis','luis.cc@bzk.com',1,77.00,'2018-06-01',NULL,1,3,'452-2313','Valladolic España. Terranova','ES-9923-133',93,0,'Cabello','Cabezudo','','822-2241',242,'NO',235,1.76,NULL,'','','','','','','',NULL,'Andres',125),('Linda','linda@foz.com',2,66.00,'2018-06-01',NULL,1,3,'323-1333','El dorado #111','8-1722-3313',95,0,'Pinzon','Aguilar','','422-4323',245,'NO',234,1.55,'����\0JFIF\0\0\0\0\0\0��\0�\0	( \Z%!1!%)+...383-7(-.+\n\n\n\r\Z+--+-------+--------++.+-+77+-7+-7+--+-7-+-+-+--+-+��\0\0�\"\0��\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0>\0\0\0\0\0!1AQaq\"��2����BRb���#Cr���$3���\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0 \0\0\0\0\0\0\0\0\0\0!1AQaB��\0\0\0?\0̤�u���p�\'N�g���s�i�gq�o&���毪$�i7�,t�t� wq��e�<�����_a�d���y*�#���I��u*�P|�G����}.����:Y\'%��6�t��9)T��3�\\2��04h��\'��D����C�>Gqs���Qͤȟ�\r>h�B\Zl8�?4DG��pN��nî@�|���ڬ�e����8m���V�8E٦�U�BǗGB�v�Q��~wW54cx��w⫪i�;)�z�����,/�������~uB�3�uD�p��co��W응���p�@{N�NI�x���uzVVD�_�4\'�`-@{G����1���Y�T��bq��\0B�Ʋ�c�Ӓ���$�d�ۛH=�w�JG!��6��{��9;ކ{�Z�޸��=ˋ���C�$������$�t��\'	�\0�9Ip���-~�R���A�B�:ky[�̮��E��J\Z�������f�މ���R.z\0��^=�:Wj�F�F�\\-��4vⴔ�1���J6����u����J�O��U,np�4pDV�W�����G��Z`�/�Y�/\'��X<��؞��1��i�Ϊ���T6൳6�j�7%x����.o;u�N*��2�x��\Z���5���+����\0	���F\\+Y��7	�Uc�6�çE�š�^K\'^�N��a�\\���w\Z�U�F�&�\Z_�S�S�����+\\Z!4!�<���S�^ҷ����!q(��է0>i�e̴��v���;d�tV-(�7��)�ʯv�{8_xz��\0D|�m+�,uP��w�I�CK\"�D���3ܤ�.*U�s�\'9\'�p{�ѝ�L�9�+��W��L�-��N\'@:��*7o�Q�����fv�O%�\0l���r駎n����ҟ n{\rf@]����Yޝ1cM��3��aL�ѹ,�;�s̭v�G��w3e��-��o%��k�)��g���#tw:�篨.q7̛vLڍ��/y��5�$����6^��\0�sm�m�u\r಻.k\Z�\r���r?�w&�U���,GKY<q+��w����\rp�:@�D\ZF��\\9�0���wJ�Hѝӳbq^i-��b�]��w1�Tb�]�^���S>72R-n\Z��/;�����w���l`0ɞ@���g�w��\'#�r?�Am-\\2�wS�{*L>���ńi������1�Hx~Uѳy���7���h���>����uV�.?��~�::��\'�zz#����C��*Ʊ�G9��ˤ�C���\\W�I��\'�=��=˃��r�N����=1:`�h�I �)�hO��+��8� >KU(�fv��	���T����8ӝ�Zqǹ\\xΠrY�L2���\n_�+|E���?��b���{�Nk}7��WZ���b�灺\0�6�٬���3�X���~��(�f�0���/˚�¨�;�ok�ǚ�ZF(@`�Ks�Uх�ty����٘G�ۺLn��#4�N׍�Dl�o��[y����_0���!�%k(%s�[+��RU�1��f�u\\n�,�|�#��UU�_������D��tıVS��^�[�\'�K9U����֍N��\0s!T���)\Z\Z���-����y�=��yKN�e�_�m��p��k�����zo\Z=᭸~�t;�xf����	����Cb�͗����ph^��9�Ů���	\07۽�-��%!,�;� p!�#��u_��K�FH2�%���p�ߖB�˴�!W�H=�y��!��r����4�H�Z	$��T�S���$�2p����RZ\Z9�l:]h�X�����|��j�v�߀�E���p/�a{���w��n�m��z\\,�ˮc��#�-}�O�����\'�gw�Mɻ�9e���n�ۿ��<m��Z�+\0�V�)�1�a��U��T>��6\"���W�K��9���R�l_�߳O�W�o/2~ϵ���-�.{�q���!|1�7#����/���߸NQF�}���R;H�G2��<l�8e.�e�5�1���Mw�[-I�H�w���<��<\Z׬�mn���a�L*M�cq��|ק�l�U�A^�^q����Β@� �?�m��I�,@��{��w1ء]�[�;ԭ&vN鍻�8�`v�H#���5������\ZE������,��-֓1��lk��.9p^I�Q�K�� E�h/$�\0���`.ןgr����X�;\"���碳����AP���_(��;��d}N]��$+��A�夌\\�r�S��k�d�I �))�$��L�+q�\'L�\n:�mE1l����b���6F�\\̹%U�Z�G��Q���k�w������\"k�����J��bJ6�}��]`u,&��ӷz=�Z�R:����g���U@�fU��*��d��vaB��4�ͺ1��@�N�j��\nf�J~\n��F�C�h�-\re8]�hJj��pt�wI��B�*�(դ�%R疛e؂RsB��sM��P����Qr��*�&5���.Ղ�����f�6���R�2.^[����;f��uʄ31��.MͲ�%���Ҙ䕇���/����+*��4����}�?6J���z�NX۸\ZW �r�;�nr�1)�L��8RQj��I�I�%t�֛q����I\'L�K���c�����+�n��7����tYH_��� �f���7��\0�-��q���,v���9�����5S���r�sN�Zt�ܐλ\\�ɾ<4ծ��5�����*�N�ҕk��b�]N����f��s��Z�=��T+��dm�z�b�L�G#N�8=�雑�谮�_	���V�y�})1�Y�lYl��!)��#\'��D��/9�c��Aʱ��X���}�I�g�N�U�7�5�S��[2��������m��V��.Bi�A#ď4G�U������~j����u6��Bɷ���f6:y���;����a�>��+ȍ�<\ZO�g��d�E���fd8����]^=0αMvhJ��9��٪���v��y^�9I�\\ֆt�2t�r���$�\'@z:t�n=)�3I:�N��D�9�ϋ�a�Y�Āxp�������\0�&�m��ɮ�Y].���W<I!y��^�q������P͸�}=֮���櫫ǟ�j�����B�����Z��Q���Ь��W��^�V���ܪj��24���m��PuY�v�9mP�XNNg�G[fլ��שK��/�EQ]�R���9G�Q�ۙI �v���bi���rMۼlUj�����ܡ��i:��2�i`o��Qؚ@��ܷ~�k�f�K��m{ͅ�FD�=B^��??��\0�,m���U~���5Ĺ��q(z�*��r��Ah#A{�J;dv2*P^F�������J���[7&��M�H��:����hϒ�s�ʔ��Y����Q<���o�.��Z��C��-��nw�6��g�u{�oY��bw��\0�값�!4-���f�������	�ex�9v����U|��]��\n�+�h�*)Ƀ�(�]0R*7N�I�]$�\'	�p��t�\'L)�0�D�V���o��ywB�$�3<��_aՙ�uB��]Y8i:�s����O�l�>���j��d��69�*�\\�:Z��d�Zz*�Ўd7Ul�E4-��駲�4��o�铊�φ���0\r�Z=��d�,��I������7���2���\Z͞�������x[�/j������(�$[�VvD��FQe7�gr���\'\0ު������;,�������:����L���^$������@>k+�UN$�M��\'���*�7J�o+�VfI$��)�@N�]A=�)(��Bp��NT�L�8�2t��2E*kuQ�Uy�/�����sn�f%q�A�@�~�mi�?^��҇Y�#a��B���ȣ�۱�Z>�U�Ӗ�u�:i0�Bڕ����� ��\\QԸ�i��#�b���Ұ�x���ua�7BUL�ӗ\0��YᎷP��hAJ��h�\0*<C\r\Z���r�U<��C�)�Y�s�^{�>��ޡ��f����6���1�����{�F�dm)g<}�\nr����U{�����#��U$�H�I I$�I$���]J�ɤ��GN���&l���T%�78�2U��V�ԗ�V��x�c��\r��$h��p�3[�.�M���@b��x�rW�Jf��G��s�\Z>��gv��\r�����G�����ZFj�������i���Ee-�V�B����+]�s�?��œq\'~��M�R?+䂊���,6H��4���Q�E����D-BO��輘�oo;���^��˹����f��Z�Ke��4^S�ꀕX���W��dU�N�����$�@$�I\0�I$I$����\0�uNgK�/���93  \Z9nI�B�;�7QÚ�,��?�B�%@m���ꒂ�\0j6�;�#0Ti�>#��uYW�,��&��-IV�?��V�\r�v6�8�fԕ6����(�U\'���<\0�O%�X&�hDm�W�w���\Zh�bm����J:N*U�\nNb���fP3F�eh(\'�uF�U]*�<��HA>1}��9�\Z����X°��Ԋ�\"�Mς%��j2���9/(�ݛ}$�����y~�^�5EOH�XX��x$S_#���V�|���vź��A���M�	�/�Y!?�B	#-��E�#yhx!��J��$�@$�I\0�I$I$���','','','','','','','',NULL,'Maria',169),('Andriano','adriano@mail.com',1,77.00,'2012-03-15','2018-06-01',1,3,'772-3312','Samaria 23.33','8-231-444',98,0,'Mejia','Varon','','',244,'NO',235,1.77,'����\0JFIF\0\0\0\0\0\0��\0�\0	\r\r\r\r \"\" $(4,$&1\'-2%1,.110#+483-8)9.,\n\n\n\r7+7-77+-7++++++-+7+/7-+-+-77-++---2++-++-++7++++++-��\0\0�\0�\"\0��\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0:\0\0\0\0\0!1AQa\"q���#2br���BCR3������\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0\"\0\0\0\0\0\0\0\0\0\0!1AQq\"���\0\0\0?\0�b\"\" \"\"\" \"%,����N\n�r���v|¥�6u�\0B7yP�,	�Wmו�%�-�<㙀�\\��@�\Z\'�g��a����Բ���`[�k3u!��9��3����|�����Y84}�uդޘ2m���,/�V�ť�/�ڑ?��̨��Z�]**�6�9���V`˰���_j�v��H�W���%5+y�Kݧ�=*ٕX���A3���}�~��,�,�\',��������{��T[:}y����NX�B�=��Z����B}�QIK�I\0����(�Ľqo�<���\\�e���L��2!����,a��|%�da����!�=KLdg���]�����#j��XK ʧ|��˖�����8#�q�Q��ʎ=�\n��NA��Cu[�����\"vY�+i���6�P�l�\'���&�@�2��t\"|^�Ϯ�}#؆��Zo-L�F�PF\'�&L�Q!DD�DDD@DDD@DD��bʝ��Unuk�Tr�-�SQ�����8���I�#���ͪ�ՉQ����?�m�~�9D$�O@d\\WEI�\0.��s�?��8k�kU�:�l�3���`���O-���r�x�=�4��T�U=�}w���wD�3�&M3H�D.Qr63d@���\'�|�~7��\"˅.�\0R�P������qLπ�\0����s���@���|�}X�t���C�[���\0y�y_�k�!�m���ƑL�7(�y�n�y�!o��>e�cƤ���p2W{bN��k6�����ܭF���w�|G�$yŖ��jd��j�<��mRʮyG�*6<�c��w$�5���NC����������������7��H�\\��촀��wZ�:��9ܕ�)�v�lF�A��j�&����Us�v��<�M������ԛoIp6����\0xL*3i�g\'^�W+A.�)P�3�a�L%^d��&0�c�c�6�LZ�&��hݗ#����~zL7�B:��I#�(�P������8K*\\�v�3��M2����/�_��4(��A�K��\\\0<�%S��0�[�oI�-�\0V\'�I���#g!v�L{]�o�\0e���J�n�-�)\0#,e���F�6�۔����/��l�[�6%q�)��̞�/\Z�X��eQ�)+r�9�����\0��y6nv|31_<�L�n��4+�Ț�J�\0,�i������$q�[��a�9lu~󰳿/�����8������u�7���붵.�=y�ǼnI�Wy{Iz�14�z�\0qξG~��^��K���2�~�T5<2e�k1���ה��2��F$}��;&�i@�hG���\0S�Ѵ���r霩?���{G���N�ڣ-���wܟ)|kz�{�y��N������Ⱥ.����s�\r���N�m��.՚�I$\"\"\" \"\"\" y5\\G�IT��Y��:ͿyE���s��M|�3I�\\s���U���<M�?�Sq����L~ ��-s����Oɥ�(�\0�8��ŝjֹ��w��i�^�U(=*����n�ߐ����ҷ�����6Rf;k����4M�J\nNY3�����\'��3��7\0W3*�Mr�;��c�e�*�$I�c�:��\0lc)�^s�&U��e[\0�cY�Y��-�2��R�4y˰Z�R��͟��l�4��u[5y��sn�<��r<�@xM��C\n�զ�@*���tK~k���[zi�I�F�\n�4�(��ݻ��O��5G[\r���xV�TR�?)����()�\0�[�6s��\\�r�go2DD��DDD@DDD@O� E�]l)��?W���fQÕ*�9fv\Z���j\Z���j}ۮ@a���S��S�Ԋ������g:u8y\"Ո���t�X�W���k݃;b^e�S�S�Z��w����뉭��Q�#�\'v�*������q���\\lA�lP�ʟܳ�8�Gn��i�ȵ%q���3|̓��x؃=�SQ��,\\>�g�0�j������>|oR�F?\\�gk�Ȧ�FX��<Y���gE��í\Z4�@���K1>2S�ܓ�3�%i�n�֟\"\"� _��<�ُ�L�(DDD@DDD@DD$C�T��X�<*�\\!���K�(�\n7VU�N�&�����ȯ�%�Ƕ]���̓�	�sx����\0>s��5$¶r�z	���ފj��6��	���L1��ոb@���c���\0�����瘓�dc�0��j9I\n:��b_�Ґ��|��X�ȈϬ�JO�c���-�V�	R*(�c����I�\'���]+�$��w�W�����T\\��08ʙ��L��0i�4��q�) ���RԔ�Q�Dņ-�qkG��w��M�\0�IRE����^ׯ�r��O�F9?a�����������6��\0Ob\"z^r\" \"\"\" \"\"\" \"\"E���{PO�\n��<q�����^-����V�R���?���$���j��)�@i�h[� �y#}I��D�f������\ZoM�\0�6��4MD\n������>�M쮪S���I�GY�����*)$��ʢr�8��ҥ��*L���}��G�9�7�v�1����3}KTW�/�_I���l��M9�g�v�˙���1�ji��!O4�j<D�J������Y�*��Ei����u�QE6�O#�0��s0���\0���ʞ\\6T�4:}�MV换l��T���w�a��\"��;�m���LJ^\Z����Հ9_<	,O��P��F�ۓH��&�T�d)��\0�Gg��P�T��aB�dp�n��}�^S����Ͷu(D������\Z��-$Y�\"����5��t�|��{ʃ� 䥟��dk����^eR���Ӗ�!�nw�bX�����l��纮���W9��\0��wl����\'���޸\r]���̋��gb������Y��L�3��j�۷������33v>��ӱˎMEA�=*��}G�82zͷߛ[�U�9�Qj|q�}3-{�;��\rA;�>�ʯ�6�a��^[:3Sè��r9}1>����*!ʲS�Ț^/��}MI S��8��i������>\\�P%���n_O\0f����!�O�����]૫G*W ��3D�=n��B�C�<���鉷�W���	;����3�)`�c�˂v\"j�\0�uN��C�C6\'sYer}у���?�-�\\��]��Eg$��y	5�\r2�z�5�A��o�O���N��(��=�v\"�����E����pq�o\'-��|�\0�UPo�GU�@�I��\'��U��\\�MfT��Wa�Np���\0Z!���h����Z���uT�P?I�Ǥ��W�=>�\0�*7�W;rU +����zg_�f>�&��3���Z���g�p���=�/�OO�$����6[�R<\rJ#��3���h��h<k�j�H��f���\0�w�N�b�D@�8�������\nc��X�A���\"�cZ��~���ov$/�t)�Vl�b�V^\0Cx���3�0#1S�d�lD�A�\0�L���%�/d��`�NZ��\'�u_��) ��@݋j\\�U(�Z$�޻�\0���H!j��*�+�#���9��\0!0��gJ8�i����t�˅�K�\0&��WG�u-Qn*�0�DN�T��即ey�O�V���b��x��\0�ot�-(�7b7b7�yHSN�u\n��+ԫ�@�!{�ډ�B�w����)�XQ�V�&*)�$�R~;��\"�8��r��-~�8��\'+�\Z�qksW;�+���Ί�`�\n�A�A�\"G�^�V�ޭt����M�p�dp7�f+	��&Y��R��^�2��\0f0��³�2�E���Nۅ;Q�t�Tv��nJ�K��/�|�8��$����	���j|��;�����#��OF�D�x�L=WW��%l?��ۈJL�3�0)����a���.�B���AJ��:OebZ�7|��5�Rp���p~�}9^�\"�nz(�3�}3�:�����=MV��f��m\ZB=����V�ի\Z�Tf�[��}LxNK�ݳ����+�o�0|g�5 �0�)��t�-BjӮS�����Z�hh8���vִ�+����*dytǤ�;4���9nn�Ų�F�e}������N�[U�*��r+3�U�����$�J�P\0\0�Z{!f��O\06��P�on:%&��\0���}��P�>h�K��j��Q=vĵYs���f{\",�@7�\02����c-5?)�¼J�P�Dg�J�J)+�r랳�Ob%T��[o����Ǭ�b\"�DJ�M����krw�[�c���\"\'��ikz���*V�S8����=d�Q��\"(YWfZ�+MQсQ]��3yN}���>r~F��D�Xi��Q�[;��)A���`}ȟ0�?X��~$�Ĥ�|OH���؈�S�P`�	LD*��$��','','','','','','','',NULL,'',19),('Gabriel','GNUNEZ@BZK.COM',1,78.88,'2018-06-01','2018-06-01',1,3,'342-2313','VILLA LUCRE SAN RAFAEL CASA 342','7-313-313',99,0,'Nuñez','Varon','','',247,'NO',238,1.98,'����\0JFIF\0\0\0\0\0\0��\0�\0	\r\r\r\r \"\" $(4,$&1\'-2%1,.110#+483-8)9.,\n\n\n\r7+7-77+-7++++++-+7+/7-+-+-77-++---2++-++-++7++++++-��\0\0�\0�\"\0��\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0:\0\0\0\0\0!1AQa\"q���#2br���BCR3������\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0\"\0\0\0\0\0\0\0\0\0\0!1AQq\"���\0\0\0?\0�b\"\" \"\"\" \"%,����N\n�r���v|¥�6u�\0B7yP�,	�Wmו�%�-�<㙀�\\��@�\Z\'�g��a����Բ���`[�k3u!��9��3����|�����Y84}�uդޘ2m���,/�V�ť�/�ڑ?��̨��Z�]**�6�9���V`˰���_j�v��H�W���%5+y�Kݧ�=*ٕX���A3���}�~��,�,�\',��������{��T[:}y����NX�B�=��Z����B}�QIK�I\0����(�Ľqo�<���\\�e���L��2!����,a��|%�da����!�=KLdg���]�����#j��XK ʧ|��˖�����8#�q�Q��ʎ=�\n��NA��Cu[�����\"vY�+i���6�P�l�\'���&�@�2��t\"|^�Ϯ�}#؆��Zo-L�F�PF\'�&L�Q!DD�DDD@DDD@DD��bʝ��Unuk�Tr�-�SQ�����8���I�#���ͪ�ՉQ����?�m�~�9D$�O@d\\WEI�\0.��s�?��8k�kU�:�l�3���`���O-���r�x�=�4��T�U=�}w���wD�3�&M3H�D.Qr63d@���\'�|�~7��\"˅.�\0R�P������qLπ�\0����s���@���|�}X�t���C�[���\0y�y_�k�!�m���ƑL�7(�y�n�y�!o��>e�cƤ���p2W{bN��k6�����ܭF���w�|G�$yŖ��jd��j�<��mRʮyG�*6<�c��w$�5���NC����������������7��H�\\��촀��wZ�:��9ܕ�)�v�lF�A��j�&����Us�v��<�M������ԛoIp6����\0xL*3i�g\'^�W+A.�)P�3�a�L%^d��&0�c�c�6�LZ�&��hݗ#����~zL7�B:��I#�(�P������8K*\\�v�3��M2����/�_��4(��A�K��\\\0<�%S��0�[�oI�-�\0V\'�I���#g!v�L{]�o�\0e���J�n�-�)\0#,e���F�6�۔����/��l�[�6%q�)��̞�/\Z�X��eQ�)+r�9�����\0��y6nv|31_<�L�n��4+�Ț�J�\0,�i������$q�[��a�9lu~󰳿/�����8������u�7���붵.�=y�ǼnI�Wy{Iz�14�z�\0qξG~��^��K���2�~�T5<2e�k1���ה��2��F$}��;&�i@�hG���\0S�Ѵ���r霩?���{G���N�ڣ-���wܟ)|kz�{�y��N������Ⱥ.����s�\r���N�m��.՚�I$\"\"\" \"\"\" y5\\G�IT��Y��:ͿyE���s��M|�3I�\\s���U���<M�?�Sq����L~ ��-s����Oɥ�(�\0�8��ŝjֹ��w��i�^�U(=*����n�ߐ����ҷ�����6Rf;k����4M�J\nNY3�����\'��3��7\0W3*�Mr�;��c�e�*�$I�c�:��\0lc)�^s�&U��e[\0�cY�Y��-�2��R�4y˰Z�R��͟��l�4��u[5y��sn�<��r<�@xM��C\n�զ�@*���tK~k���[zi�I�F�\n�4�(��ݻ��O��5G[\r���xV�TR�?)����()�\0�[�6s��\\�r�go2DD��DDD@DDD@O� E�]l)��?W���fQÕ*�9fv\Z���j\Z���j}ۮ@a���S��S�Ԋ������g:u8y\"Ո���t�X�W���k݃;b^e�S�S�Z��w����뉭��Q�#�\'v�*������q���\\lA�lP�ʟܳ�8�Gn��i�ȵ%q���3|̓��x؃=�SQ��,\\>�g�0�j������>|oR�F?\\�gk�Ȧ�FX��<Y���gE��í\Z4�@���K1>2S�ܓ�3�%i�n�֟\"\"� _��<�ُ�L�(DDD@DDD@DD$C�T��X�<*�\\!���K�(�\n7VU�N�&�����ȯ�%�Ƕ]���̓�	�sx����\0>s��5$¶r�z	���ފj��6��	���L1��ոb@���c���\0�����瘓�dc�0��j9I\n:��b_�Ґ��|��X�ȈϬ�JO�c���-�V�	R*(�c����I�\'���]+�$��w�W�����T\\��08ʙ��L��0i�4��q�) ���RԔ�Q�Dņ-�qkG��w��M�\0�IRE����^ׯ�r��O�F9?a�����������6��\0Ob\"z^r\" \"\"\" \"\"\" \"\"E���{PO�\n��<q�����^-����V�R���?���$���j��)�@i�h[� �y#}I��D�f������\ZoM�\0�6��4MD\n������>�M쮪S���I�GY�����*)$��ʢr�8��ҥ��*L���}��G�9�7�v�1����3}KTW�/�_I���l��M9�g�v�˙���1�ji��!O4�j<D�J������Y�*��Ei����u�QE6�O#�0��s0���\0���ʞ\\6T�4:}�MV换l��T���w�a��\"��;�m���LJ^\Z����Հ9_<	,O��P��F�ۓH��&�T�d)��\0�Gg��P�T��aB�dp�n��}�^S����Ͷu(D������\Z��-$Y�\"����5��t�|��{ʃ� 䥟��dk����^eR���Ӗ�!�nw�bX�����l��纮���W9��\0��wl����\'���޸\r]���̋��gb������Y��L�3��j�۷������33v>��ӱˎMEA�=*��}G�82zͷߛ[�U�9�Qj|q�}3-{�;��\rA;�>�ʯ�6�a��^[:3Sè��r9}1>����*!ʲS�Ț^/��}MI S��8��i������>\\�P%���n_O\0f����!�O�����]૫G*W ��3D�=n��B�C�<���鉷�W���	;����3�)`�c�˂v\"j�\0�uN��C�C6\'sYer}у���?�-�\\��]��Eg$��y	5�\r2�z�5�A��o�O���N��(��=�v\"�����E����pq�o\'-��|�\0�UPo�GU�@�I��\'��U��\\�MfT��Wa�Np���\0Z!���h����Z���uT�P?I�Ǥ��W�=>�\0�*7�W;rU +����zg_�f>�&��3���Z���g�p���=�/�OO�$����6[�R<\rJ#��3���h��h<k�j�H��f���\0�w�N�b�D@�8�������\nc��X�A���\"�cZ��~���ov$/�t)�Vl�b�V^\0Cx���3�0#1S�d�lD�A�\0�L���%�/d��`�NZ��\'�u_��) ��@݋j\\�U(�Z$�޻�\0���H!j��*�+�#���9��\0!0��gJ8�i����t�˅�K�\0&��WG�u-Qn*�0�DN�T��即ey�O�V���b��x��\0�ot�-(�7b7b7�yHSN�u\n��+ԫ�@�!{�ډ�B�w����)�XQ�V�&*)�$�R~;��\"�8��r��-~�8��\'+�\Z�qksW;�+���Ί�`�\n�A�A�\"G�^�V�ޭt����M�p�dp7�f+	��&Y��R��^�2��\0f0��³�2�E���Nۅ;Q�t�Tv��nJ�K��/�|�8��$����	���j|��;�����#��OF�D�x�L=WW��%l?��ۈJL�3�0)����a���.�B���AJ��:OebZ�7|��5�Rp���p~�}9^�\"�nz(�3�}3�:�����=MV��f��m\ZB=����V�ի\Z�Tf�[��}LxNK�ݳ����+�o�0|g�5 �0�)��t�-BjӮS�����Z�hh8���vִ�+����*dytǤ�;4���9nn�Ų�F�e}������N�[U�*��r+3�U�����$�J�P\0\0�Z{!f��O\06��P�on:%&��\0���}��P�>h�K��j��Q=vĵYs���f{\",�@7�\02����c-5?)�¼J�P�Dg�J�J)+�r랳�Ob%T��[o����Ǭ�b\"�DJ�M����krw�[�c���\"\'��ikz���*V�S8����=d�Q��\"(YWfZ�+MQсQ]��3yN}���>r~F��D�Xi��Q�[;��)A���`}ȟ0�?X��~$�Ĥ�|OH���؈�S�P`�	LD*��$��','','','','','','','',NULL,'Jesus',95),('Emanuel','chiqui_emanuel@hotmail.com',1,33.00,'2015-10-28','2018-06-22',1,5,'235-3318','Samaria #2, casa455','8-222-244',208,0,'Urriola','Ruiz','','',243,'NO',234,1.30,NULL,'Celso','Urriola','celso@gmail.com','451-3131','','','',NULL,'Santo',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_codes`
--

DROP TABLE IF EXISTS `system_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_codes` (
  `idsystem_codes` int(10) NOT NULL AUTO_INCREMENT,
  `system_code` varchar(10) COLLATE latin1_bin NOT NULL,
  `system_cod_description` varchar(80) COLLATE latin1_bin DEFAULT NULL,
  `system_cod_group` varchar(10) COLLATE latin1_bin NOT NULL,
  PRIMARY KEY (`idsystem_codes`)
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=latin1 COLLATE=latin1_bin COMMENT='system codes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_codes`
--

LOCK TABLES `system_codes` WRITE;
/*!40000 ALTER TABLE `system_codes` DISABLE KEYS */;
INSERT INTO `system_codes` VALUES (1,'GENDER001','MASCULINO','GENDER'),(2,'GENDER002','FEMENINO','GENDER'),(3,'CATEGORY01','CADETE','CATEGORY'),(4,'CATEGORY02','JUVENIL','CATEGORY'),(5,'CATEGORY03','INFANTIL','CATEGORY'),(6,'EMPLOY1001','ABOGADO ','EMPLOYMENT'),(7,'EMPLOY1002','ADMINISTRADOR','EMPLOYMENT'),(8,'EMPLOY1003','ADMINISTRATIVO','EMPLOYMENT'),(9,'EMPLOY1004','AGRÓNOMO ','EMPLOYMENT'),(10,'EMPLOY1005','ALERGÓLOGO','EMPLOYMENT'),(11,'EMPLOY1006','ALERGISTA','EMPLOYMENT'),(12,'EMPLOY1007','ALERGÓLOGA','EMPLOYMENT'),(13,'EMPLOY1008','ALERGISTA','EMPLOYMENT'),(14,'EMPLOY1009','ALMACENISTA','EMPLOYMENT'),(15,'EMPLOY1010','ANATOMISTA ','EMPLOYMENT'),(16,'EMPLOY1011','ANESTESIÓLOGO','EMPLOYMENT'),(17,'EMPLOY1012','ANESTESISTA','EMPLOYMENT'),(18,'EMPLOY1013','ANESTESIÓLOGA','EMPLOYMENT'),(19,'EMPLOY1014','ANESTESISTA','EMPLOYMENT'),(20,'EMPLOY1015','ANTOLOGISTA','EMPLOYMENT'),(21,'EMPLOY1016','ANTROPÓLOGO','EMPLOYMENT'),(22,'EMPLOY1017','ARABISTA ','EMPLOYMENT'),(23,'EMPLOY1018','ARCHIVERO','EMPLOYMENT'),(24,'EMPLOY1019','ARQUEÓLOGO','EMPLOYMENT'),(25,'EMPLOY1020','ARQUITECTO','EMPLOYMENT'),(26,'EMPLOY1021','ASESOR','EMPLOYMENT'),(27,'EMPLOY1022','ASTROFÍSICO','EMPLOYMENT'),(28,'EMPLOY1023','ASTRÓLOGO','EMPLOYMENT'),(29,'EMPLOY1024','ASTRÓNOMO','EMPLOYMENT'),(30,'EMPLOY1025','ATLETA','EMPLOYMENT'),(31,'EMPLOY1026','AUXILIAR','EMPLOYMENT'),(32,'EMPLOY1027','AVICULTOR','EMPLOYMENT'),(33,'EMPLOY1028','BACTERIÓLOGO','EMPLOYMENT'),(34,'EMPLOY1029','BIBLIÓGRAFO','EMPLOYMENT'),(35,'EMPLOY1030','BIBLIOTECARIO','EMPLOYMENT'),(36,'EMPLOY1031','BIOFÍSICO','EMPLOYMENT'),(37,'EMPLOY1032','BIÓGRAFO','EMPLOYMENT'),(38,'EMPLOY1033','BIÓLOGO','EMPLOYMENT'),(39,'EMPLOY1034','BIOQUÍMICO','EMPLOYMENT'),(40,'EMPLOY1035','BOTÁNICO','EMPLOYMENT'),(41,'EMPLOY1036','CANCERÓLOGO','EMPLOYMENT'),(42,'EMPLOY1037','CARDIÓLOGO','EMPLOYMENT'),(43,'EMPLOY1038','CARTÓGRAFO','EMPLOYMENT'),(44,'EMPLOY1039','CATEDRÁTICO','EMPLOYMENT'),(45,'EMPLOY1040','CIRUJANO','EMPLOYMENT'),(46,'EMPLOY1041','CODIRECTOR','EMPLOYMENT'),(47,'EMPLOY1042','CONSERJE','EMPLOYMENT'),(48,'EMPLOY1043','COORDINADOR','EMPLOYMENT'),(49,'EMPLOY1044','COSMÓGRAFO','EMPLOYMENT'),(50,'EMPLOY1045','COSMÓLOGO','EMPLOYMENT'),(51,'EMPLOY1046','CRIMINALISTA','EMPLOYMENT'),(52,'EMPLOY1047','DECANO','EMPLOYMENT'),(53,'EMPLOY1048','DECORADOR','EMPLOYMENT'),(54,'EMPLOY1049','DEMÓGRAFO','EMPLOYMENT'),(55,'EMPLOY1050','DENTISTA','EMPLOYMENT'),(56,'EMPLOY1051','DERMATÓLOGO','EMPLOYMENT'),(57,'EMPLOY1052','DIBUJANTE','EMPLOYMENT'),(58,'EMPLOY1053','DIRECTOR','EMPLOYMENT'),(59,'EMPLOY1054','DOCTOR','EMPLOYMENT'),(60,'EMPLOY1055','ECÓLOGO','EMPLOYMENT'),(61,'EMPLOY1056','ECONOMISTA','EMPLOYMENT'),(62,'EMPLOY1057','EDUCADOR','EMPLOYMENT'),(63,'EMPLOY1058','ENDOCRINÓLOGO','EMPLOYMENT'),(64,'EMPLOY1059','ENFERMERO','EMPLOYMENT'),(65,'EMPLOY1060','ENÓLOGO','EMPLOYMENT'),(66,'EMPLOY1061','ENTOMÓLOGO','EMPLOYMENT'),(67,'EMPLOY1062','EPIDEMIÓLOGO','EMPLOYMENT'),(68,'EMPLOY1063','ESPELEÓLOGO','EMPLOYMENT'),(69,'EMPLOY1064','ESTADÍSTICO','EMPLOYMENT'),(70,'EMPLOY1065','ETIMÓLOGO','EMPLOYMENT'),(71,'EMPLOY1066','ETIMOLOGISTA','EMPLOYMENT'),(72,'EMPLOY1067','ETIMÓLOGA','EMPLOYMENT'),(73,'EMPLOY1068','ETIMOLOGISTA','EMPLOYMENT'),(74,'EMPLOY1069','ETNÓGRAFO','EMPLOYMENT'),(75,'EMPLOY1070','ETNÓLOGO','EMPLOYMENT'),(76,'EMPLOY1071','FARMACÉUTICO','EMPLOYMENT'),(77,'EMPLOY1072','FILÓLOGO','EMPLOYMENT'),(78,'EMPLOY1073','FILÓSOFO','EMPLOYMENT'),(79,'EMPLOY1074','FISCAL','EMPLOYMENT'),(80,'EMPLOY1075','FÍSICO','EMPLOYMENT'),(81,'EMPLOY1076','FISIÓLOGO','EMPLOYMENT'),(82,'EMPLOY1077','FISIOTERAPEUTA','EMPLOYMENT'),(83,'EMPLOY1078','FONETISTA','EMPLOYMENT'),(84,'EMPLOY1079','FONÍATRA','EMPLOYMENT'),(85,'EMPLOY1080','FONÓLOGO','EMPLOYMENT'),(86,'EMPLOY1081','FORENSE','EMPLOYMENT'),(87,'EMPLOY1082','FOTÓGRAFO','EMPLOYMENT'),(88,'EMPLOY1083','GEMÓLOGO','EMPLOYMENT'),(89,'EMPLOY1084','GENETISTA','EMPLOYMENT'),(90,'EMPLOY1085','GEODESTA','EMPLOYMENT'),(91,'EMPLOY1086','GEOFÍSICO','EMPLOYMENT'),(92,'EMPLOY1087','GEÓGRAFO','EMPLOYMENT'),(93,'EMPLOY1088','GEÓLOGO','EMPLOYMENT'),(94,'EMPLOY1089','GEOQUÍMICA','EMPLOYMENT'),(95,'EMPLOY1090','GERENTE','EMPLOYMENT'),(96,'EMPLOY1091','GERIATRA','EMPLOYMENT'),(97,'EMPLOY1092','TRABAJADOR SOCIAL','EMPLOYMENT'),(98,'EMPLOY1093','GRAFÓLOGO','EMPLOYMENT'),(99,'EMPLOY1094','GRAMÁTICO','EMPLOYMENT'),(100,'EMPLOY1095','HEMATÓLOGO','EMPLOYMENT'),(101,'EMPLOY1096','HEPATÓLOGO','EMPLOYMENT'),(102,'EMPLOY1097','HIDROGEÓLOGO','EMPLOYMENT'),(103,'EMPLOY1098','HIDRÓGRAFO','EMPLOYMENT'),(104,'EMPLOY1099','HIDRÓLOGO','EMPLOYMENT'),(105,'EMPLOY1100','HIGIENISTA','EMPLOYMENT'),(106,'EMPLOY1101','HISPANISTA','EMPLOYMENT'),(107,'EMPLOY1102','HISTORIADOR','EMPLOYMENT'),(108,'EMPLOY1103','HOMEÓPATA','EMPLOYMENT'),(109,'EMPLOY1104','INFORMÁTICO','EMPLOYMENT'),(110,'EMPLOY1105','INGENIERO','EMPLOYMENT'),(111,'EMPLOY1106','INGENIERO TÉCNICO','EMPLOYMENT'),(112,'EMPLOY1107','INMUNÓLOGO','EMPLOYMENT'),(113,'EMPLOY1108','INSPECTOR','EMPLOYMENT'),(114,'EMPLOY1109','JARDINERO','EMPLOYMENT'),(115,'EMPLOY1110','JUEZ','EMPLOYMENT'),(116,'EMPLOY1111','LATINISTA','EMPLOYMENT'),(117,'EMPLOY1112','LECTOR','EMPLOYMENT'),(118,'EMPLOY1113','LEXICÓGRAFO','EMPLOYMENT'),(119,'EMPLOY1114','LEXICÓLOGO','EMPLOYMENT'),(120,'EMPLOY1115','LINGÜISTA','EMPLOYMENT'),(121,'EMPLOY1116','LOGOPEDA','EMPLOYMENT'),(122,'EMPLOY1117','MAESTRO','EMPLOYMENT'),(123,'EMPLOY1118','MATEMÁTICO','EMPLOYMENT'),(124,'EMPLOY1119','MATRÓN','EMPLOYMENT'),(125,'EMPLOY1120','MEDICO','EMPLOYMENT'),(126,'EMPLOY1121','METEORÓLOGO','EMPLOYMENT'),(127,'EMPLOY1122','MICÓLOGO','EMPLOYMENT'),(128,'EMPLOY1123','MICROBIOLÓGICO','EMPLOYMENT'),(129,'EMPLOY1124','MICROCIRUJANO','EMPLOYMENT'),(130,'EMPLOY1125','MIMÓGRAFO','EMPLOYMENT'),(131,'EMPLOY1126','MINERALOGISTA','EMPLOYMENT'),(132,'EMPLOY1127','MONITOR','EMPLOYMENT'),(133,'EMPLOY1128','MUSICÓLOGO','EMPLOYMENT'),(134,'EMPLOY1129','NATURÓPATA','EMPLOYMENT'),(135,'EMPLOY1130','NEFRÓLOGO','EMPLOYMENT'),(136,'EMPLOY1131','NEUMÓLOGO','EMPLOYMENT'),(137,'EMPLOY1132','NEUROANATOMISTA','EMPLOYMENT'),(138,'EMPLOY1133','NEUROBIÓLOGO','EMPLOYMENT'),(139,'EMPLOY1134','NEUROCIRUJANO','EMPLOYMENT'),(140,'EMPLOY1135','NEUROEMBRIÓLOGO','EMPLOYMENT'),(141,'EMPLOY1136','NEUROFISIÓLOGO','EMPLOYMENT'),(142,'EMPLOY1137','NEURÓLOGO','EMPLOYMENT'),(143,'EMPLOY1138','NUTRÓLOGO','EMPLOYMENT'),(144,'EMPLOY1139','OCEANÓGRAFO','EMPLOYMENT'),(145,'EMPLOY1140','ODONTÓLOGO','EMPLOYMENT'),(146,'EMPLOY1141','OFICIAL','EMPLOYMENT'),(147,'EMPLOY1142','OFICINISTA','EMPLOYMENT'),(148,'EMPLOY1143','OFTALMÓLOGO','EMPLOYMENT'),(149,'EMPLOY1144','ONCÓLOGO','EMPLOYMENT'),(150,'EMPLOY1145','ÓPTICO','EMPLOYMENT'),(151,'EMPLOY1146','OPTOMETRISTA','EMPLOYMENT'),(152,'EMPLOY1147','ORDENANZA','EMPLOYMENT'),(153,'EMPLOY1148','ORIENTADOR','EMPLOYMENT'),(154,'EMPLOY1149','ORNITÓLOGO','EMPLOYMENT'),(155,'EMPLOY1150','ORTOPÉDICO','EMPLOYMENT'),(156,'EMPLOY1151','ORTOPEDISTA','EMPLOYMENT'),(157,'EMPLOY1152','OSTEÓLOGO','EMPLOYMENT'),(158,'EMPLOY1153','OSTEÓPATA','EMPLOYMENT'),(159,'EMPLOY1154','OTORRINOLARINGÓLOGO','EMPLOYMENT'),(160,'EMPLOY1155','PALEOBIÓLOGO','EMPLOYMENT'),(161,'EMPLOY1156','PALEOBOTÁNICO','EMPLOYMENT'),(162,'EMPLOY1157','PALEÓGRAFO','EMPLOYMENT'),(163,'EMPLOY1158','PALEÓLOGO','EMPLOYMENT'),(164,'EMPLOY1159','PALEONTÓLOGO','EMPLOYMENT'),(165,'EMPLOY1160','PATÓLOGO','EMPLOYMENT'),(166,'EMPLOY1161','PEDAGOGO','EMPLOYMENT'),(167,'EMPLOY1162','PEDIATRA','EMPLOYMENT'),(168,'EMPLOY1163','PEDICURO','EMPLOYMENT'),(169,'EMPLOY1164','PERIODISTA','EMPLOYMENT'),(170,'EMPLOY1165','PERITO','EMPLOYMENT'),(171,'EMPLOY1166','INGENIERO TÉCNICO','EMPLOYMENT'),(172,'EMPLOY1167','PISCICULTOR','EMPLOYMENT'),(173,'EMPLOY1168','PODÓLOGO','EMPLOYMENT'),(174,'EMPLOY1169','PORTERO','EMPLOYMENT'),(175,'EMPLOY1170','PREHISTORIADOR','EMPLOYMENT'),(176,'EMPLOY1171','PRESIDENTE','EMPLOYMENT'),(177,'EMPLOY1172','PROCTÓLOGO','EMPLOYMENT'),(178,'EMPLOY1173','PROFESOR','EMPLOYMENT'),(179,'EMPLOY1174','PROGRAMADOR','EMPLOYMENT'),(180,'EMPLOY1175','PROTÉSICO','EMPLOYMENT'),(181,'EMPLOY1176','PROVEEDOR','EMPLOYMENT'),(182,'EMPLOY1177','PSICOANALISTA','EMPLOYMENT'),(183,'EMPLOY1178','PSICÓLOGO','EMPLOYMENT'),(184,'EMPLOY1179','PSICOFÍSICO','EMPLOYMENT'),(185,'EMPLOY1180','PSICOPEDAGOGO','EMPLOYMENT'),(186,'EMPLOY1181','PSICOTERAPEUTA','EMPLOYMENT'),(187,'EMPLOY1182','PSIQUIATRA','EMPLOYMENT'),(188,'EMPLOY1183','PUBLICISTA','EMPLOYMENT'),(189,'EMPLOY1184','PUBLICITARIO','EMPLOYMENT'),(190,'EMPLOY1185','PUERICULTOR','EMPLOYMENT'),(191,'EMPLOY1186','QUÍMICO','EMPLOYMENT'),(192,'EMPLOY1187','QUIROPRÁCTICO','EMPLOYMENT'),(193,'EMPLOY1188','RADIOASTRÓNOMO','EMPLOYMENT'),(194,'EMPLOY1189','RADIOFONISTA','EMPLOYMENT'),(195,'EMPLOY1190','RADIÓLOGO','EMPLOYMENT'),(196,'EMPLOY1191','RADIOTÉCNICO','EMPLOYMENT'),(197,'EMPLOY1192','RADIOTELEFONISTA','EMPLOYMENT'),(198,'EMPLOY1193','RADIOTELEGRAFISTA','EMPLOYMENT'),(199,'EMPLOY1194','RADIOTERAPEUTA','EMPLOYMENT'),(200,'EMPLOY1195','RECTOR','EMPLOYMENT'),(201,'EMPLOY1196','SANITARIO','EMPLOYMENT'),(202,'EMPLOY1197','SECRETARIO','EMPLOYMENT'),(203,'EMPLOY1198','SEXÓLOGO','EMPLOYMENT'),(204,'EMPLOY1199','SISMÓLOGO','EMPLOYMENT'),(205,'EMPLOY1200','SOCIÓLOGO','EMPLOYMENT'),(206,'EMPLOY1201','SUBDELEGADO','EMPLOYMENT'),(207,'EMPLOY1202','SUBDIRECTOR','EMPLOYMENT'),(208,'EMPLOY1203','SUBSECRETARIO','EMPLOYMENT'),(209,'EMPLOY1204','TÉCNICO','EMPLOYMENT'),(210,'EMPLOY1205','TELEFONISTA','EMPLOYMENT'),(211,'EMPLOY1206','TEÓLOGO','EMPLOYMENT'),(212,'EMPLOY1207','TERAPEUTA','EMPLOYMENT'),(213,'EMPLOY1208','TOCOGINECÓLOGO','EMPLOYMENT'),(214,'EMPLOY1209','TOCÓLOGO','EMPLOYMENT'),(215,'EMPLOY1210','TOXICÓLOGO','EMPLOYMENT'),(216,'EMPLOY1211','TRADUCTOR','EMPLOYMENT'),(217,'EMPLOY1212','TRANSCRIPTOR','EMPLOYMENT'),(218,'EMPLOY1213','TRAUMATÓLOGO','EMPLOYMENT'),(219,'EMPLOY1214','TUTOR','EMPLOYMENT'),(220,'EMPLOY1215','URÓLOGO','EMPLOYMENT'),(221,'EMPLOY1216','VETERINARIO','EMPLOYMENT'),(222,'EMPLOY1217','VICEDECANO','EMPLOYMENT'),(223,'EMPLOY1218','VICEDIRECTOR','EMPLOYMENT'),(224,'EMPLOY1219','VICEGERENTE','EMPLOYMENT'),(225,'EMPLOY1220','VICEPRESIDENTE','EMPLOYMENT'),(226,'EMPLOY1221','VICERRECTOR','EMPLOYMENT'),(227,'EMPLOY1222','VICESECRETARIO','EMPLOYMENT'),(228,'EMPLOY1223','VIRÓLOGO','EMPLOYMENT'),(229,'EMPLOY1224','VITICULTOR','EMPLOYMENT'),(230,'EMPLOY1225','VULCANÓLOGO ','EMPLOYMENT'),(231,'EMPLOY1226','XILÓGRAFO','EMPLOYMENT'),(232,'EMPLOY1227','ZOÓLOGO','EMPLOYMENT'),(233,'EMPLOY1228','ZOOTÉCNICO','EMPLOYMENT'),(234,'SIZE001','SMALL','SIZES'),(235,'SIZE001','MEDIUN','SIZES'),(236,'SIZE001','LARGE','SIZES'),(237,'SIZE001','XLARGE','SIZES'),(238,'SIZE001','2XL','SIZES'),(239,'SIZE001','3XL','SIZES'),(240,'BLOODGRO01','O-','BLOODGRO'),(241,'BLOODGRO02','O+','BLOODGRO'),(242,'BLOODGRO03','A-','BLOODGRO'),(243,'BLOODGRO04','A+','BLOODGRO'),(244,'BLOODGRO05','B-','BLOODGRO'),(245,'BLOODGRO07','B+','BLOODGRO'),(246,'BLOODGRO08','AB-','BLOODGRO'),(247,'BLOODGRO09','AB+','BLOODGRO'),(248,'MAT2018','MATRICULA','PRODUCTS'),(249,'UNIFORME01','UNIFORMES ESTUDIANTES INFANTIL','PRODUCTS'),(250,'UNIFORME02','UNIFORMES ESTUDIANTES CADETE','PRODUCTS'),(251,'UNIFORME03','UNIFORME ESTUDIANTE JUVENIL','PRODUCTS'),(252,'PAGO001','EFECTIVO','PAYMENTS'),(253,'PAGO002','CHEQUE','PAYMENTS'),(254,'PAGO003','ACH','PAYMENTS');
/*!40000 ALTER TABLE `system_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_dashboard`
--

DROP TABLE IF EXISTS `system_dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_dashboard` (
  `ID_SYS_DASHBOARD` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(40) COLLATE latin1_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  PRIMARY KEY (`ID_SYS_DASHBOARD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_dashboard`
--

LOCK TABLES `system_dashboard` WRITE;
/*!40000 ALTER TABLE `system_dashboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_dashboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_parameters`
--

DROP TABLE IF EXISTS `system_parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_parameters` (
  `PARAMETER _ID` int(11) NOT NULL,
  `DESCRIPTION` varchar(80) COLLATE latin1_bin NOT NULL,
  `DATE _CREATION` date NOT NULL,
  `EXPIRATION_DATE` date NOT NULL,
  `_KEY` varchar(40) COLLATE latin1_bin NOT NULL,
  `_VALUE` varchar(200) COLLATE latin1_bin DEFAULT NULL,
  PRIMARY KEY (`PARAMETER _ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin COMMENT='PARAMETERS OF THE SYSTEM';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_parameters`
--

LOCK TABLES `system_parameters` WRITE;
/*!40000 ALTER TABLE `system_parameters` DISABLE KEYS */;
INSERT INTO `system_parameters` VALUES (1,'CANTIDAD DE DIAS PARA NOTIFICACION DE INSCRIPCIONES ','2018-03-29','2999-12-31','DAYS_REG_NOTIFICATION','7'),(2,'CANTIDAD DE DIAS PARA NOTIFICACION DE PAGOS','2018-03-30','2999-12-31','PAYMENT_NOTIFICATION','3'),(3,'CANTIDAD DE DIAS PARA NOTIFICACION DE ACTIVIDADES','2018-04-08','2999-12-31','NOTIFICATION_ACTIVITIES','7');
/*!40000 ALTER TABLE `system_parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `FIRST_NAME` varchar(40) COLLATE latin1_bin NOT NULL,
  `LAST_NAME` varchar(40) COLLATE latin1_bin NOT NULL,
  `LAST_NAME2` varchar(40) COLLATE latin1_bin DEFAULT NULL,
  `CATEGORY` int(3) NOT NULL,
  `TELEPHONE_CONTACT` varchar(30) COLLATE latin1_bin DEFAULT NULL,
  `MAIL` varchar(50) COLLATE latin1_bin DEFAULT NULL,
  `DOC_ID` varchar(60) COLLATE latin1_bin NOT NULL,
  `ID_TEACHER` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID_TEACHER`),
  UNIQUE KEY `ID_TEACHER` (`ID_TEACHER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `date_given_low` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `first_name` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `last_name` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `password` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `role` varchar(255) COLLATE latin1_bin DEFAULT NULL,
  `date_registration` date NOT NULL,
  `system_signature_name` varchar(40) COLLATE latin1_bin NOT NULL DEFAULT ' ',
  PRIMARY KEY (`id`),
  KEY `INDEX_SIGNATURE` (`system_signature_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'lro.1971@yahoo.com','lro','lro','$2a$10$kB8HT.FFpWWWYzw7E6rLNenT0JsNISOvuwrOcXbILIpwRnHCxE56i','ROLE_ADMIN','2018-02-11','LRODRIGUEZN'),(2,NULL,'jvega@hotmail.com','jve','jve','$2a$10$pLij2JswyCheXlwaX6YjHeryOhOZPc/P93m4dHGfdK.VrMg1bAHpy','ROLE_USER','2018-02-16','JVEGA');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-22 16:08:55
--otros comandos
/*----------------------------------------------------+
| concat('DROP TABLE IF EXISTS `', table_name, '`;') |
+----------------------------------------------------+
| DROP TABLE IF EXISTS `access_profile`;             |
| DROP TABLE IF EXISTS `account`;                    |
| DROP TABLE IF EXISTS `app_profile`;                |
| DROP TABLE IF EXISTS `calendar_activities`;        |
| DROP TABLE IF EXISTS `detail_payments`;            |
| DROP TABLE IF EXISTS `hibernate_sequence`;         |
| DROP TABLE IF EXISTS `payments`;                   |
| DROP TABLE IF EXISTS `products`;                   |
| DROP TABLE IF EXISTS `student`;                    |
| DROP TABLE IF EXISTS `system_codes`;               |
| DROP TABLE IF EXISTS `system_dashboard`;           |
| DROP TABLE IF EXISTS `system_parameters`;          |
| DROP TABLE IF EXISTS `teacher`;                    |
| DROP TABLE IF EXISTS `user`;                       |
+----------------------------------------------------*/

ALTER TABLE `payments` CHANGE COLUMN `id_payment` `id_payment` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;

CREATE TABLE `payments` (
	`id_payment` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`date_registration` DATETIME NULL DEFAULT NULL,
	`payday` DATETIME NOT NULL,
	`user_register` VARCHAR(40) NOT NULL COLLATE 'latin1_bin',
	`total_payment` DECIMAL(7,2) NOT NULL,
	`total_tax` DECIMAL(7,2) NOT NULL DEFAULT '0',
	`subtotal` DECIMAL(7,2) NULL DEFAULT '0',
	`number_of_transfer` VARCHAR(40) NULL DEFAULT NULL COLLATE 'latin1_bin',
	`name_of_bank` VARCHAR(40) NULL DEFAULT NULL COLLATE 'latin1_bin',
	`type_payment` INT(11) NOT NULL,
	PRIMARY KEY (`id_payment`),
	INDEX `FK_USER_idx` (`user_register`)
)
COMMENT='table to register the payments'
COLLATE='latin1_bin'
ENGINE=InnoDB
AUTO_INCREMENT=1000000
;
