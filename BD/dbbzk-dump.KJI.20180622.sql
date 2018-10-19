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
INSERT INTO `student` VALUES ('Lorenzo','lrodriguez71@gmail.com',1,77.00,'2018-05-28',NULL,1,3,'234-0011','samaria 2, casa 342','8-331-9233',89,0,'Rodriguez','Buffon','','',246,'NO',235,1.67,'','','','','','','','344-3411',NULL,'',18),('Jerry','jmina@farsa.es.kk',1,-88.00,'2018-05-29',NULL,1,4,'334-333','Colombia','8-331-9222',90,0,'Mina','Fernandez','','',245,'NO',237,1.90,'','','','','','','','',NULL,'',6),('Vivian','vivileti@mail.com',2,59.00,'2018-05-29',NULL,1,5,'782-2241','CDE-PH New Seal #444','8-2221-331',92,0,'Gonzales','Morales','','',244,'NO',234,1.56,'','Pedro','Gonzales','pedro.33-gonzales@mail.com','455-2234','','','',NULL,'Leticia',0),('Luis','luis.cc@bzk.com',1,77.00,'2018-06-01',NULL,1,3,'452-2313','Valladolic EspaÃ±a. Terranova','ES-9923-133',93,0,'Cabello','Cabezudo','','822-2241',242,'NO',235,1.76,NULL,'','','','','','','',NULL,'Andres',125),('Linda','linda@foz.com',2,66.00,'2018-06-01',NULL,1,3,'323-1333','El dorado #111','8-1722-3313',95,0,'Pinzon','Aguilar','','422-4323',245,'NO',234,1.55,'ÿØÿà\0JFIF\0\0\0\0\0\0ÿÛ\0„\0	( \Z%!1!%)+...383-7(-.+\n\n\n\r\Z+--+-------+--------++.+-+77+-7+-7+--+-7-+-+-+--+-+ÿÀ\0\0¿\"\0ÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\0>\0\0\0\0\0!1AQaq\"‘2¡±ÁðBRbÑáñ#Cr‚’¢$3²ÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\0 \0\0\0\0\0\0\0\0\0\0!1AQaBÿÚ\0\0\0?\0Ì¤’u£Œ‚p˜\'N’gè€æásÐiÕgqéo&íò÷æ¯ª$Ýi7Ð,tÎt¯ wqéÁe›<íâóºÝ¥_a‘dšßêy*¸#µ˜ÝI½¹u*ÒP|°G›Ÿñœ}.²¿Æé:Y\'%­Ê6êt¹è9)T±±3Ñ\\2±04hßù\'ìªßDéŸûº¢Cì>Gqsú¾œQÍ¤ÈŸË\r>hÖB\Zl8¿?4DGÈðpN–•nÃ®@ú|…þèÚ¬Üe¡ë’Ôá8mÍí§ðVõ8EÙ¦–UBÇ—GBèŸvèQå·ó~wW54cx´úwâ«ªi‹;)´z€›ÂÚ,/½ï¨×ø¢êÄ~uBÌ3ßuDËp´“coÌ×Wì‘çùÁpš@{NŸNI¢xú›uzVVDè_¼4\'‡`-@{G±ä«Þ1»âùYƒT˜ÞbqÔÿ\0B´Æ²òc¸Ó’ â ×$÷dµÛ›H=Èw¹JG!Þå6œ†{ò9;Þ†{ÔZ½Þ¸½é=Ë‹ŠªC—$¸¸¤™¶©$t¹ˆ\'	á\0“9Ip«’Ã-~R§¸íAøBÏ:ky[§Ì®ø•EÉæJ\Z œô¬®¼f¢Þ‰¡‚çR.z\0¬ö^=ç:Wjã—F…Fò\\-ÅÄ4vâ´”¤1€†J6¥„£Äu†ÈÃJ†O¥ÇU,npÛ4pDV¸W±ùºü÷G·™Z`í¹/á Y¡/\'êâ¶X<ÇÄØžÁž1¯Ái²ÎªõôþT6àµ³6þjÌ7%x¢öÃí.o;uŸN*—Ã2üx÷â½\Z®šã5çøÅ+©¦Þÿ\0	çý¥F\\+YŠè7	äUc²6áÃ§E§Å¡Þ^K\'^ÙNô»aÄ\\°ñÌw\Z…UŠFè&ß\Z_ìSÅS£›¨Ïù+\\Z!4!ã<³üîS•^Ò·†½¸û!q(î­Õ§0>i¨eÌ´õÕv‰Ùî;d‰tV-(ê7˜Ì)½Ê¯vé{8_xzÞÿ\0D|…m+—,uP‘Èw¹IîCK\"›DˆÈä3Ü¤÷.*U¢s—\'9\'¹p{ÓÑÏL¸9É+˜W¡§Lœ-œÇN\'@:©Æ*7oþQíüþÊÙfv’O%ÿ\0lü†Šré§Žn³“¾ä•ÒŸ n{\rf@]âø YÞ1cMñÝ3šÐaLñÑ¹,Õ;­sÌ­vÌGýÙw3eÝ- ¤o%“Æk¯)â´­gƒ¡#tw:•ç¯¨.q7Ì›vLÚž¦/y‘Ù5—$þñþÓ6^„ÿ\0îsm½mÐu\rà²».k\Zç\r¦ö¾r?‰w&¯U¤ðì,GKY<q+–¸w§ÞÊ\rpàº:@®D\ZFä©ñ\\9³0±Âà«wJòHÑÓ³bq^i-éÞb]‡àw1ÉTbØ]Á^ÔS>72R-n\ZƒÌ/;Ÿù÷‘ðwêî²¸é¬Ël`0Éž@›Š÷gªw·¡\'#§r?Am-\\2ßwSÌ{*L>´°ƒÅ„iÈ‘­Äï‘Õ1îHx~UÑ³yÁý 7»è‹Úh†÷ˆ>ñþ¡uVÙ.?Êá~Ä::°‡\'Žzz#äš±ÃC‘î»½Ù*Æ±òG9„‘Ë¤®C¸¦„\\W¹Iå÷\'Ò=÷©=Ëƒž¯rÎN¹’’¥=1:`hãI )ÐhOðžË+´Ç8Ç >KU(Éfv¡¿	üáüTäÓÇÛ8Ó×ZqÇ¹\\xÎ rY×L2µÖê\n_š+|E ñ»?¿Éb¨£ß{ûNk}7…þWZý«Úb‘çº\0Ì6ÂÙ¬ñÄë3´X“œñ~öï°(œfß0ÞÊÜ/Ëš¦Â¨;îokÝÇšôZF(@`×KsòUÑ…‡ty¬®ðêÙ˜G˜ÛºLnñí#4ÑN×èžDläo°Ì[y¢ú«Ô_0¼ö‡!Ö%k(%s‚[+Ž–RUê©1ÚÇf”u\\nú,Ý|ä›#ØäUUÀ_«ŠåÍïüDÝtÄ±VSŒš^ó£[™\'†K9Uµ˜²ÖNãÈ\0s!TÆÒË)\Z\Z­Ž§-µ³¶¥yÆ=…šyKN„eÙ_Ðm”îpþ‘kõ¹à‹Úzo\Z=á­¸~‰t;Šxfñ¨ÍÍÝ	®éÓîCbáÍ—Á¸ú£ph^Àœ9åÅ®ú”€	\07Û½î-÷ù%!,à;Ì p!Á#¾ßu_„¿Ké˜FH2·%Šœúp‘ß–B½Ë´¥!W¦H=Èy¤÷!Þår¶‹Í×4÷HªZ	$’ÓT”S…£$á2pƒ¤³›RZ\Z9’l:]h‰X¦™»|Ôä¿jêvÜß€â“E‰¸â®p/Äa{¾ûÙw¨¡nðm¼àz\\,·Ë®côØ#ò-}ÒO¶õ¿úù\'—gwžMÉ»‰9e™à´Ønæ¹Û¿ª×<m¯àZì+\0…V£)„1å—aŸ U´óT>Àº6\"áÎîW®K³¬9†‹õR‡l_áß³OðWŠo/2~Ïµ’¸-˜.{³qëšá“Ã!|1¸7#»™Ìê/ÆËÔß¸NQF¸}‘””R;HÃG2´Ë<léž8e.íeð¼5ò1²¸üMw·[-IúHÑw‚ŠÃ<ù«<\Z×¬šmn†ÌÉañL*MÒcqÒù|×§âl»U©A^Ê^qƒà…Î’@ã æ?ûmÖÊIÄ,@³¸{èà­w1Ø¡]€[õ;Ô­&vNé»¯8—`vH#®£¯5¡ ÙÇîå\ZE°ƒÝþƒêˆ,²Ë-Ö“1ììlk¬Ð.9p^IQ–Kž­ Eôh/$ÿ\0ôÑÚ`.×Ÿgrû¥ÎXì;\"áÉÇç¢³”åó÷AP‹¹ç_(÷É;²íd}N]¡$+¼ŽAÊå¤Œ\\¤râS¸¨k…dŠI Ñ))Ù$¤§Lœ+qœ\'L\n:ÇmE1l¡öÉËb¸ÕÓ6Fî¸\\Ì¹%UŽZ¥G†–QÀá£ãkwŸš½¡ÙÐ\"kŽ¿ºÛÊJí³ÔbJ6Á}çÂ]`u,&í²ÒÓ·z=ëZÞR:€±³—g¶ðáU@ËfUÕš*†‹dŽ£vaB°œ4”Íº1´÷@ÑN›jš®\nfÒJ~\nƒëF—C¿hê-\re8]áhJj²áptÒwI±ÕBí*®(Õ¤Ã%Rç–›eØ‚RsB¬ŸsMœßP¡ýªæQr‘ˆ*†&5àñú.Õ‚•ªŠª÷f«6ž²R¾2.^[ÑÛÀ‚;fŽ«uÊ„31Äð.MÍ²¶%²íâ±Ò˜ä•‡ôÓß/µõú+*òÑ4¡†íß}?6J¢¬þz¢NXÛ¸\ZW ¦rí;nrÖ1)‰L’£8RQj“IÊI¤%tÉÖ›q¤¨„à¡I\'L¤Kžªð§cµÔÎà‹+êŒnÎë7¼æäätYH_ºàá¨ ûf´¬Š7†È\0±-µóqµ‰à§,véðå9•Ýæù5S¦–År¦sNóZtÊÜÎ»\\±É¾<4Õ®æºÃ5ŸŽ¦Âè*ÌNÝÒ•kúœbÙ]NŽ¡ÎÍf¨£sÎó´ZŒ=¶ÑT+’â‹dmóz bÛL¤G#Nî 8=îé›‘±è°®Ù_	Ûí®Vy„})1¯Y“lYlˆù!)ö¦#\'™ÀD‹û/9¦c‹¬AÊ±¥ÙXÜðó}íI¾gÕNíUÆ7•5ÂSåÌ[2³õ³Š¸£¥Üm€ÉVâð.Bi—A#Ä4GýU”œ¹ŽÈÜ~j‰Š ‘u6´ÛBÉ·Š©Úf6:yäÝý;ÜÞña÷>‹­+Èî<\ZOÉg¶›dÔEÂæfd8†°Üú]^=0Î±MvhJ·æ9®ÁÙªú—¢vÏày^¸9Iî\\Ö†tá2t‚räÉƒ$“\'@z:tÉn=)3I:ˆN€DÕ9±Ï‹ÄaÌY¡Ä€xpáÁ¬¨©ä–×\0Ö&émÜâÉ®¶Y].®ÐÙW<I!yÍà^úq¿ªÐËÂÌPÍ¸ð}=Ö®áËæ««ÇŸ·jš‹Œ¾ˆBàýãé÷Z©®Q†âÇÐ¬ÚíWöÉ^ÐV°¸Üªjýš24†ÈæmPuYûv²9mPòXNNgÂG[fÕ¬…Ç×©K´Ú/ÛEQ]R¸ñ÷9G³QÈÛ™I ðvªö“biÜðãrMÛ¼lUjþ‡äñãÜ¡¿µi:ßóŠ2—i`oþäQØš@ðíÜ·~ãkÜf¸K²ôm{Í…‹FDü=B^·ô??Šÿ\0š,m„ãîU~ÓÀ÷5Ä¹ÆÀq(z¬*œ³rÃÜAh#A{æJ;dv2*P^Fô®ÕÎÎÝôJÏÙß[7&€šMó›H¿Þ:Ù¦©§hÏ’¯s‚Ê”ªÌY»”³žQ<û¼üo·.ðØZùƒC‰Í-¹ÞnwÏ6ž‹g·u{”oYØÇbwÿ\0‘ê°’í!4-¢ðÛf¸»‰»Ëóê	·exÆ9v¢•ÖîU|®¹]§‘\n÷+Æhâ*)Éƒ©(©]0R*7N€IÓ]$£\'	‚p©Êt“\'L)0DÒV÷¬ÐoÎùywBÝ$‚3<Úá_aÕ™ŸuBüÈ]Y8i:s¢œ¦ãOÕl›>ðêŽÃj¼¥déêˆ69¨*ê’\\Á:Z–ÁdŸZz*®ÐŽd7UlÅE4-ø¢é§²Õ4àÜo°é“ŠÖÏ†’©«0\ríZ=•ûdß,ÅI®„›’óÃ7É–2ãÍÅ\ZÍž³õ²µ¤Á÷x[Ñ/j¼¼Øüè(ô$[ VvD²•FQe7–gr¼ªñ\'\0Þª‰òÚêÇ›;,–Ðãßõœš:óìë”ïLÞßâ^$„£Ÿóºß@>k+‘UN$’MÍó\'‰â«ç*£7Jêo+™VfI$)’@Né]A=Ð)(’ÒBp¢…NT’L82tÁá2E*kuQŠUyÚ/£‡½Ñõsnµf%q’Až@‚~Êmi†?^§ˆÒ‡Yã#aëÝB†£È£ÚÛ±½Z>UÖÓ–æuÓ:i0ìBÚ•£¤¯ËÍ ®¾\\QÔ¸¹iÏê#Ôb—’êÒ°ôxõ¸uaÑ7BULâ´Ó—\0›ÄYáŽ·P…“hAJå­hç¨\0*<C\r\ZªŠ¼rüU<µ†CÐ)ÙY¡sÌ^{¯>Ú×Þ¡ü›fûšÞÒ6æëÎ1©·¥”ó{¾Fßdm)g<}¢\nr‹˜ÞýU{Ÿª¨˜ã#”œU$’H’I I$€I$’Ò¨]JêœÉ¤¢GN¹¹à&lš ÝT%—782U¸¥VàÔ—‚V«°xÍcœï\r¹ž$hˆÃpï3[©.îM¬¸Ó@bïˆæxØrWÛJf©ßG˜õs®\Z>þgvÛñ½\r°¥½óGÍ»’ææ¥ZFj¾šÆãÝù…i§ŠüEe-’VÕB¨Œ®¤+]Ísš?†…Å“q\'~×ÍM•R?+ä‚Ššü­,6H«´4¤æâQ±EÀ”˜ÄD-BO”Çè¼˜Ôoo;™¿¹^¯ˆË¹ŽÏÊÇf’¼ZŠKeùÑ4^S›ê€•XÎ¿ÕWÎÛdUâN¨©¢¨‰$’@$’I\0’I$I$Šžê\0¥uNgK§/²…×93  \Z9nIêBç;ñ7QÃšå,¾ú?äBé%@m‰Ðåê’‚ÿ\0j6Ç;¨#0Ti£>#³åuYW»,…Ç&´Û-IVû?‚ÍVï\r‡v6Ø8—fÔ•6µ˜Äè(ŸU\'‡½õ<\0æO%êX&ÊhDm×WÚwˆÙü\ZhÃbmº›“êJ:N*UÍ\nNb›š’fP3F­eh(\'ÂuFŽU]*À<ÕåHA>1}ØÖ9Ã\Z³¥ŽÈXÂ°‰œÔŠì\"®MÏ‚%¹âj2´ˆ¸9/(ÚÝ›}$›Í‰ÇÊy~é^°5EOHÙXXö‚x$S_#¡æ¹ÏVÔ|ÂÕívÅºãÂAŒ›M‹	à/¨Y!?ÕB	#-ÛóEÉ#yhx!ÞÞJÉÍ$’@$’I\0’I$I$ÿÙ','','','','','','','',NULL,'Maria',169),('Andriano','adriano@mail.com',1,77.00,'2012-03-15','2018-06-01',1,3,'772-3312','Samaria 23.33','8-231-444',98,0,'Mejia','Varon','','',244,'NO',235,1.77,'ÿØÿà\0JFIF\0\0\0\0\0\0ÿÛ\0„\0	\r\r\r\r \"\" $(4,$&1\'-2%1,.110#+483-8)9.,\n\n\n\r7+7-77+-7++++++-+7+/7-+-+-77-++---2++-++-++7++++++-ÿÀ\0\0È\0È\"\0ÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\0:\0\0\0\0\0!1AQa\"q‘¡#2br±ÁÑBCR3’ð²áñÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\0\"\0\0\0\0\0\0\0\0\0\0!1AQq\"ÿÚ\0\0\0?\0œb\"\" \"\"\" \"%,À“æN\n¢rúÏév|Â¥å6uÿ\0B7yPŸ,	ñWm×•‹%Š-­<ã™€©\\à@ú\Z\'Ég´aºßÕùÔ²ºýå`[Ûk3u!ëÔ9ûÉ3‹¯®¢|™¤öƒªY84îœ€}êuÕ¤Þ˜2màî×,/‘VåÅ¥Ç/âÚ‘?¥¿Ì¨‘¢Z¡]**½6Œ9‘”†V`Ë°ÉÁñ_jÚvœïH³W¬¤«%5+yÐKÝ§ñµ=*Ù•Xº‹ËA3ïíÌ}ù~«åŸ,Ù,ù\',ÙñùÀ“øƒ¶{ûœT[:}y‰ï¾»NXâBï=ýåZ€ËÎB}ÓQIK’I\0è«äß(ÙÄ½qo€<ùãÆ\\µeÆ¨ØL†Æ2!ø±˜ë,aÙÒ|%Údañú±!ã=KLdg¦Òí•]ÈÎÕÇé#jäýXK Ê§|×ÌË–ô«ŽŒ¸8#óq´Q®ÉÊŽ=Ð\n¯¦NAšõCu[£ÐÊÅ\"vYÚ+i¬–—6P„lÑ\'úÌú&@À2œ©t\"|^î¸Ï®â}#Ø†°÷Zo-L“F³PF\'ó&LâQ!DD¨DDD@DDD@DDðÏbÊªêUnuk£Tr÷-ÜSQà‹ãóë8÷«’Iß#þä‹ÛÍªÓÕ‰Q‚öÔê?êmÆ~Ò9D$€O@d\\WEIÿ\0.•¡s€?Äê8kƒkUÃ:òŽ¸l©3¹µàª`ø€ÛO-üªÖršxÓ=Ê4£¢T¨U=â}w™íÂwD€3Ð&M3H§D.Qr63d@òôè\'ž|»~7‚¨\"Ë….ª\0R–PøçòË÷œqLÏ€ÿ\0—¤›Ñs€Ã—@ã¤Æ|›}Xá¬tùâçC¬[”¦ã\0yây_‡k¨!Èm·“­Æ‘L·7(Îyºn±y§!oŽÑ>e¡cÆ¤¾¹²p2W{bNýk6ÆÑìÔòÜ­F¬ààwª|Gž$yÅ–½Ñjd„âj»<½ömRÊ®yG´*6<›cüÏw$Þ5äæãŠNCëˆ›Úˆ€ˆˆˆ€ˆˆˆ€ˆˆ7öíH\\ñì´€ûÍwZ¯:û£9Ü•ß)ÒvïlF¥A¿çjÌ&«‚íËUs…vÛé<žM²²õøÕÙÔ›oIp6ÁûÌÔ\0xL*3i‚g\'^éW+A.­)P¥3Éa«L%^d”÷&0‰cÕcÔ6¢LZÝ&ÎßhÝ—#Ðà‰Ø~zL7å¸B:ïïI#(žPÛò÷ˆãã8K*\\÷vÈ3–¯M2Ïæßë/“_×Ö4(§ÍAûK’”\\\0<†%S ð0µ[áoIª-Œ\0V\'ÂIœ’#g!v÷L{]“oÿ\0e—ÓóJønÉ-é)\0#,e®ÕõF¼6€Û”ÅÀùƒ/ÃÒlª[¤6%qŸ)Íò¯Ìž/\Z³X˜˜eQÕ)+r–9¾±¼¤ÿ\0•Áy6nv|31_<€LÊn®ƒ4+óÈš«Jÿ\0,íiþŠºøõö$q¦[ÜÐaÞ9lu~ó°³¿/ˆøÉí8ž“õµäÆuó˜7—‰Éë¶µ.Ù=yŠÇ¼nI—Wy{Iz¸14×zÍ\0qÎ¾G~““^ ÍK­±Ð2«~óT5<2eµk1ô®·×”’²2‘F$}Ãú;&·i@hGÏèÿ\0S´Ñ´§¶™réœ©?éôš{GîõÚN´Ú£-È‚€wÜŸ)|kzÞ{éyëíN¾§¹ìÔèšÈº.¼¼Ž„só\rüŒÛN­m‡.ÕšÎI$\"\"\" \"\"\" y5\\GƒITøÕYµ˜:Í¿yEÀüÀs¯ÄM|‘3I†\\s–‰”UÅéø<Mí?þSq¦Ò úL~ ¦µ-s‘•¨•OÉ¥í(ì\0é8³òÅjÖ¹¢Áwë¾Âiõ^©U(=*…ªŒ­nõßœäé‰ÝÒ·¬¾¶Ëå6Rf;k´Äôç4MÑJ\nNY3ßñÊÔÉ\'§¦3çá7\0W3*¦Mr·;çÊcÉe«*æ $IcÞ:‘ÿ\0lc)–^sã’&UÊãe[\0ÂcYYøà-ø2°¸R±4yË°Z•R±ëÍŸ‡Òl¬4«šu[5yé“îsnê<‰ñ·r<¥@xM·ÛC\nÛÕ¦­@*ç©ÄætK~kû§ð[zië’IF¤\nƒ4Ü(«ßÝ»‹ÓOÏ÷5G[\r¿šîxV˜TR¹?)¾šÍž()ÿ\0–[å6s³Á\\ãˆr¹go2DDÚÖDDD@DDD@Oö E¼]l)½Å?W¨ž›fQÃ•*°9fv\ZþˆÕj\Zª½àj}Û®@að‘ß»Sæ¢à«S¨ÔŠ°ÃÉçâšÚg:u8y\"ÕˆÞÒ»t—XÌW™ ækÝƒ;b^e†SÒS¦Zòwñ‹ÂÓáë‰­³¤Q#¸\'vï*š™˜õ¬£qº½¶\\lA–lPÓÊŸÜ³æ—8ÅGn»ªi¬Èµ%qœà·3|Ì“›§xØƒ=©SQÍã,\\>Óg¶0Íjõª™Ÿáð>|oRµF?\\âgkõÈ¦ÁFXû¨<YŽÃîgE£ðÃ­\Z4ê@¨¡ðK1>2SŠÜ“Ô3·%iÏnªÖŸ\"\"ù _´½<ÙÙŽL‘(DDD@DDD@DD$CÄTý›X®<*ª\\!øŒõKÒ(í„\n7VUúN¥&õÃý™£È¯µ%¿Ç¶]»²­ÍƒŸ	“sx´‘Ž\0>s™Ó5$Â¶r¼z	²¾«ÞŠj›ó6ûø	ÊøèL1¨ÞÕ¸b@îéóc˜‘ÿ\0¤ÌÀ”Ôç˜“Œdcå0®¬j9I\n:€Àb_¶ÒŒ–|þóX–ÈˆÏ¬¾JO¹cÍç·ñ-½V¤	R*(ßc‰èÑéIæ\'÷´Æ]+Þ$áÎw‹W¿–ÒÊðT\\ŒŒ08Ê™íÝLóã0iÒ4«q”) øŽ“RÔ”°QæDÅ†-ÚqkG¯ãwÏðMÿ\0œIREý™§´^×¯råOF9?a÷’„êø´õ§öçù6Ûÿ\0Ob\"z^r\" \"\"\" \"\"\" \"\"E½»ª{POâ\nÎÈ<q“÷µâ^-²Óšæ°VÆR˜÷«?Á¾’$°ÖÏjµ‹)§@iõh[© šy#}IÁùDÖf³“Ððåû\ZoM‰\0ˆ6Ìí4MD\nýØèœõñ>§Mì®ªS¨¼¥IìGY²¡ª„ª*)$œŸÊ¢rù8ûéÒ¥ºí*L¢µ±}¹ÙG£97Šv¦1÷ÜØó3}KTWÏ/‡_Iæõ˜lÕûM9”g½vóË™™ÉË1©ji¾ù!O4×j<DªJ ÉõÎãÎY©*õÍEiŒƒá¼äuQE6¨O#ò0ÁÆs0øŽÿ\0™¹ÙÊž\\6Té4:}­MVæ¢lÎøT™¾“wÚa…ù\"°“;×mûº¶LJ^\Z†ä†ÀïÕ€9_<	,O™ûP·öF“Û“H­½&¤T•d)î‚ž\0’Gg½¬P»T·¾aBëdp´nžÒ}Þ^S¯éëåÍ¶u(Dðìöö•º\Z•ª-$YÜ\"™‘5âÙtë|­¸{Êƒ§ ä¥ŸÜ dk¯ö­ª^eR ´§Ó–ˆ!þnwúbX¬¦§Í‰lôäçº®´ö÷W9¨ÿ\0™ñwl×¹©Ø\'³ÓéÞ¸\r]¾¢ýÌ‹ëÕgbÎÅÜîÌÌY›âLµ3Šâj«Û·¬ìõØó33v>¤ÎÓ±ËŽMEAØ=*”þ}Gñ82zÍ·ß›[ŠU×9§Qj|qÔ}3-{‘;ñß\rA;ê>íÊ¯¼6Åaþ™^[:3SÃ¨¾r9}1>™°¬µ*!Ê²SæÈš^/à›}MI S­Ô8ßôi£—‹½†þ>\\êP%ž¡¾n_O\0fãþ°Ù!€O½ÓÞé¼ó]à««G*W ï’ô3Dú=nÝBˆCƒ<“™íé‰·ãWˆŠ	;¹ÉÃÇ3§)`ÇcËË‚v\"jÿ\0éuNÝÅCñC6\'sYÂŠer}Ñƒ“ð˜å?ë-³\\õê]ºÓEg$ò¢Ëy	5ð\r2‰z€5ÕAø§oÃOÜÊàN§§(¨à=Áv\"Ÿ õõEÙÀÄ÷pqço\'-÷¨|ÿ\0ÛUPo‘GU¶@ßI‘á\'ßûUõÍ\\åMfTéùWaüNp‰¾ÿ\0Z!ÙðhúŽ„Zý·uT—P?Iê¿Ç¤™¸Wµ=>ÿ\0•*7²W;rU +ÒýÏæzg_Æf>±&¾Ë3ÙòŽ‡ÆZŽŸgºpƒý·=í/üOO–$ öâ6[ëR<\rJ#æ‡ú3¬Âêh‰Ïh<k§j÷HÎÛfîêÿ\0âwúN†b¤D@ƒ8›¶ú¯”Óè\nc «XˆA°ù“\"ýcZº¾~òê»×ov$/Àt)‚Vlˆb¥V^\0CxÌâ·3Ò0#1S¤dÛlDÇAÿ\0»L‘´•%ô/dú¯`ˆNZ‹š\'öu_¶ß) ¦â@Ý‹j\\—U(µZ$Þ»ÿ\0“µŽH!jöÍ*¯+¨#ÃÓá9«\0!0ãÄgJ8óŽ©i¿€Ÿ‰tÔË…÷Kÿ\0&þ¤WGŠu-Qn*Ç0©DN³TøÕå³ey­O‰VžŒÇbœ€x°ÿ\0îotÍ-(î7b7b7ùyHSNÕu\n”î+Ô«Í@Ó!{ÛÚ‰–B€wùÌÎí)­XQºVö&*)¹$›R~;•þ\"ž8û¯r·ò-~¥8·º\'+Æ\Z‡qksW;­+ñÆÜÎŠµ`Ê\nAÊAÈ\"G¯^÷V™Þ­t§òŸàMÜpÕdp7þf+	”Ç&Y¨žRÚ’^Ð2¿ÿ\0f0«“Â³Å2¹E¢¿äNÛ…;QÔtîTvöºnJ¬K¨ý/Ô|ó8Ö‚$˜Ñôï	ö‘§j|¨µ;Šçýš¤#“úOFùDùx¬L=WWÂÆ%l?‰ç”ÛˆJL÷3Ç0)ü§„çaçô•.ûBÒÇÆAJ¤¼:OebZÂ7|¨û5í­RpáþÒp~Æ}9^¯\"änz(ó3äš}3Â:‡¶ÙÚ×=MVýãfû‰m\ZB=ãÞº­V¥Õ«\Z­Tfá[å‡ü}LxNK©Ý³°®’¹+Ýo‚0|gÓ5 Ž0¶)¯÷t¨-BjÓ®Sœ²äçÊZÉhh8®â‚÷vÖ´ª+‚‰ï*dytÇ¤é;4ìûÛ9nn•Å²ïF›e} ùŸÑüÌNÒ[UÖ*šär+3ÖUÆü¾€ì$ýJ˜P\0\0ÀZ{!f’òO\06òí³Pæ¯on:%&¬ÿ\0¹ŽØ}äÑPí>hãKÚï®j±ªQ=vÄµYsØûÄf{\",Ô@7ÿ\02œŸ„Èc-5?)ŒÂ¼JÖP¨Dg€J J)+è‰rëž³ÌOb%T‘Ò[oˆ˜é¨Ç¬¸b\"ìDJMŠê½å­krw¥[còÖ\"\'ô„’Çikz°¾Ö*V¦S8·£Ýä=dïQç¿Ú\"(YWfZå+MQÑQ]šØ3yN}ÜüÆ>r~FÈÌD–Xiø·Qö[;šÞ)AÊþì`}ÈŸ0Õ?X‰•~$¨Ä¤œ|OH‰Œ«Øˆ„S‰P`ô	LD*±ã$ÿÙ','','','','','','','',NULL,'',19),('Gabriel','GNUNEZ@BZK.COM',1,78.88,'2018-06-01','2018-06-01',1,3,'342-2313','VILLA LUCRE SAN RAFAEL CASA 342','7-313-313',99,0,'NuÃ±ez','Varon','','',247,'NO',238,1.98,'ÿØÿà\0JFIF\0\0\0\0\0\0ÿÛ\0„\0	\r\r\r\r \"\" $(4,$&1\'-2%1,.110#+483-8)9.,\n\n\n\r7+7-77+-7++++++-+7+/7-+-+-77-++---2++-++-++7++++++-ÿÀ\0\0È\0È\"\0ÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\0:\0\0\0\0\0!1AQa\"q‘¡#2br±ÁÑBCR3’ð²áñÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\0\"\0\0\0\0\0\0\0\0\0\0!1AQq\"ÿÚ\0\0\0?\0œb\"\" \"\"\" \"%,À“æN\n¢rúÏév|Â¥å6uÿ\0B7yPŸ,	ñWm×•‹%Š-­<ã™€©\\à@ú\Z\'Ég´aºßÕùÔ²ºýå`[Ûk3u!ëÔ9ûÉ3‹¯®¢|™¤öƒªY84îœ€}êuÕ¤Þ˜2màî×,/‘VåÅ¥Ç/âÚ‘?¥¿Ì¨‘¢Z¡]**½6Œ9‘”†V`Ë°ÉÁñ_jÚvœïH³W¬¤«%5+yÐKÝ§ñµ=*Ù•Xº‹ËA3ïíÌ}ù~«åŸ,Ù,ù\',ÙñùÀ“øƒ¶{ûœT[:}y‰ï¾»NXâBï=ýåZ€ËÎB}ÓQIK’I\0è«äß(ÙÄ½qo€<ùãÆ\\µeÆ¨ØL†Æ2!ø±˜ë,aÙÒ|%Údañú±!ã=KLdg¦Òí•]ÈÎÕÇé#jäýXK Ê§|×ÌË–ô«ŽŒ¸8#óq´Q®ÉÊŽ=Ð\n¯¦NAšõCu[£ÐÊÅ\"vYÚ+i¬–—6P„lÑ\'úÌú&@À2œ©t\"|^î¸Ï®â}#Ø†°÷Zo-L“F³PF\'ó&LâQ!DD¨DDD@DDD@DDðÏbÊªêUnuk£Tr÷-ÜSQà‹ãóë8÷«’Iß#þä‹ÛÍªÓÕ‰Q‚öÔê?êmÆ~Ò9D$€O@d\\WEIÿ\0.•¡s€?Äê8kƒkUÃ:òŽ¸l©3¹µàª`ø€ÛO-üªÖršxÓ=Ê4£¢T¨U=â}w™íÂwD€3Ð&M3H§D.Qr63d@òôè\'ž|»~7‚¨\"Ë….ª\0R–PøçòË÷œqLÏ€ÿ\0—¤›Ñs€Ã—@ã¤Æ|›}Xá¬tùâçC¬[”¦ã\0yây_‡k¨!Èm·“­Æ‘L·7(Îyºn±y§!oŽÑ>e¡cÆ¤¾¹²p2W{bNýk6ÆÑìÔòÜ­F¬ààwª|Gž$yÅ–½Ñjd„âj»<½ömRÊ®yG´*6<›cüÏw$Þ5äæãŠNCëˆ›Úˆ€ˆˆˆ€ˆˆˆ€ˆˆ7öíH\\ñì´€ûÍwZ¯:û£9Ü•ß)ÒvïlF¥A¿çjÌ&«‚íËUs…vÛé<žM²²õøÕÙÔ›oIp6ÁûÌÔ\0xL*3i‚g\'^éW+A.­)P¥3Éa«L%^d”÷&0‰cÕcÔ6¢LZÝ&ÎßhÝ—#Ðà‰Ø~zL7å¸B:ïïI#(žPÛò÷ˆãã8K*\\÷vÈ3–¯M2Ïæßë/“_×Ö4(§ÍAûK’”\\\0<†%S ð0µ[áoIª-Œ\0V\'ÂIœ’#g!v÷L{]“oÿ\0e—ÓóJønÉ-é)\0#,e®ÕõF¼6€Û”ÅÀùƒ/ÃÒlª[¤6%qŸ)Íò¯Ìž/\Z³X˜˜eQÕ)+r–9¾±¼¤ÿ\0•Áy6nv|31_<€LÊn®ƒ4+óÈš«Jÿ\0,íiþŠºøõö$q¦[ÜÐaÞ9lu~ó°³¿/ˆøÉí8ž“õµäÆuó˜7—‰Éë¶µ.Ù=yŠÇ¼nI—Wy{Iz¸14×zÍ\0qÎ¾G~““^ ÍK­±Ð2«~óT5<2eµk1ô®·×”’²2‘F$}Ãú;&·i@hGÏèÿ\0S´Ñ´§¶™réœ©?éôš{GîõÚN´Ú£-È‚€wÜŸ)|kzÞ{éyëíN¾§¹ìÔèšÈº.¼¼Ž„só\rüŒÛN­m‡.ÕšÎI$\"\"\" \"\"\" y5\\GƒITøÕYµ˜:Í¿yEÀüÀs¯ÄM|‘3I†\\s–‰”UÅéø<Mí?þSq¦Ò úL~ ¦µ-s‘•¨•OÉ¥í(ì\0é8³òÅjÖ¹¢Áwë¾Âiõ^©U(=*…ªŒ­nõßœäé‰ÝÒ·¬¾¶Ëå6Rf;k´Äôç4MÑJ\nNY3ßñÊÔÉ\'§¦3çá7\0W3*¦Mr·;çÊcÉe«*æ $IcÞ:‘ÿ\0lc)–^sã’&UÊãe[\0ÂcYYøà-ø2°¸R±4yË°Z•R±ëÍŸ‡Òl¬4«šu[5yé“îsnê<‰ñ·r<¥@xM·ÛC\nÛÕ¦­@*ç©ÄætK~kû§ð[zië’IF¤\nƒ4Ü(«ßÝ»‹ÓOÏ÷5G[\r¿šîxV˜TR¹?)¾šÍž()ÿ\0–[å6s³Á\\ãˆr¹go2DDÚÖDDD@DDD@Oö E¼]l)½Å?W¨ž›fQÃ•*°9fv\ZþˆÕj\Zª½àj}Û®@að‘ß»Sæ¢à«S¨ÔŠ°ÃÉçâšÚg:u8y\"ÕˆÞÒ»t—XÌW™ ækÝƒ;b^e†SÒS¦Zòwñ‹ÂÓáë‰­³¤Q#¸\'vï*š™˜õ¬£qº½¶\\lA–lPÓÊŸÜ³æ—8ÅGn»ªi¬Èµ%qœà·3|Ì“›§xØƒ=©SQÍã,\\>Óg¶0Íjõª™Ÿáð>|oRµF?\\âgkõÈ¦ÁFXû¨<YŽÃîgE£ðÃ­\Z4ê@¨¡ðK1>2SŠÜ“Ô3·%iÏnªÖŸ\"\"ù _´½<ÙÙŽL‘(DDD@DDD@DD$CÄTý›X®<*ª\\!øŒõKÒ(í„\n7VUúN¥&õÃý™£È¯µ%¿Ç¶]»²­ÍƒŸ	“sx´‘Ž\0>s™Ó5$Â¶r¼z	²¾«ÞŠj›ó6ûø	ÊøèL1¨ÞÕ¸b@îéóc˜‘ÿ\0¤ÌÀ”Ôç˜“Œdcå0®¬j9I\n:€Àb_¶ÒŒ–|þóX–ÈˆÏ¬¾JO¹cÍç·ñ-½V¤	R*(ßc‰èÑéIæ\'÷´Æ]+Þ$áÎw‹W¿–ÒÊðT\\ŒŒ08Ê™íÝLóã0iÒ4«q”) øŽ“RÔ”°QæDÅ†-ÚqkG¯ãwÏðMÿ\0œIREý™§´^×¯råOF9?a÷’„êø´õ§öçù6Ûÿ\0Ob\"z^r\" \"\"\" \"\"\" \"\"E½»ª{POâ\nÎÈ<q“÷µâ^-²Óšæ°VÆR˜÷«?Á¾’$°ÖÏjµ‹)§@iõh[© šy#}IÁùDÖf³“Ððåû\ZoM‰\0ˆ6Ìí4MD\nýØèœõñ>§Mì®ªS¨¼¥IìGY²¡ª„ª*)$œŸÊ¢rù8ûéÒ¥ºí*L¢µ±}¹ÙG£97Šv¦1÷ÜØó3}KTWÏ/‡_Iæõ˜lÕûM9”g½vóË™™ÉË1©ji¾ù!O4×j<DªJ ÉõÎãÎY©*õÍEiŒƒá¼äuQE6¨O#ò0ÁÆs0øŽÿ\0™¹ÙÊž\\6Té4:}­MVæ¢lÎøT™¾“wÚa…ù\"°“;×mûº¶LJ^\Z†ä†ÀïÕ€9_<	,O™ûP·öF“Û“H­½&¤T•d)î‚ž\0’Gg½¬P»T·¾aBëdp´nžÒ}Þ^S¯éëåÍ¶u(Dðìöö•º\Z•ª-$YÜ\"™‘5âÙtë|­¸{Êƒ§ ä¥ŸÜ dk¯ö­ª^eR ´§Ó–ˆ!þnwúbX¬¦§Í‰lôäçº®´ö÷W9¨ÿ\0™ñwl×¹©Ø\'³ÓéÞ¸\r]¾¢ýÌ‹ëÕgbÎÅÜîÌÌY›âLµ3Šâj«Û·¬ìõØó33v>¤ÎÓ±ËŽMEAØ=*”þ}Gñ82zÍ·ß›[ŠU×9§Qj|qÔ}3-{‘;ñß\rA;ê>íÊ¯¼6Åaþ™^[:3SÃ¨¾r9}1>™°¬µ*!Ê²SæÈš^/à›}MI S­Ô8ßôi£—‹½†þ>\\êP%ž¡¾n_O\0fãþ°Ù!€O½ÓÞé¼ó]à««G*W ï’ô3Dú=nÝBˆCƒ<“™íé‰·ãWˆŠ	;¹ÉÃÇ3§)`ÇcËË‚v\"jÿ\0éuNÝÅCñC6\'sYÂŠer}Ñƒ“ð˜å?ë-³\\õê]ºÓEg$ò¢Ëy	5ð\r2‰z€5ÕAø§oÃOÜÊàN§§(¨à=Áv\"Ÿ õõEÙÀÄ÷pqço\'-÷¨|ÿ\0ÛUPo‘GU¶@ßI‘á\'ßûUõÍ\\åMfTéùWaüNp‰¾ÿ\0Z!ÙðhúŽ„Zý·uT—P?Iê¿Ç¤™¸Wµ=>ÿ\0•*7²W;rU +ÒýÏæzg_Æf>±&¾Ë3ÙòŽ‡ÆZŽŸgºpƒý·=í/üOO–$ öâ6[ëR<\rJ#æ‡ú3¬Âêh‰Ïh<k§j÷HÎÛfîêÿ\0âwúN†b¤D@ƒ8›¶ú¯”Óè\nc «XˆA°ù“\"ýcZº¾~òê»×ov$/Àt)‚Vlˆb¥V^\0CxÌâ·3Ò0#1S¤dÛlDÇAÿ\0»L‘´•%ô/dú¯`ˆNZ‹š\'öu_¶ß) ¦â@Ý‹j\\—U(µZ$Þ»ÿ\0“µŽH!jöÍ*¯+¨#ÃÓá9«\0!0ãÄgJ8óŽ©i¿€Ÿ‰tÔË…÷Kÿ\0&þ¤WGŠu-Qn*Ç0©DN³TøÕå³ey­O‰VžŒÇbœ€x°ÿ\0îotÍ-(î7b7b7ùyHSNÕu\n”î+Ô«Í@Ó!{ÛÚ‰–B€wùÌÎí)­XQºVö&*)¹$›R~;•þ\"ž8û¯r·ò-~¥8·º\'+Æ\Z‡qksW;­+ñÆÜÎŠµ`Ê\nAÊAÈ\"G¯^÷V™Þ­t§òŸàMÜpÕdp7þf+	”Ç&Y¨žRÚ’^Ð2¿ÿ\0f0«“Â³Å2¹E¢¿äNÛ…;QÔtîTvöºnJ¬K¨ý/Ô|ó8Ö‚$˜Ñôï	ö‘§j|¨µ;Šçýš¤#“úOFùDùx¬L=WWÂÆ%l?‰ç”ÛˆJL÷3Ç0)ü§„çaçô•.ûBÒÇÆAJ¤¼:OebZÂ7|¨û5í­RpáþÒp~Æ}9^¯\"änz(ó3äš}3Â:‡¶ÙÚ×=MVýãfû‰m\ZB=ãÞº­V¥Õ«\Z­Tfá[å‡ü}LxNK©Ý³°®’¹+Ýo‚0|gÓ5 Ž0¶)¯÷t¨-BjÓ®Sœ²äçÊZÉhh8®â‚÷vÖ´ª+‚‰ï*dytÇ¤é;4ìûÛ9nn•Å²ïF›e} ùŸÑüÌNÒ[UÖ*šär+3ÖUÆü¾€ì$ýJ˜P\0\0ÀZ{!f’òO\06òí³Pæ¯on:%&¬ÿ\0¹ŽØ}äÑPí>hãKÚï®j±ªQ=vÄµYsØûÄf{\",Ô@7ÿ\02œŸ„Èc-5?)ŒÂ¼JÖP¨Dg€J J)+è‰rëž³ÌOb%T‘Ò[oˆ˜é¨Ç¬¸b\"ìDJMŠê½å­krw¥[còÖ\"\'ô„’Çikz°¾Ö*V¦S8·£Ýä=dïQç¿Ú\"(YWfZå+MQÑQ]šØ3yN}ÜüÆ>r~FÈÌD–Xiø·Qö[;šÞ)AÊþì`}ÈŸ0Õ?X‰•~$¨Ä¤œ|OH‰Œ«Øˆ„S‰P`ô	LD*±ã$ÿÙ','','','','','','','',NULL,'Jesus',95),('Emanuel','chiqui_emanuel@hotmail.com',1,33.00,'2015-10-28','2018-06-22',1,5,'235-3318','Samaria #2, casa455','8-222-244',208,0,'Urriola','Ruiz','','',243,'NO',234,1.30,NULL,'Celso','Urriola','celso@gmail.com','451-3131','','','',NULL,'Santo',0);
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
INSERT INTO `system_codes` VALUES (1,'GENDER001','MASCULINO','GENDER'),(2,'GENDER002','FEMENINO','GENDER'),(3,'CATEGORY01','CADETE','CATEGORY'),(4,'CATEGORY02','JUVENIL','CATEGORY'),(5,'CATEGORY03','INFANTIL','CATEGORY'),(6,'EMPLOY1001','ABOGADO ','EMPLOYMENT'),(7,'EMPLOY1002','ADMINISTRADOR','EMPLOYMENT'),(8,'EMPLOY1003','ADMINISTRATIVO','EMPLOYMENT'),(9,'EMPLOY1004','AGRÃ“NOMO ','EMPLOYMENT'),(10,'EMPLOY1005','ALERGÃ“LOGO','EMPLOYMENT'),(11,'EMPLOY1006','ALERGISTA','EMPLOYMENT'),(12,'EMPLOY1007','ALERGÃ“LOGA','EMPLOYMENT'),(13,'EMPLOY1008','ALERGISTA','EMPLOYMENT'),(14,'EMPLOY1009','ALMACENISTA','EMPLOYMENT'),(15,'EMPLOY1010','ANATOMISTA ','EMPLOYMENT'),(16,'EMPLOY1011','ANESTESIÃ“LOGO','EMPLOYMENT'),(17,'EMPLOY1012','ANESTESISTA','EMPLOYMENT'),(18,'EMPLOY1013','ANESTESIÃ“LOGA','EMPLOYMENT'),(19,'EMPLOY1014','ANESTESISTA','EMPLOYMENT'),(20,'EMPLOY1015','ANTOLOGISTA','EMPLOYMENT'),(21,'EMPLOY1016','ANTROPÃ“LOGO','EMPLOYMENT'),(22,'EMPLOY1017','ARABISTA ','EMPLOYMENT'),(23,'EMPLOY1018','ARCHIVERO','EMPLOYMENT'),(24,'EMPLOY1019','ARQUEÃ“LOGO','EMPLOYMENT'),(25,'EMPLOY1020','ARQUITECTO','EMPLOYMENT'),(26,'EMPLOY1021','ASESOR','EMPLOYMENT'),(27,'EMPLOY1022','ASTROFÃSICO','EMPLOYMENT'),(28,'EMPLOY1023','ASTRÃ“LOGO','EMPLOYMENT'),(29,'EMPLOY1024','ASTRÃ“NOMO','EMPLOYMENT'),(30,'EMPLOY1025','ATLETA','EMPLOYMENT'),(31,'EMPLOY1026','AUXILIAR','EMPLOYMENT'),(32,'EMPLOY1027','AVICULTOR','EMPLOYMENT'),(33,'EMPLOY1028','BACTERIÃ“LOGO','EMPLOYMENT'),(34,'EMPLOY1029','BIBLIÃ“GRAFO','EMPLOYMENT'),(35,'EMPLOY1030','BIBLIOTECARIO','EMPLOYMENT'),(36,'EMPLOY1031','BIOFÃSICO','EMPLOYMENT'),(37,'EMPLOY1032','BIÃ“GRAFO','EMPLOYMENT'),(38,'EMPLOY1033','BIÃ“LOGO','EMPLOYMENT'),(39,'EMPLOY1034','BIOQUÃMICO','EMPLOYMENT'),(40,'EMPLOY1035','BOTÃNICO','EMPLOYMENT'),(41,'EMPLOY1036','CANCERÃ“LOGO','EMPLOYMENT'),(42,'EMPLOY1037','CARDIÃ“LOGO','EMPLOYMENT'),(43,'EMPLOY1038','CARTÃ“GRAFO','EMPLOYMENT'),(44,'EMPLOY1039','CATEDRÃTICO','EMPLOYMENT'),(45,'EMPLOY1040','CIRUJANO','EMPLOYMENT'),(46,'EMPLOY1041','CODIRECTOR','EMPLOYMENT'),(47,'EMPLOY1042','CONSERJE','EMPLOYMENT'),(48,'EMPLOY1043','COORDINADOR','EMPLOYMENT'),(49,'EMPLOY1044','COSMÃ“GRAFO','EMPLOYMENT'),(50,'EMPLOY1045','COSMÃ“LOGO','EMPLOYMENT'),(51,'EMPLOY1046','CRIMINALISTA','EMPLOYMENT'),(52,'EMPLOY1047','DECANO','EMPLOYMENT'),(53,'EMPLOY1048','DECORADOR','EMPLOYMENT'),(54,'EMPLOY1049','DEMÃ“GRAFO','EMPLOYMENT'),(55,'EMPLOY1050','DENTISTA','EMPLOYMENT'),(56,'EMPLOY1051','DERMATÃ“LOGO','EMPLOYMENT'),(57,'EMPLOY1052','DIBUJANTE','EMPLOYMENT'),(58,'EMPLOY1053','DIRECTOR','EMPLOYMENT'),(59,'EMPLOY1054','DOCTOR','EMPLOYMENT'),(60,'EMPLOY1055','ECÃ“LOGO','EMPLOYMENT'),(61,'EMPLOY1056','ECONOMISTA','EMPLOYMENT'),(62,'EMPLOY1057','EDUCADOR','EMPLOYMENT'),(63,'EMPLOY1058','ENDOCRINÃ“LOGO','EMPLOYMENT'),(64,'EMPLOY1059','ENFERMERO','EMPLOYMENT'),(65,'EMPLOY1060','ENÃ“LOGO','EMPLOYMENT'),(66,'EMPLOY1061','ENTOMÃ“LOGO','EMPLOYMENT'),(67,'EMPLOY1062','EPIDEMIÃ“LOGO','EMPLOYMENT'),(68,'EMPLOY1063','ESPELEÃ“LOGO','EMPLOYMENT'),(69,'EMPLOY1064','ESTADÃSTICO','EMPLOYMENT'),(70,'EMPLOY1065','ETIMÃ“LOGO','EMPLOYMENT'),(71,'EMPLOY1066','ETIMOLOGISTA','EMPLOYMENT'),(72,'EMPLOY1067','ETIMÃ“LOGA','EMPLOYMENT'),(73,'EMPLOY1068','ETIMOLOGISTA','EMPLOYMENT'),(74,'EMPLOY1069','ETNÃ“GRAFO','EMPLOYMENT'),(75,'EMPLOY1070','ETNÃ“LOGO','EMPLOYMENT'),(76,'EMPLOY1071','FARMACÃ‰UTICO','EMPLOYMENT'),(77,'EMPLOY1072','FILÃ“LOGO','EMPLOYMENT'),(78,'EMPLOY1073','FILÃ“SOFO','EMPLOYMENT'),(79,'EMPLOY1074','FISCAL','EMPLOYMENT'),(80,'EMPLOY1075','FÃSICO','EMPLOYMENT'),(81,'EMPLOY1076','FISIÃ“LOGO','EMPLOYMENT'),(82,'EMPLOY1077','FISIOTERAPEUTA','EMPLOYMENT'),(83,'EMPLOY1078','FONETISTA','EMPLOYMENT'),(84,'EMPLOY1079','FONÃATRA','EMPLOYMENT'),(85,'EMPLOY1080','FONÃ“LOGO','EMPLOYMENT'),(86,'EMPLOY1081','FORENSE','EMPLOYMENT'),(87,'EMPLOY1082','FOTÃ“GRAFO','EMPLOYMENT'),(88,'EMPLOY1083','GEMÃ“LOGO','EMPLOYMENT'),(89,'EMPLOY1084','GENETISTA','EMPLOYMENT'),(90,'EMPLOY1085','GEODESTA','EMPLOYMENT'),(91,'EMPLOY1086','GEOFÃSICO','EMPLOYMENT'),(92,'EMPLOY1087','GEÃ“GRAFO','EMPLOYMENT'),(93,'EMPLOY1088','GEÃ“LOGO','EMPLOYMENT'),(94,'EMPLOY1089','GEOQUÃMICA','EMPLOYMENT'),(95,'EMPLOY1090','GERENTE','EMPLOYMENT'),(96,'EMPLOY1091','GERIATRA','EMPLOYMENT'),(97,'EMPLOY1092','TRABAJADOR SOCIAL','EMPLOYMENT'),(98,'EMPLOY1093','GRAFÃ“LOGO','EMPLOYMENT'),(99,'EMPLOY1094','GRAMÃTICO','EMPLOYMENT'),(100,'EMPLOY1095','HEMATÃ“LOGO','EMPLOYMENT'),(101,'EMPLOY1096','HEPATÃ“LOGO','EMPLOYMENT'),(102,'EMPLOY1097','HIDROGEÃ“LOGO','EMPLOYMENT'),(103,'EMPLOY1098','HIDRÃ“GRAFO','EMPLOYMENT'),(104,'EMPLOY1099','HIDRÃ“LOGO','EMPLOYMENT'),(105,'EMPLOY1100','HIGIENISTA','EMPLOYMENT'),(106,'EMPLOY1101','HISPANISTA','EMPLOYMENT'),(107,'EMPLOY1102','HISTORIADOR','EMPLOYMENT'),(108,'EMPLOY1103','HOMEÃ“PATA','EMPLOYMENT'),(109,'EMPLOY1104','INFORMÃTICO','EMPLOYMENT'),(110,'EMPLOY1105','INGENIERO','EMPLOYMENT'),(111,'EMPLOY1106','INGENIERO TÃ‰CNICO','EMPLOYMENT'),(112,'EMPLOY1107','INMUNÃ“LOGO','EMPLOYMENT'),(113,'EMPLOY1108','INSPECTOR','EMPLOYMENT'),(114,'EMPLOY1109','JARDINERO','EMPLOYMENT'),(115,'EMPLOY1110','JUEZ','EMPLOYMENT'),(116,'EMPLOY1111','LATINISTA','EMPLOYMENT'),(117,'EMPLOY1112','LECTOR','EMPLOYMENT'),(118,'EMPLOY1113','LEXICÃ“GRAFO','EMPLOYMENT'),(119,'EMPLOY1114','LEXICÃ“LOGO','EMPLOYMENT'),(120,'EMPLOY1115','LINGÃœISTA','EMPLOYMENT'),(121,'EMPLOY1116','LOGOPEDA','EMPLOYMENT'),(122,'EMPLOY1117','MAESTRO','EMPLOYMENT'),(123,'EMPLOY1118','MATEMÃTICO','EMPLOYMENT'),(124,'EMPLOY1119','MATRÃ“N','EMPLOYMENT'),(125,'EMPLOY1120','MEDICO','EMPLOYMENT'),(126,'EMPLOY1121','METEORÃ“LOGO','EMPLOYMENT'),(127,'EMPLOY1122','MICÃ“LOGO','EMPLOYMENT'),(128,'EMPLOY1123','MICROBIOLÃ“GICO','EMPLOYMENT'),(129,'EMPLOY1124','MICROCIRUJANO','EMPLOYMENT'),(130,'EMPLOY1125','MIMÃ“GRAFO','EMPLOYMENT'),(131,'EMPLOY1126','MINERALOGISTA','EMPLOYMENT'),(132,'EMPLOY1127','MONITOR','EMPLOYMENT'),(133,'EMPLOY1128','MUSICÃ“LOGO','EMPLOYMENT'),(134,'EMPLOY1129','NATURÃ“PATA','EMPLOYMENT'),(135,'EMPLOY1130','NEFRÃ“LOGO','EMPLOYMENT'),(136,'EMPLOY1131','NEUMÃ“LOGO','EMPLOYMENT'),(137,'EMPLOY1132','NEUROANATOMISTA','EMPLOYMENT'),(138,'EMPLOY1133','NEUROBIÃ“LOGO','EMPLOYMENT'),(139,'EMPLOY1134','NEUROCIRUJANO','EMPLOYMENT'),(140,'EMPLOY1135','NEUROEMBRIÃ“LOGO','EMPLOYMENT'),(141,'EMPLOY1136','NEUROFISIÃ“LOGO','EMPLOYMENT'),(142,'EMPLOY1137','NEURÃ“LOGO','EMPLOYMENT'),(143,'EMPLOY1138','NUTRÃ“LOGO','EMPLOYMENT'),(144,'EMPLOY1139','OCEANÃ“GRAFO','EMPLOYMENT'),(145,'EMPLOY1140','ODONTÃ“LOGO','EMPLOYMENT'),(146,'EMPLOY1141','OFICIAL','EMPLOYMENT'),(147,'EMPLOY1142','OFICINISTA','EMPLOYMENT'),(148,'EMPLOY1143','OFTALMÃ“LOGO','EMPLOYMENT'),(149,'EMPLOY1144','ONCÃ“LOGO','EMPLOYMENT'),(150,'EMPLOY1145','Ã“PTICO','EMPLOYMENT'),(151,'EMPLOY1146','OPTOMETRISTA','EMPLOYMENT'),(152,'EMPLOY1147','ORDENANZA','EMPLOYMENT'),(153,'EMPLOY1148','ORIENTADOR','EMPLOYMENT'),(154,'EMPLOY1149','ORNITÃ“LOGO','EMPLOYMENT'),(155,'EMPLOY1150','ORTOPÃ‰DICO','EMPLOYMENT'),(156,'EMPLOY1151','ORTOPEDISTA','EMPLOYMENT'),(157,'EMPLOY1152','OSTEÃ“LOGO','EMPLOYMENT'),(158,'EMPLOY1153','OSTEÃ“PATA','EMPLOYMENT'),(159,'EMPLOY1154','OTORRINOLARINGÃ“LOGO','EMPLOYMENT'),(160,'EMPLOY1155','PALEOBIÃ“LOGO','EMPLOYMENT'),(161,'EMPLOY1156','PALEOBOTÃNICO','EMPLOYMENT'),(162,'EMPLOY1157','PALEÃ“GRAFO','EMPLOYMENT'),(163,'EMPLOY1158','PALEÃ“LOGO','EMPLOYMENT'),(164,'EMPLOY1159','PALEONTÃ“LOGO','EMPLOYMENT'),(165,'EMPLOY1160','PATÃ“LOGO','EMPLOYMENT'),(166,'EMPLOY1161','PEDAGOGO','EMPLOYMENT'),(167,'EMPLOY1162','PEDIATRA','EMPLOYMENT'),(168,'EMPLOY1163','PEDICURO','EMPLOYMENT'),(169,'EMPLOY1164','PERIODISTA','EMPLOYMENT'),(170,'EMPLOY1165','PERITO','EMPLOYMENT'),(171,'EMPLOY1166','INGENIERO TÃ‰CNICO','EMPLOYMENT'),(172,'EMPLOY1167','PISCICULTOR','EMPLOYMENT'),(173,'EMPLOY1168','PODÃ“LOGO','EMPLOYMENT'),(174,'EMPLOY1169','PORTERO','EMPLOYMENT'),(175,'EMPLOY1170','PREHISTORIADOR','EMPLOYMENT'),(176,'EMPLOY1171','PRESIDENTE','EMPLOYMENT'),(177,'EMPLOY1172','PROCTÃ“LOGO','EMPLOYMENT'),(178,'EMPLOY1173','PROFESOR','EMPLOYMENT'),(179,'EMPLOY1174','PROGRAMADOR','EMPLOYMENT'),(180,'EMPLOY1175','PROTÃ‰SICO','EMPLOYMENT'),(181,'EMPLOY1176','PROVEEDOR','EMPLOYMENT'),(182,'EMPLOY1177','PSICOANALISTA','EMPLOYMENT'),(183,'EMPLOY1178','PSICÃ“LOGO','EMPLOYMENT'),(184,'EMPLOY1179','PSICOFÃSICO','EMPLOYMENT'),(185,'EMPLOY1180','PSICOPEDAGOGO','EMPLOYMENT'),(186,'EMPLOY1181','PSICOTERAPEUTA','EMPLOYMENT'),(187,'EMPLOY1182','PSIQUIATRA','EMPLOYMENT'),(188,'EMPLOY1183','PUBLICISTA','EMPLOYMENT'),(189,'EMPLOY1184','PUBLICITARIO','EMPLOYMENT'),(190,'EMPLOY1185','PUERICULTOR','EMPLOYMENT'),(191,'EMPLOY1186','QUÃMICO','EMPLOYMENT'),(192,'EMPLOY1187','QUIROPRÃCTICO','EMPLOYMENT'),(193,'EMPLOY1188','RADIOASTRÃ“NOMO','EMPLOYMENT'),(194,'EMPLOY1189','RADIOFONISTA','EMPLOYMENT'),(195,'EMPLOY1190','RADIÃ“LOGO','EMPLOYMENT'),(196,'EMPLOY1191','RADIOTÃ‰CNICO','EMPLOYMENT'),(197,'EMPLOY1192','RADIOTELEFONISTA','EMPLOYMENT'),(198,'EMPLOY1193','RADIOTELEGRAFISTA','EMPLOYMENT'),(199,'EMPLOY1194','RADIOTERAPEUTA','EMPLOYMENT'),(200,'EMPLOY1195','RECTOR','EMPLOYMENT'),(201,'EMPLOY1196','SANITARIO','EMPLOYMENT'),(202,'EMPLOY1197','SECRETARIO','EMPLOYMENT'),(203,'EMPLOY1198','SEXÃ“LOGO','EMPLOYMENT'),(204,'EMPLOY1199','SISMÃ“LOGO','EMPLOYMENT'),(205,'EMPLOY1200','SOCIÃ“LOGO','EMPLOYMENT'),(206,'EMPLOY1201','SUBDELEGADO','EMPLOYMENT'),(207,'EMPLOY1202','SUBDIRECTOR','EMPLOYMENT'),(208,'EMPLOY1203','SUBSECRETARIO','EMPLOYMENT'),(209,'EMPLOY1204','TÃ‰CNICO','EMPLOYMENT'),(210,'EMPLOY1205','TELEFONISTA','EMPLOYMENT'),(211,'EMPLOY1206','TEÃ“LOGO','EMPLOYMENT'),(212,'EMPLOY1207','TERAPEUTA','EMPLOYMENT'),(213,'EMPLOY1208','TOCOGINECÃ“LOGO','EMPLOYMENT'),(214,'EMPLOY1209','TOCÃ“LOGO','EMPLOYMENT'),(215,'EMPLOY1210','TOXICÃ“LOGO','EMPLOYMENT'),(216,'EMPLOY1211','TRADUCTOR','EMPLOYMENT'),(217,'EMPLOY1212','TRANSCRIPTOR','EMPLOYMENT'),(218,'EMPLOY1213','TRAUMATÃ“LOGO','EMPLOYMENT'),(219,'EMPLOY1214','TUTOR','EMPLOYMENT'),(220,'EMPLOY1215','URÃ“LOGO','EMPLOYMENT'),(221,'EMPLOY1216','VETERINARIO','EMPLOYMENT'),(222,'EMPLOY1217','VICEDECANO','EMPLOYMENT'),(223,'EMPLOY1218','VICEDIRECTOR','EMPLOYMENT'),(224,'EMPLOY1219','VICEGERENTE','EMPLOYMENT'),(225,'EMPLOY1220','VICEPRESIDENTE','EMPLOYMENT'),(226,'EMPLOY1221','VICERRECTOR','EMPLOYMENT'),(227,'EMPLOY1222','VICESECRETARIO','EMPLOYMENT'),(228,'EMPLOY1223','VIRÃ“LOGO','EMPLOYMENT'),(229,'EMPLOY1224','VITICULTOR','EMPLOYMENT'),(230,'EMPLOY1225','VULCANÃ“LOGO ','EMPLOYMENT'),(231,'EMPLOY1226','XILÃ“GRAFO','EMPLOYMENT'),(232,'EMPLOY1227','ZOÃ“LOGO','EMPLOYMENT'),(233,'EMPLOY1228','ZOOTÃ‰CNICO','EMPLOYMENT'),(234,'SIZE001','SMALL','SIZES'),(235,'SIZE001','MEDIUN','SIZES'),(236,'SIZE001','LARGE','SIZES'),(237,'SIZE001','XLARGE','SIZES'),(238,'SIZE001','2XL','SIZES'),(239,'SIZE001','3XL','SIZES'),(240,'BLOODGRO01','O-','BLOODGRO'),(241,'BLOODGRO02','O+','BLOODGRO'),(242,'BLOODGRO03','A-','BLOODGRO'),(243,'BLOODGRO04','A+','BLOODGRO'),(244,'BLOODGRO05','B-','BLOODGRO'),(245,'BLOODGRO07','B+','BLOODGRO'),(246,'BLOODGRO08','AB-','BLOODGRO'),(247,'BLOODGRO09','AB+','BLOODGRO'),(248,'MAT2018','MATRICULA','PRODUCTS'),(249,'UNIFORME01','UNIFORMES ESTUDIANTES INFANTIL','PRODUCTS'),(250,'UNIFORME02','UNIFORMES ESTUDIANTES CADETE','PRODUCTS'),(251,'UNIFORME03','UNIFORME ESTUDIANTE JUVENIL','PRODUCTS'),(252,'PAGO001','EFECTIVO','PAYMENTS'),(253,'PAGO002','CHEQUE','PAYMENTS'),(254,'PAGO003','ACH','PAYMENTS');
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
