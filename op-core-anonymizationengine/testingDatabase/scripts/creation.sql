-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: operando_personaldatadb
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
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `ID` int(11) NOT NULL,
  `NAME_0` varchar(10) DEFAULT NULL,
  `NAME_1` varchar(10) DEFAULT NULL,
  `NAME_2` varchar(10) DEFAULT NULL,
  `CITIZENS_NUMBER` int(11) DEFAULT NULL,
  `PROVINCE_ID` int(11) DEFAULT NULL,
  `CLIMA_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `cities_provinces_fk` (`PROVINCE_ID`),
  KEY `cities_clima_fk` (`CLIMA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `climate`
--

DROP TABLE IF EXISTS `climate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `climate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(50) DEFAULT NULL,
  `DESCRIPTION_1` varchar(50) DEFAULT NULL,
  `DESCRIPTION_2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `climate`
--

LOCK TABLES `climate` WRITE;
/*!40000 ALTER TABLE `climate` DISABLE KEYS */;
INSERT INTO `climate` VALUES (1,'Tundra','',''),(2,'Icecap ','',''),(3,'Highland ','',''),(4,'Desert ','',''),(5,'Semiarid ','',''),(6,'Tundra','',''),(7,'Icecap ','',''),(8,'Highland ','',''),(9,'Desert ','',''),(10,'Semiarid ','',''),(11,'Tropical wet','',''),(12,'Tropical wet & dry','',''),(13,'Humid subtropical','',''),(14,'Marine west coast','',''),(15,'Mediterranean','',''),(16,'Humid continental','',''),(17,'Subarctic','','');
/*!40000 ALTER TABLE `climate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(50) DEFAULT NULL,
  `DESCRIPTION_1` varchar(50) DEFAULT NULL,
  `DESCRIPTION_2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'United-States','North America','*\r'),(2,'Cambodia','Asia','*\r'),(3,'England','Europa','*\r'),(4,'Puerto-Rico','North America','*\r'),(5,'Canada','North America','*\r'),(6,'Germany','Europe','*\r'),(7,'Outlying-US(Guam-USVI-etc)','North America','*\r'),(8,'India','Asia','*\r'),(9,'Japan','Asia','*\r'),(10,'Greece','Europe','*\r'),(11,'South','Africa','*\r'),(12,'China','Asia','*\r'),(13,'Cuba','North America','*\r'),(14,'Iran','Asia','*\r'),(15,'Honduras','North America','*\r'),(16,'Philippines','Asia','*\r'),(17,'Italy','Europe','*\r'),(18,'Poland','Europe','*\r'),(19,'Jamaica','North America','*\r'),(20,'Vietnam','Asia','*\r'),(21,'Mexico','North America','*\r'),(22,'Portugal','Europe','*\r'),(23,'Ireland','Europe','*\r'),(24,'France','Europe','*\r'),(25,'Dominican-Republic','North America','*\r'),(26,'Laos','Asia','*\r'),(27,'Ecuador','South America','*\r'),(28,'Taiwan','Asia','*\r'),(29,'Haiti','North America','*\r'),(30,'Columbia','South America','*\r'),(31,'Hungary','Europe','*\r'),(32,'Guatemala','North America','*\r'),(33,'Nicaragua','South America','*\r'),(34,'Scotland','Europe','*\r'),(35,'Thailand','Asia','*\r'),(36,'Yugoslavia','Europe','*\r'),(37,'El-Salvador','North America','*\r'),(38,'Trinadad&Tobago','South America','*\r'),(39,'Peru','South America','*\r'),(40,'Hong','Asia','*\r'),(41,'Holand-Netherlands','Europe','*\r');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_types`
--

DROP TABLE IF EXISTS `data_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_types` (
  `DATA_TYPE_ID` int(1) NOT NULL DEFAULT '0',
  `DATA_TYPE_DESCRIPTION` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DATA_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_types`
--

LOCK TABLES `data_types` WRITE;
/*!40000 ALTER TABLE `data_types` DISABLE KEYS */;
INSERT INTO `data_types` VALUES (1,'Insensitive'),(2,'Sensitive'),(3,'Quasi-identifying'),(4,'Identifying');
/*!40000 ALTER TABLE `data_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dataunits_types`
--

DROP TABLE IF EXISTS `dataunits_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dataunits_types` (
  `NAME` int(1) DEFAULT NULL,
  `SURNAME` int(1) DEFAULT NULL,
  `IDENTIFICATION_NUMBER` int(1) DEFAULT NULL,
  `CELL_PHONE_NUMBER` int(1) DEFAULT NULL,
  `EMAIL_ADDRESS` int(1) DEFAULT NULL,
  `SSN` int(1) DEFAULT NULL,
  `GENDER` int(1) DEFAULT NULL,
  `RACE` int(1) DEFAULT NULL,
  `DATE_OF_BIRTH` int(1) DEFAULT NULL,
  `BIRTH_CITY` int(1) DEFAULT NULL,
  `RESIDENCE_CITY` int(1) DEFAULT NULL,
  `RESIDENCE_STREET` int(1) DEFAULT NULL,
  `RESIDENCE_POST_CODE` int(1) DEFAULT NULL,
  `NATIVE_COUNTRY` int(1) DEFAULT NULL,
  `MARITAL_STATUS` int(1) DEFAULT NULL,
  `NUMBER_OF_CHILDREN` int(1) DEFAULT NULL,
  `EDUCATION` int(1) DEFAULT NULL,
  `POLITICAL_TENDENCY` int(1) DEFAULT NULL,
  `WORK_CLASS` int(1) DEFAULT NULL,
  `OCCUPATION` int(1) DEFAULT NULL,
  `SALARY_CLASS` int(1) DEFAULT NULL,
  `DISEASE` int(1) DEFAULT NULL,
  `SPORT` int(1) DEFAULT NULL,
  `HOBBIE` int(1) DEFAULT NULL,
  `HABIT` int(1) DEFAULT NULL,
  `RELIGION` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dataunits_types`
--

LOCK TABLES `dataunits_types` WRITE;
/*!40000 ALTER TABLE `dataunits_types` DISABLE KEYS */;
INSERT INTO `dataunits_types` VALUES (3,3,4,4,4,4,1,1,3,2,1,2,3,1,2,2,2,2,2,2,2,2,1,1,1,2);
/*!40000 ALTER TABLE `dataunits_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diseases`
--

DROP TABLE IF EXISTS `diseases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diseases` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(50) DEFAULT NULL,
  `DESCRIPTION_1` varchar(50) DEFAULT NULL,
  `DESCRIPTION_2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diseases`
--

LOCK TABLES `diseases` WRITE;
/*!40000 ALTER TABLE `diseases` DISABLE KEYS */;
INSERT INTO `diseases` VALUES (1,'Arthritis','*','*'),(2,'Asthma','*','*'),(3,'Cancer','*','*'),(4,'Chronic Fatigue Syndrome','*','*'),(5,'Diabetes','*','*'),(6,'Epilepsy','*','*'),(7,'Flu (Influenza)','*','*'),(8,'Heart Disease','*','*'),(9,'Hepatitis','*','*'),(10,'HIV/AIDS','*','*'),(11,'Meningitis','*','*'),(12,'Overweight and Obesity','*','*'),(13,'Salmonella','*','*'),(14,'Arthritis','*','*'),(15,'Sexually Transmitted Diseases (STDs)','*','*'),(16,'Stroke','*','*'),(17,'Arthritis','*','*'),(18,'Traumatic Brain Injury (TBI)','*','*'),(19,'Tuberculosis (TB)','*','*');
/*!40000 ALTER TABLE `diseases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(50) DEFAULT NULL,
  `DESCRIPTION_1` varchar(50) DEFAULT NULL,
  `DESCRIPTION_2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (1,'Bachelors','Undergraduate','Higher education'),(2,'Some-college','Undergraduate','Higher education'),(3,'11th','High School','Secondary education'),(4,'HS-grad','High School','Secondary education'),(5,'Prof-school','Professional Education','Higher education'),(6,'Assoc-acdm','Professional Education','Higher education'),(7,'Assoc-voc','Professional Education','Higher education'),(8,'9th','High School','Secondary education'),(9,'7th-8th','High School','Secondary education'),(10,'12th','High School','Secondary education'),(11,'Masters','Graduate','Higher education'),(12,'1st-4th','Primary School','Primary education'),(13,'10th','High School','Secondary education'),(14,'Doctorate','Graduate','Higher education'),(15,'5th-6th','Primary School','Primary education'),(16,'Preschool','Primary School','Primary education');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genders`
--

DROP TABLE IF EXISTS `genders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(10) DEFAULT NULL,
  `DESCRIPTION_1` varchar(10) DEFAULT NULL,
  `DESCRIPTION_2` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genders`
--

LOCK TABLES `genders` WRITE;
/*!40000 ALTER TABLE `genders` DISABLE KEYS */;
INSERT INTO `genders` VALUES (1,'Male','*\r','*'),(2,'Female','*\r','*');
/*!40000 ALTER TABLE `genders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generic_personal_data`
--

DROP TABLE IF EXISTS `generic_personal_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generic_personal_data` (
  `PERSONAL_ID` int(11) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `SURNAME` varchar(10) DEFAULT NULL,
  `IDENTIFICATION_NUMBER` varchar(20) DEFAULT NULL,
  `CELL_PHONE_NUMBER` varchar(15) DEFAULT NULL,
  `EMAIL_ADDRESS` varchar(30) DEFAULT NULL,
  `SSN` varchar(15) DEFAULT NULL,
  `GENDER_ID` int(11) DEFAULT NULL,
  `RACE_ID` int(11) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `RESIDENCE_CITY_CITIZENS_NUMBER` int(11) DEFAULT NULL,
  `BIRTH_CITY_ID` int(11) DEFAULT NULL,
  `RESIDENCE_CITY_ID` int(11) DEFAULT NULL,
  `RESIDENCE_STREET` varchar(50) DEFAULT NULL,
  `RESIDENCE_POST_CODE` varchar(10) DEFAULT NULL,
  `NATIVE_COUNTRY_ID` int(11) DEFAULT NULL,
  `MARITAL_STATUS_ID` int(11) DEFAULT NULL,
  `NUMBER_OF_CHILDREN` int(11) DEFAULT NULL,
  `EDUCATION_ID` int(11) DEFAULT NULL,
  `POLITICAL_TENDENCY_ID` int(11) DEFAULT NULL,
  `WORK_CLASS_ID` int(11) DEFAULT NULL,
  `OCCUPATION_ID` int(11) DEFAULT NULL,
  `SALARY_CLASS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PERSONAL_ID`),
  KEY `generic_personal_data_genders_fk` (`GENDER_ID`),
  KEY `generic_personal_data_countries_fk` (`NATIVE_COUNTRY_ID`),
  KEY `generic_personal_data_political_tendency_fk` (`POLITICAL_TENDENCY_ID`),
  KEY `generic_personal_data_race_fk` (`RACE_ID`),
  KEY `generic_personal_data_work_class_fk` (`WORK_CLASS_ID`),
  KEY `generic_personal_data_occupation_fk` (`OCCUPATION_ID`),
  KEY `generic_personal_data_education_fk` (`EDUCATION_ID`),
  KEY `generic_personal_data_birth_cities_fk` (`BIRTH_CITY_ID`),
  KEY `generic_personal_data_residende_cities_fk` (`RESIDENCE_CITY_ID`),
  KEY `generic_personal_data_marital_status_fk` (`MARITAL_STATUS_ID`),
  KEY `generic_personal_data_salary_class_fk` (`SALARY_CLASS_ID`),
  CONSTRAINT `generic_personal_data_countries_FK` FOREIGN KEY (`NATIVE_COUNTRY_ID`) REFERENCES `countries` (`ID`),
  CONSTRAINT `generic_personal_data_education_FK` FOREIGN KEY (`EDUCATION_ID`) REFERENCES `education` (`ID`),
  CONSTRAINT `generic_personal_data_genders_FK` FOREIGN KEY (`GENDER_ID`) REFERENCES `genders` (`ID`),
  CONSTRAINT `generic_personal_data_marital_status_FK` FOREIGN KEY (`MARITAL_STATUS_ID`) REFERENCES `marital_status` (`ID`),
  CONSTRAINT `generic_personal_data_occupation_FK` FOREIGN KEY (`OCCUPATION_ID`) REFERENCES `occupation` (`ID`),
  CONSTRAINT `generic_personal_data_race_FK` FOREIGN KEY (`RACE_ID`) REFERENCES `race` (`ID`),
  CONSTRAINT `generic_personal_data_salary_class_FK` FOREIGN KEY (`SALARY_CLASS_ID`) REFERENCES `salary_class` (`ID`),
  CONSTRAINT `generic_personal_data_work_class_FK` FOREIGN KEY (`WORK_CLASS_ID`) REFERENCES `work_class` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generic_personal_data`
--

LOCK TABLES `generic_personal_data` WRITE;
/*!40000 ALTER TABLE `generic_personal_data` DISABLE KEYS */;
INSERT INTO `generic_personal_data` VALUES (1,'Elliot','Smith','1111111111','555555555','john.smith@gmail.com','1111111111',1,1,'1971-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1),(2,'John','Smith','1111111112','555555552','john.smith@gmail.com','1111111112',1,2,'1981-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1),(3,'Ralph','Smith','1111111113','555555553','john.smith@gmail.com','1111111113',1,3,'1981-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1),(4,'John','Smith','1111111114','555555554','john.smith@gmail.com','1111111114',1,4,'1981-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1),(5,'Michael','Smith','1111111115','555555555','john.smith@gmail.com','11111111154',1,5,'1981-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1);
/*!40000 ALTER TABLE `generic_personal_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habits`
--

DROP TABLE IF EXISTS `habits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `habits` (
  `ID` int(11) NOT NULL,
  `DESCRIPTION_0` varchar(10) DEFAULT NULL,
  `DESCRIPTION_1` varchar(10) DEFAULT NULL,
  `DESCRIPTION_2` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habits`
--

LOCK TABLES `habits` WRITE;
/*!40000 ALTER TABLE `habits` DISABLE KEYS */;
/*!40000 ALTER TABLE `habits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hobbies`
--

DROP TABLE IF EXISTS `hobbies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hobbies` (
  `ID` int(11) NOT NULL,
  `DESCRIPTION_0` varchar(10) DEFAULT NULL,
  `DESCRIPTION_1` varchar(10) DEFAULT NULL,
  `DESCRIPTION_2` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hobbies`
--

LOCK TABLES `hobbies` WRITE;
/*!40000 ALTER TABLE `hobbies` DISABLE KEYS */;
/*!40000 ALTER TABLE `hobbies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marital_status`
--

DROP TABLE IF EXISTS `marital_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marital_status` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(50) DEFAULT NULL,
  `DESCRIPTION_1` varchar(50) DEFAULT NULL,
  `DESCRIPTION_2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marital_status`
--

LOCK TABLES `marital_status` WRITE;
/*!40000 ALTER TABLE `marital_status` DISABLE KEYS */;
INSERT INTO `marital_status` VALUES (1,'Married-civ-spouse','spouse present','*\r'),(2,'Divorced','spouse not present','*\r'),(3,'Never-married','spouse not present','*\r'),(4,'Separated','spouse not present','*\r'),(5,'Widowed','spouse not present','*\r'),(6,'Married-spouse-absent','spouse not present','*\r'),(7,'Married-AF-spouse','spouse present','*\r');
/*!40000 ALTER TABLE `marital_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occupation`
--

DROP TABLE IF EXISTS `occupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `occupation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(10) DEFAULT NULL,
  `DESCRIPTION_1` varchar(10) DEFAULT NULL,
  `DESCRIPTION_2` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupation`
--

LOCK TABLES `occupation` WRITE;
/*!40000 ALTER TABLE `occupation` DISABLE KEYS */;
INSERT INTO `occupation` VALUES (1,'Tech-suppo','Technical','*\r'),(2,'Craft-repa','Technical','*\r'),(3,'Other-serv','Other','*\r'),(4,'Sales','Nontechnic','*\r'),(5,'Exec-manag','Nontechnic','*\r'),(6,'Prof-speci','Technical','*\r'),(7,'Handlers-c','Nontechnic','*\r'),(8,'Machine-op','Technical','*\r'),(9,'Adm-cleric','Other','*\r'),(10,'Farming-fi','Other','*\r'),(11,'Transport-','Other','*\r'),(12,'Priv-house','Other','*\r'),(13,'Protective','Other','*\r'),(14,'Armed-Forc','Other','*\r');
/*!40000 ALTER TABLE `occupation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `operando_personaldata_view`
--

DROP TABLE IF EXISTS `operando_personaldata_view`;
/*!50001 DROP VIEW IF EXISTS `operando_personaldata_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `operando_personaldata_view` (
  `OCCUPATION` tinyint NOT NULL,
  `SALARY_CLASS` tinyint NOT NULL,
  `GENDER` tinyint NOT NULL,
  `EDUCATION` tinyint NOT NULL,
  `COUNTRY` tinyint NOT NULL,
  `RACE` tinyint NOT NULL,
  `EMAIL_ADDRESS` tinyint NOT NULL,
  `CELL_PHONE_NUMBER` tinyint NOT NULL,
  `SURNAME` tinyint NOT NULL,
  `NUMBER_OF_CHILDREN` tinyint NOT NULL,
  `RESIDENCE_POST_CODE` tinyint NOT NULL,
  `NAME` tinyint NOT NULL,
  `IDENTIFICATION_NUMBER` tinyint NOT NULL,
  `DATE_OF_BIRTH` tinyint NOT NULL,
  `SSN` tinyint NOT NULL,
  `MARITAL_STATUS` tinyint NOT NULL,
  `WORK_CLASS` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `personal_data_access_levels`
--

DROP TABLE IF EXISTS `personal_data_access_levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_data_access_levels` (
  `PERSONAL_ID` int(11) NOT NULL,
  `DATA_UNIT` varchar(50) NOT NULL DEFAULT '',
  `DEFAULT_ACCESS_LEVEL` int(10) DEFAULT NULL,
  PRIMARY KEY (`PERSONAL_ID`,`DATA_UNIT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_data_access_levels`
--

LOCK TABLES `personal_data_access_levels` WRITE;
/*!40000 ALTER TABLE `personal_data_access_levels` DISABLE KEYS */;
INSERT INTO `personal_data_access_levels` VALUES (1,'BIRTH_CITY_ID',0),(1,'CELL_PHONE_NUMBER',2),(1,'DATE_OF_BIRTH',0),(1,'DISEASES',2),(1,'EDUCATION_ID',0),(1,'EMAIL_ADDRESS',2),(1,'GENDER_ID',0),(1,'HABITS',0),(1,'HOBBIES',0),(1,'IDENTIFICATION_NUMBER',2),(1,'MARITAL_STATUS_ID',0),(1,'NAME',2),(1,'NATIVE_COUNTRY_ID',0),(1,'NUMBER_OF_CHILDREN',0),(1,'OCCUPATION_ID',0),(1,'POLITICAL_TENDENCY_ID',0),(1,'RACE_ID',0),(1,'RELIGION',2),(1,'RESIDENCE_CITY_CITIZENS_NUMBER',0),(1,'RESIDENCE_CITY_ID',0),(1,'RESIDENCE_POST_CODE',0),(1,'RESIDENCE_STREET',0),(1,'SALARY_CLASS_ID',0),(1,'SPORTS',0),(1,'SSN',2),(1,'SURNAME',2),(1,'WORK_CLASS_ID',0);
/*!40000 ALTER TABLE `personal_data_access_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_data_access_levels_requester`
--

DROP TABLE IF EXISTS `personal_data_access_levels_requester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_data_access_levels_requester` (
  `PERSONAL_ID` int(11) NOT NULL DEFAULT '0',
  `DATA_UNIT` varchar(50) DEFAULT NULL,
  `DEFAULT_ACCESS_LEVEL` varchar(20) DEFAULT NULL,
  `REQUESTER_ID` varchar(20) DEFAULT NULL,
  `ACCESS_LEVEL_PER_REQUESTER` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PERSONAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_data_access_levels_requester`
--

LOCK TABLES `personal_data_access_levels_requester` WRITE;
/*!40000 ALTER TABLE `personal_data_access_levels_requester` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_data_access_levels_requester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_diseases`
--

DROP TABLE IF EXISTS `personal_diseases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_diseases` (
  `PERSONAL_ID` int(11) NOT NULL,
  `DISEASE_ID` int(11) NOT NULL,
  `INITIAL_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`PERSONAL_ID`,`DISEASE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_diseases`
--

LOCK TABLES `personal_diseases` WRITE;
/*!40000 ALTER TABLE `personal_diseases` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_diseases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_habits`
--

DROP TABLE IF EXISTS `personal_habits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_habits` (
  `PERSONAL_ID` int(11) NOT NULL,
  `HABIT_ID` int(11) NOT NULL,
  PRIMARY KEY (`PERSONAL_ID`,`HABIT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_habits`
--

LOCK TABLES `personal_habits` WRITE;
/*!40000 ALTER TABLE `personal_habits` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_habits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_hobbies`
--

DROP TABLE IF EXISTS `personal_hobbies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_hobbies` (
  `PERSONAL_ID` int(11) NOT NULL,
  `HOBBIE_ID` int(11) NOT NULL,
  PRIMARY KEY (`PERSONAL_ID`,`HOBBIE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_hobbies`
--

LOCK TABLES `personal_hobbies` WRITE;
/*!40000 ALTER TABLE `personal_hobbies` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_hobbies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_religions`
--

DROP TABLE IF EXISTS `personal_religions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_religions` (
  `PERSONAL_ID` int(11) NOT NULL,
  `RELIGION_ID` int(11) NOT NULL,
  PRIMARY KEY (`PERSONAL_ID`,`RELIGION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_religions`
--

LOCK TABLES `personal_religions` WRITE;
/*!40000 ALTER TABLE `personal_religions` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_religions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_sports`
--

DROP TABLE IF EXISTS `personal_sports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_sports` (
  `PERSONAL_ID` int(11) NOT NULL,
  `SPORT_ID` int(11) NOT NULL,
  PRIMARY KEY (`PERSONAL_ID`,`SPORT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_sports`
--

LOCK TABLES `personal_sports` WRITE;
/*!40000 ALTER TABLE `personal_sports` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_sports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `political_tendency`
--

DROP TABLE IF EXISTS `political_tendency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `political_tendency` (
  `ID` int(11) NOT NULL,
  `DATA_TYPE` varchar(20) NOT NULL,
  `DESCRIPTION_0` varchar(10) NOT NULL,
  `DESCRIPTION_1` varchar(10) NOT NULL,
  `DESCRIPTION_2` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `political_tendency`
--

LOCK TABLES `political_tendency` WRITE;
/*!40000 ALTER TABLE `political_tendency` DISABLE KEYS */;
/*!40000 ALTER TABLE `political_tendency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provinces`
--

DROP TABLE IF EXISTS `provinces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provinces` (
  `ID` int(11) NOT NULL,
  `NAME_0` varchar(10) DEFAULT NULL,
  `NAME_1` varchar(10) DEFAULT NULL,
  `NAME_2` varchar(10) DEFAULT NULL,
  `COUNTRY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `provinces_countries_fk` (`COUNTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provinces`
--

LOCK TABLES `provinces` WRITE;
/*!40000 ALTER TABLE `provinces` DISABLE KEYS */;
/*!40000 ALTER TABLE `provinces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `race`
--

DROP TABLE IF EXISTS `race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `race` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(50) DEFAULT NULL,
  `DESCRIPTION_1` varchar(50) DEFAULT NULL,
  `DESCRIPTION_2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `race`
--

LOCK TABLES `race` WRITE;
/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` VALUES (1,'White','*\r','*'),(2,'Asian-Pac-Islander','*\r','*'),(3,'Amer-Indian-Eskimo','*\r','*'),(4,'Other','*\r','*'),(5,'Black','*\r','*');
/*!40000 ALTER TABLE `race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `religions`
--

DROP TABLE IF EXISTS `religions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `religions` (
  `ID` int(11) NOT NULL,
  `DESCRIPTION_0` varchar(10) NOT NULL,
  `DESCRIPTION_1` varchar(10) NOT NULL,
  `DESCRIPTION_2` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `religions`
--

LOCK TABLES `religions` WRITE;
/*!40000 ALTER TABLE `religions` DISABLE KEYS */;
/*!40000 ALTER TABLE `religions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary_class`
--

DROP TABLE IF EXISTS `salary_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary_class` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(10) DEFAULT NULL,
  `DESCRIPTION_1` varchar(10) DEFAULT NULL,
  `DESCRIPTION_2` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary_class`
--

LOCK TABLES `salary_class` WRITE;
/*!40000 ALTER TABLE `salary_class` DISABLE KEYS */;
INSERT INTO `salary_class` VALUES (1,'<=50K','*','*'),(2,'>50K','*','*');
/*!40000 ALTER TABLE `salary_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sports`
--

DROP TABLE IF EXISTS `sports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sports` (
  `ID` int(11) NOT NULL,
  `NAME_0` varchar(10) DEFAULT NULL,
  `NAME_1` varchar(10) DEFAULT NULL,
  `NAME_2` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports`
--

LOCK TABLES `sports` WRITE;
/*!40000 ALTER TABLE `sports` DISABLE KEYS */;
/*!40000 ALTER TABLE `sports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_class`
--

DROP TABLE IF EXISTS `work_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_class` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION_0` varchar(50) DEFAULT NULL,
  `DESCRIPTION_1` varchar(50) DEFAULT NULL,
  `DESCRIPTION_2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_class`
--

LOCK TABLES `work_class` WRITE;
/*!40000 ALTER TABLE `work_class` DISABLE KEYS */;
INSERT INTO `work_class` VALUES (1,'Private','Non-Government','*\r'),(2,'Self-emp-not-inc','Non-Government','*\r'),(3,'Self-emp-inc','Non-Government','*\r'),(4,'Federal-gov','Government','*\r'),(5,'Local-gov','Government','*\r'),(6,'State-gov','Government','*\r'),(7,'Without-pay','Unemployed','*\r'),(8,'Never-worked','Unemployed','*\r');
/*!40000 ALTER TABLE `work_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'operando_personaldatadb'
--

--
-- Final view structure for view `operando_personaldata_view`
--

/*!50001 DROP TABLE IF EXISTS `operando_personaldata_view`*/;
/*!50001 DROP VIEW IF EXISTS `operando_personaldata_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `operando_personaldata_view` AS select distinct `occupation`.`DESCRIPTION_0` AS `OCCUPATION`,`salary_class`.`DESCRIPTION_0` AS `SALARY_CLASS`,`genders`.`DESCRIPTION_0` AS `GENDER`,`education`.`DESCRIPTION_0` AS `EDUCATION`,`countries`.`DESCRIPTION_0` AS `COUNTRY`,`race`.`DESCRIPTION_0` AS `RACE`,`generic_personal_data`.`EMAIL_ADDRESS` AS `EMAIL_ADDRESS`,`generic_personal_data`.`CELL_PHONE_NUMBER` AS `CELL_PHONE_NUMBER`,`generic_personal_data`.`SURNAME` AS `SURNAME`,`generic_personal_data`.`NUMBER_OF_CHILDREN` AS `NUMBER_OF_CHILDREN`,`generic_personal_data`.`RESIDENCE_POST_CODE` AS `RESIDENCE_POST_CODE`,`generic_personal_data`.`NAME` AS `NAME`,`generic_personal_data`.`IDENTIFICATION_NUMBER` AS `IDENTIFICATION_NUMBER`,`generic_personal_data`.`DATE_OF_BIRTH` AS `DATE_OF_BIRTH`,`generic_personal_data`.`SSN` AS `SSN`,`marital_status`.`DESCRIPTION_0` AS `MARITAL_STATUS`,`work_class`.`DESCRIPTION_0` AS `WORK_CLASS` from ((((((((`occupation` join `salary_class`) join `genders`) join `education`) join `countries`) join `race`) join `generic_personal_data` on(((`occupation`.`ID` = `generic_personal_data`.`OCCUPATION_ID`) and (`salary_class`.`ID` = `generic_personal_data`.`SALARY_CLASS_ID`) and (`genders`.`ID` = `generic_personal_data`.`GENDER_ID`) and (`education`.`ID` = `generic_personal_data`.`EDUCATION_ID`) and (`countries`.`ID` = `generic_personal_data`.`NATIVE_COUNTRY_ID`) and (`race`.`ID` = `generic_personal_data`.`RACE_ID`)))) join `marital_status` on((`marital_status`.`ID` = `generic_personal_data`.`MARITAL_STATUS_ID`))) join `work_class` on((`work_class`.`ID` = `generic_personal_data`.`WORK_CLASS_ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-03  9:32:33
