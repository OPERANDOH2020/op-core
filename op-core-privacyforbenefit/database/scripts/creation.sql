-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: operando_privacyforbenefitdb
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `DEAL`
--

DROP TABLE IF EXISTS `DEAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEAL` (
  `ID` varchar(20) NOT NULL,
  `OFFER_ID` varchar(20) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `CREATED_AT` varchar(20) NOT NULL,
  `CANCELED_AT` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `OFFER_ID` (`OFFER_ID`),
  CONSTRAINT `DEAL_ibfk_1` FOREIGN KEY (`OFFER_ID`) REFERENCES `OFFER` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DEAL`
--

LOCK TABLES `DEAL` WRITE;
/*!40000 ALTER TABLE `DEAL` DISABLE KEYS */;
/*!40000 ALTER TABLE `DEAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OFFER`
--

DROP TABLE IF EXISTS `OFFER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OFFER` (
  `ID` varchar(10) NOT NULL DEFAULT '',
  `OSP_ID` varchar(5) DEFAULT NULL,
  `TITLE` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `SERVICE_WEBSITE` varchar(100) DEFAULT NULL,
  `IS_ENABLED` tinyint(1) DEFAULT NULL,
  `OSP_CALLBACK_URL` varchar(50) DEFAULT NULL,
  `EXPIRATION_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `OFFER_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `OSP` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OFFER`
--

LOCK TABLES `OFFER` WRITE;
/*!40000 ALTER TABLE `OFFER` DISABLE KEYS */;
/*!40000 ALTER TABLE `OFFER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OSP`
--

DROP TABLE IF EXISTS `OSP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OSP` (
  `ID` varchar(5) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `OSP_WEBSITE` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OSP`
--

LOCK TABLES `OSP` WRITE;
/*!40000 ALTER TABLE `OSP` DISABLE KEYS */;
INSERT INTO `OSP` VALUES ('1','Facebook',NULL,'https://www.facebook.com');
/*!40000 ALTER TABLE `OSP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'operando_privacyforbenefitdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-09 15:42:26
