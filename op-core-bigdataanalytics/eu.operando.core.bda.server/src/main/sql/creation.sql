-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: operando_bigdataanalyticsdb
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `users` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `climate`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `climate`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `climate` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (1,'job 01'),(2,'job 02'),(3,'job 03'),(4,'job 04'),(5,'job 05'),(6,'job 06'),(7,'job 07');
/*!40000 ALTER TABLE `climate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usersjobs`
--

DROP TABLE IF EXISTS `usersJobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usersjobs` (
  `user_id` int(10) DEFAULT NULL,
  `job_id` int(10) DEFAULT NULL,
  `subscribed` boolean DEFAULT false,
  `frequency` varchar(50) DEFAULT NULL,
  `downloadLink` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`job_id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_job_id` (`job_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_id` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersjobs`
--

LOCK TABLES `usersJobs` WRITE;
/*!40000 ALTER TABLE `DEAL` DISABLE KEYS */;
INSERT INTO `usersJobs` VALUES (3,2,true,'monthly',null),(3,5,false,null,null),(3,6,true,'dayly',null),(3,7,true,'hourly',null);
/*!40000 ALTER TABLE `DEAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'operando_bigdataanalyticsdb'
--

--
-- Final view structure for view `operando_bigdataanalyticsdb`
--

/*!50001 DROP TABLE IF EXISTS `operando_bigdataanalyticsdb_view`*/;
/*!50001 DROP VIEW IF EXISTS `operando_bigdataanalyticsdb_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `operando_bigdataanalyticsdb_view` AS select distinct `occupation`.`DESCRIPTION_0` AS `OCCUPATION`,`salary_class`.`DESCRIPTION_0` AS `SALARY_CLASS`,`genders`.`DESCRIPTION_0` AS `GENDER`,`education`.`DESCRIPTION_0` AS `EDUCATION`,`countries`.`DESCRIPTION_0` AS `COUNTRY`,`race`.`DESCRIPTION_0` AS `RACE`,`generic_personal_data`.`EMAIL_ADDRESS` AS `EMAIL_ADDRESS`,`generic_personal_data`.`CELL_PHONE_NUMBER` AS `CELL_PHONE_NUMBER`,`generic_personal_data`.`SURNAME` AS `SURNAME`,`generic_personal_data`.`NUMBER_OF_CHILDREN` AS `NUMBER_OF_CHILDREN`,`generic_personal_data`.`RESIDENCE_POST_CODE` AS `RESIDENCE_POST_CODE`,`generic_personal_data`.`NAME` AS `NAME`,`generic_personal_data`.`IDENTIFICATION_NUMBER` AS `IDENTIFICATION_NUMBER`,`generic_personal_data`.`DATE_OF_BIRTH` AS `DATE_OF_BIRTH`,`generic_personal_data`.`SSN` AS `SSN`,`marital_status`.`DESCRIPTION_0` AS `MARITAL_STATUS`,`work_class`.`DESCRIPTION_0` AS `WORK_CLASS` from ((((((((`occupation` join `salary_class`) join `genders`) join `education`) join `countries`) join `race`) join `generic_personal_data` on(((`occupation`.`ID` = `generic_personal_data`.`OCCUPATION_ID`) and (`salary_class`.`ID` = `generic_personal_data`.`SALARY_CLASS_ID`) and (`genders`.`ID` = `generic_personal_data`.`GENDER_ID`) and (`education`.`ID` = `generic_personal_data`.`EDUCATION_ID`) and (`countries`.`ID` = `generic_personal_data`.`NATIVE_COUNTRY_ID`) and (`race`.`ID` = `generic_personal_data`.`RACE_ID`)))) join `marital_status` on((`marital_status`.`ID` = `generic_personal_data`.`MARITAL_STATUS_ID`))) join `work_class` on((`work_class`.`ID` = `generic_personal_data`.`WORK_CLASS_ID`))) */;
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
