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
-- Dumping data for table `LOGS`
--

LOCK TABLES `LOGS` WRITE;
/*!40000 ALTER TABLE `LOGS` DISABLE KEYS */;
INSERT INTO `LOGS` VALUES ('username','2016-06-07 15:10:22,174','io.swagger.api.impl.LogApiServiceImpl','INFO','Module','1001','Low','[keyword1, keyword2, keyword3]','First log','First log for testing purposes'),('username','2016-10-19 13:02:41,722','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1002','LOW','[keywordA, keywordB, keywordC]','First log','First log for testing purposes'),('username','2016-10-19 13:05:27,467','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1002','LOW','[keywordA, keywordB, keywordC]','First log','First log for testing purposes'),('username','2016-10-19 13:49:52,484','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1002','LOW','[keywordA, keywordB, keywordC]','First log','First log for testing purposes'),('username','2016-12-07 12:28:27,342','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes'),('username','2016-12-07 17:11:53,932','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes'),('username','2016-12-13 09:55:59,366','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes'),('username','2016-12-13 11:42:17,433','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','3420A3','LOW','[test]','logDBtest','just a test for curl');
/*!40000 ALTER TABLE `LOGS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `climate`
--

LOCK TABLES `climate` WRITE;
/*!40000 ALTER TABLE `climate` DISABLE KEYS */;
INSERT INTO `climate` VALUES (1,'Tundra','',''),(2,'Icecap ','',''),(3,'Highland ','',''),(4,'Desert ','',''),(5,'Semiarid ','',''),(6,'Tundra','',''),(7,'Icecap ','',''),(8,'Highland ','',''),(9,'Desert ','',''),(10,'Semiarid ','',''),(11,'Tropical wet','',''),(12,'Tropical wet & dry','',''),(13,'Humid subtropical','',''),(14,'Marine west coast','',''),(15,'Mediterranean','',''),(16,'Humid continental','',''),(17,'Subarctic','','');
/*!40000 ALTER TABLE `climate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'United-States','North America','*\r'),(2,'Cambodia','Asia','*\r'),(3,'England','Europa','*\r'),(4,'Puerto-Rico','North America','*\r'),(5,'Canada','North America','*\r'),(6,'Germany','Europe','*\r'),(7,'Outlying-US(Guam-USVI-etc)','North America','*\r'),(8,'India','Asia','*\r'),(9,'Japan','Asia','*\r'),(10,'Greece','Europe','*\r'),(11,'South','Africa','*\r'),(12,'China','Asia','*\r'),(13,'Cuba','North America','*\r'),(14,'Iran','Asia','*\r'),(15,'Honduras','North America','*\r'),(16,'Philippines','Asia','*\r'),(17,'Italy','Europe','*\r'),(18,'Poland','Europe','*\r'),(19,'Jamaica','North America','*\r'),(20,'Vietnam','Asia','*\r'),(21,'Mexico','North America','*\r'),(22,'Portugal','Europe','*\r'),(23,'Ireland','Europe','*\r'),(24,'France','Europe','*\r'),(25,'Dominican-Republic','North America','*\r'),(26,'Laos','Asia','*\r'),(27,'Ecuador','South America','*\r'),(28,'Taiwan','Asia','*\r'),(29,'Haiti','North America','*\r'),(30,'Columbia','South America','*\r'),(31,'Hungary','Europe','*\r'),(32,'Guatemala','North America','*\r'),(33,'Nicaragua','South America','*\r'),(34,'Scotland','Europe','*\r'),(35,'Thailand','Asia','*\r'),(36,'Yugoslavia','Europe','*\r'),(37,'El-Salvador','North America','*\r'),(38,'Trinadad&Tobago','South America','*\r'),(39,'Peru','South America','*\r'),(40,'Hong','Asia','*\r'),(41,'Holand-Netherlands','Europe','*\r');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `data_types`
--

LOCK TABLES `data_types` WRITE;
/*!40000 ALTER TABLE `data_types` DISABLE KEYS */;
INSERT INTO `data_types` VALUES (1,'Insensitive'),(2,'Sensitive'),(3,'Quasi-identifying'),(4,'Identifying');
/*!40000 ALTER TABLE `data_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dataunits_types`
--

LOCK TABLES `dataunits_types` WRITE;
/*!40000 ALTER TABLE `dataunits_types` DISABLE KEYS */;
INSERT INTO `dataunits_types` VALUES (3,3,4,4,4,4,1,1,3,2,1,2,3,1,2,2,2,2,2,2,2,2,1,1,1,2);
/*!40000 ALTER TABLE `dataunits_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `diseases`
--

LOCK TABLES `diseases` WRITE;
/*!40000 ALTER TABLE `diseases` DISABLE KEYS */;
INSERT INTO `diseases` VALUES (1,'Arthritis','*','*'),(2,'Asthma','*','*'),(3,'Cancer','*','*'),(4,'Chronic Fatigue Syndrome','*','*'),(5,'Diabetes','*','*'),(6,'Epilepsy','*','*'),(7,'Flu (Influenza)','*','*'),(8,'Heart Disease','*','*'),(9,'Hepatitis','*','*'),(10,'HIV/AIDS','*','*'),(11,'Meningitis','*','*'),(12,'Overweight and Obesity','*','*'),(13,'Salmonella','*','*'),(14,'Arthritis','*','*'),(15,'Sexually Transmitted Diseases (STDs)','*','*'),(16,'Stroke','*','*'),(17,'Arthritis','*','*'),(18,'Traumatic Brain Injury (TBI)','*','*'),(19,'Tuberculosis (TB)','*','*');
/*!40000 ALTER TABLE `diseases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (1,'Bachelors','Undergraduate','Higher education'),(2,'Some-college','Undergraduate','Higher education'),(3,'11th','High School','Secondary education'),(4,'HS-grad','High School','Secondary education'),(5,'Prof-school','Professional Education','Higher education'),(6,'Assoc-acdm','Professional Education','Higher education'),(7,'Assoc-voc','Professional Education','Higher education'),(8,'9th','High School','Secondary education'),(9,'7th-8th','High School','Secondary education'),(10,'12th','High School','Secondary education'),(11,'Masters','Graduate','Higher education'),(12,'1st-4th','Primary School','Primary education'),(13,'10th','High School','Secondary education'),(14,'Doctorate','Graduate','Higher education'),(15,'5th-6th','Primary School','Primary education'),(16,'Preschool','Primary School','Primary education');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `genders`
--

LOCK TABLES `genders` WRITE;
/*!40000 ALTER TABLE `genders` DISABLE KEYS */;
INSERT INTO `genders` VALUES (1,'Male','*\r','*'),(2,'Female','*\r','*');
/*!40000 ALTER TABLE `genders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `generic_personal_data`
--

LOCK TABLES `generic_personal_data` WRITE;
/*!40000 ALTER TABLE `generic_personal_data` DISABLE KEYS */;
INSERT INTO `generic_personal_data` VALUES (1,'Elliot','Smith','1111111111','555555555','john.smith@gmail.com','1111111111',1,1,'1971-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1),(2,'John','Smith','1111111112','555555552','john.smith@gmail.com','1111111112',1,2,'1981-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1),(3,'Ralph','Smith','1111111113','555555553','john.smith@gmail.com','1111111113',1,3,'1981-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1),(4,'John','Smith','1111111114','555555554','john.smith@gmail.com','1111111114',1,4,'1981-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1),(5,'Michael','Smith','1111111115','555555555','john.smith@gmail.com','11111111154',1,5,'1981-01-01',1000000,48005,1,'Gran Vía','48005',1,1,2,1,1,1,1,1);
/*!40000 ALTER TABLE `generic_personal_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `habits`
--

LOCK TABLES `habits` WRITE;
/*!40000 ALTER TABLE `habits` DISABLE KEYS */;
/*!40000 ALTER TABLE `habits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hobbies`
--

LOCK TABLES `hobbies` WRITE;
/*!40000 ALTER TABLE `hobbies` DISABLE KEYS */;
/*!40000 ALTER TABLE `hobbies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `marital_status`
--

LOCK TABLES `marital_status` WRITE;
/*!40000 ALTER TABLE `marital_status` DISABLE KEYS */;
INSERT INTO `marital_status` VALUES (1,'Married-civ-spouse','spouse present','*\r'),(2,'Divorced','spouse not present','*\r'),(3,'Never-married','spouse not present','*\r'),(4,'Separated','spouse not present','*\r'),(5,'Widowed','spouse not present','*\r'),(6,'Married-spouse-absent','spouse not present','*\r'),(7,'Married-AF-spouse','spouse present','*\r');
/*!40000 ALTER TABLE `marital_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `occupation`
--

LOCK TABLES `occupation` WRITE;
/*!40000 ALTER TABLE `occupation` DISABLE KEYS */;
INSERT INTO `occupation` VALUES (1,'Tech-suppo','Technical','*\r'),(2,'Craft-repa','Technical','*\r'),(3,'Other-serv','Other','*\r'),(4,'Sales','Nontechnic','*\r'),(5,'Exec-manag','Nontechnic','*\r'),(6,'Prof-speci','Technical','*\r'),(7,'Handlers-c','Nontechnic','*\r'),(8,'Machine-op','Technical','*\r'),(9,'Adm-cleric','Other','*\r'),(10,'Farming-fi','Other','*\r'),(11,'Transport-','Other','*\r'),(12,'Priv-house','Other','*\r'),(13,'Protective','Other','*\r'),(14,'Armed-Forc','Other','*\r');
/*!40000 ALTER TABLE `occupation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_data_access_levels`
--

LOCK TABLES `personal_data_access_levels` WRITE;
/*!40000 ALTER TABLE `personal_data_access_levels` DISABLE KEYS */;
INSERT INTO `personal_data_access_levels` VALUES (1,'BIRTH_CITY_ID',0),(1,'CELL_PHONE_NUMBER',2),(1,'DATE_OF_BIRTH',0),(1,'DISEASES',2),(1,'EDUCATION_ID',0),(1,'EMAIL_ADDRESS',2),(1,'GENDER_ID',0),(1,'HABITS',0),(1,'HOBBIES',0),(1,'IDENTIFICATION_NUMBER',2),(1,'MARITAL_STATUS_ID',0),(1,'NAME',2),(1,'NATIVE_COUNTRY_ID',0),(1,'NUMBER_OF_CHILDREN',0),(1,'OCCUPATION_ID',0),(1,'POLITICAL_TENDENCY_ID',0),(1,'RACE_ID',0),(1,'RELIGION',2),(1,'RESIDENCE_CITY_CITIZENS_NUMBER',0),(1,'RESIDENCE_CITY_ID',0),(1,'RESIDENCE_POST_CODE',0),(1,'RESIDENCE_STREET',0),(1,'SALARY_CLASS_ID',0),(1,'SPORTS',0),(1,'SSN',2),(1,'SURNAME',2),(1,'WORK_CLASS_ID',0);
/*!40000 ALTER TABLE `personal_data_access_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_data_access_levels_requester`
--

LOCK TABLES `personal_data_access_levels_requester` WRITE;
/*!40000 ALTER TABLE `personal_data_access_levels_requester` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_data_access_levels_requester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_diseases`
--

LOCK TABLES `personal_diseases` WRITE;
/*!40000 ALTER TABLE `personal_diseases` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_diseases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_habits`
--

LOCK TABLES `personal_habits` WRITE;
/*!40000 ALTER TABLE `personal_habits` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_habits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_hobbies`
--

LOCK TABLES `personal_hobbies` WRITE;
/*!40000 ALTER TABLE `personal_hobbies` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_hobbies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_religions`
--

LOCK TABLES `personal_religions` WRITE;
/*!40000 ALTER TABLE `personal_religions` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_religions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_sports`
--

LOCK TABLES `personal_sports` WRITE;
/*!40000 ALTER TABLE `personal_sports` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_sports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `political_tendency`
--

LOCK TABLES `political_tendency` WRITE;
/*!40000 ALTER TABLE `political_tendency` DISABLE KEYS */;
/*!40000 ALTER TABLE `political_tendency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `provinces`
--

LOCK TABLES `provinces` WRITE;
/*!40000 ALTER TABLE `provinces` DISABLE KEYS */;
/*!40000 ALTER TABLE `provinces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `race`
--

LOCK TABLES `race` WRITE;
/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` VALUES (1,'White','*\r','*'),(2,'Asian-Pac-Islander','*\r','*'),(3,'Amer-Indian-Eskimo','*\r','*'),(4,'Other','*\r','*'),(5,'Black','*\r','*');
/*!40000 ALTER TABLE `race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `religions`
--

LOCK TABLES `religions` WRITE;
/*!40000 ALTER TABLE `religions` DISABLE KEYS */;
/*!40000 ALTER TABLE `religions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `salary_class`
--

LOCK TABLES `salary_class` WRITE;
/*!40000 ALTER TABLE `salary_class` DISABLE KEYS */;
INSERT INTO `salary_class` VALUES (1,'<=50K','*','*'),(2,'>50K','*','*');
/*!40000 ALTER TABLE `salary_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sports`
--

LOCK TABLES `sports` WRITE;
/*!40000 ALTER TABLE `sports` DISABLE KEYS */;
/*!40000 ALTER TABLE `sports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `work_class`
--

LOCK TABLES `work_class` WRITE;
/*!40000 ALTER TABLE `work_class` DISABLE KEYS */;
INSERT INTO `work_class` VALUES (1,'Private','Non-Government','*\r'),(2,'Self-emp-not-inc','Non-Government','*\r'),(3,'Self-emp-inc','Non-Government','*\r'),(4,'Federal-gov','Government','*\r'),(5,'Local-gov','Government','*\r'),(6,'State-gov','Government','*\r'),(7,'Without-pay','Unemployed','*\r'),(8,'Never-worked','Unemployed','*\r');
/*!40000 ALTER TABLE `work_class` ENABLE KEYS */;
UNLOCK TABLES;

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
