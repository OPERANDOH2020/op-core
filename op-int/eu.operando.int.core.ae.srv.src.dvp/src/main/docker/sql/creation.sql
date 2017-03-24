CREATE DATABASE  IF NOT EXISTS `operando_personaldatadb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `operando_personaldatadb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: operando_personaldatadb
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `LOGS`
--

DROP TABLE IF EXISTS `LOGS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOGS` (
  `USER_ID` varchar(20) DEFAULT NULL,
  `DATED` varchar(50) DEFAULT NULL,
  `LOGGER` varchar(50) DEFAULT NULL,
  `LEVEL` varchar(10) DEFAULT NULL,
  `REQUESTERTYPE` varchar(20) DEFAULT NULL,
  `REQUESTERID` varchar(20) DEFAULT NULL,
  `LOGPRIORITY` varchar(10) DEFAULT NULL,
  `KEYWORDS` varchar(50) DEFAULT NULL,
  `TITLE` varchar(50) DEFAULT NULL,
  `MESSAGE` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `CITIZENS_NUMBER` int(11) NOT NULL DEFAULT '0',
  `PROVINCE_ID` int(11) DEFAULT NULL,
  `CLIMA_ID` int(11) NOT NULL DEFAULT '0',
  KEY `cities_provinces_fk` (`PROVINCE_ID`),
  KEY `cities_clima_fk` (`CLIMA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`PERSONAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
-- Temporary view structure for view `operando_personaldata_view`
--

DROP TABLE IF EXISTS `operando_personaldata_view`;
/*!50001 DROP VIEW IF EXISTS `operando_personaldata_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `operando_personaldata_view` AS SELECT 
 1 AS `OCCUPATION`,
 1 AS `SALARY_CLASS`,
 1 AS `GENDER`,
 1 AS `EDUCATION`,
 1 AS `COUNTRY`,
 1 AS `RACE`,
 1 AS `EMAIL_ADDRESS`,
 1 AS `CELL_PHONE_NUMBER`,
 1 AS `SURNAME`,
 1 AS `NUMBER_OF_CHILDREN`,
 1 AS `RESIDENCE_POST_CODE`,
 1 AS `NAME`,
 1 AS `IDENTIFICATION_NUMBER`,
 1 AS `DATE_OF_BIRTH`,
 1 AS `SSN`,
 1 AS `MARITAL_STATUS`,
 1 AS `WORK_CLASS`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `personalData`
--

DROP TABLE IF EXISTS `personalData`;
/*!50001 DROP VIEW IF EXISTS `personalData`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `personalData` AS SELECT 
 1 AS `OCCUPATION`,
 1 AS `SALARY_CLASS`,
 1 AS `GENDER`,
 1 AS `EDUCATION`,
 1 AS `COUNTRY`,
 1 AS `RACE`,
 1 AS `EMAIL_ADDRESS`,
 1 AS `CELL_PHONE_NUMBER`,
 1 AS `SURNAME`,
 1 AS `NUMBER_OF_CHILDREN`,
 1 AS `RESIDENCE_POST_CODE`,
 1 AS `NAME`,
 1 AS `IDENTIFICATION_NUMBER`,
 1 AS `DATE_OF_BIRTH`,
 1 AS `SSN`,
 1 AS `MARITAL_STATUS`,
 1 AS `WORK_CLASS`*/;
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
-- Final view structure for view `operando_personaldata_view`
--

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

--
-- Final view structure for view `personalData`
--

/*!50001 DROP VIEW IF EXISTS `personalData`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `personalData` AS select distinct `occupation`.`DESCRIPTION_0` AS `OCCUPATION`,`salary_class`.`DESCRIPTION_0` AS `SALARY_CLASS`,`genders`.`DESCRIPTION_0` AS `GENDER`,`education`.`DESCRIPTION_0` AS `EDUCATION`,`countries`.`DESCRIPTION_0` AS `COUNTRY`,`race`.`DESCRIPTION_0` AS `RACE`,`generic_personal_data`.`EMAIL_ADDRESS` AS `EMAIL_ADDRESS`,`generic_personal_data`.`CELL_PHONE_NUMBER` AS `CELL_PHONE_NUMBER`,`generic_personal_data`.`SURNAME` AS `SURNAME`,`generic_personal_data`.`NUMBER_OF_CHILDREN` AS `NUMBER_OF_CHILDREN`,`generic_personal_data`.`RESIDENCE_POST_CODE` AS `RESIDENCE_POST_CODE`,`generic_personal_data`.`NAME` AS `NAME`,`generic_personal_data`.`IDENTIFICATION_NUMBER` AS `IDENTIFICATION_NUMBER`,`generic_personal_data`.`DATE_OF_BIRTH` AS `DATE_OF_BIRTH`,`generic_personal_data`.`SSN` AS `SSN`,`marital_status`.`DESCRIPTION_0` AS `MARITAL_STATUS`,`work_class`.`DESCRIPTION_0` AS `WORK_CLASS` from ((((((((`occupation` join `salary_class`) join `genders`) join `education`) join `countries`) join `race`) join `generic_personal_data` on(((`occupation`.`ID` = `generic_personal_data`.`OCCUPATION_ID`) and (`salary_class`.`ID` = `generic_personal_data`.`SALARY_CLASS_ID`) and (`genders`.`ID` = `generic_personal_data`.`GENDER_ID`) and (`education`.`ID` = `generic_personal_data`.`EDUCATION_ID`) and (`countries`.`ID` = `generic_personal_data`.`NATIVE_COUNTRY_ID`) and (`race`.`ID` = `generic_personal_data`.`RACE_ID`)))) join `marital_status` on((`marital_status`.`ID` = `generic_personal_data`.`MARITAL_STATUS_ID`))) join `work_class` on((`work_class`.`ID` = `generic_personal_data`.`WORK_CLASS_ID`))) */;
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

-- Dump completed on 2017-02-14  9:57:39
