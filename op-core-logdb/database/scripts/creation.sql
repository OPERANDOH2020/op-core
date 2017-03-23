-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: operando_logdb
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
  `MESSAGE` varchar(1000) DEFAULT NULL,
  `LOGTYPE` varchar(20) DEFAULT NULL,
  `AFFECTED_USER_ID` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOGS`
--

LOCK TABLES `LOGS` WRITE;
/*!40000 ALTER TABLE `LOGS` DISABLE KEYS */;
INSERT INTO `LOGS` VALUES ('username','2016-06-07 15:10:22,174','io.swagger.api.impl.LogApiServiceImpl','INFO','Module','1001','Low','[keyword1, keyword2, keyword3]','First log','First log for testing purposes',NULL,NULL),('username','2016-10-19 13:02:41,722','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1002','LOW','[keywordA, keywordB, keywordC]','First log','First log for testing purposes',NULL,NULL),('username','2016-10-19 13:05:27,467','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1002','LOW','[keywordA, keywordB, keywordC]','First log','First log for testing purposes',NULL,NULL),('username','2016-10-19 13:49:52,484','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1002','LOW','[keywordA, keywordB, keywordC]','First log','First log for testing purposes',NULL,NULL),('username','2016-12-07 12:28:27,342','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes',NULL,NULL),('username','2016-12-07 17:11:53,932','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes',NULL,NULL),('username','2016-12-13 09:55:59,366','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes',NULL,NULL),('username','2016-12-13 11:42:17,433','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','3420A3','LOW','[test]','logDBtest','just a test for curl',NULL,NULL),('003','2017-02-16 11:51:33,564','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes',NULL,NULL),('003','2017-02-16 12:00:08,814','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes',NULL,NULL),('001','2017-02-16 15:24:21,888','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1001','NORMAL','[]','Privacy settings discrepancy','The privacy settings for user 001 with OSP 12 are not as required. This requires action.',NULL,NULL),('001','2017-02-16 15:50:42,634','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','3420A3','LOW','[test]','logDBtest','just a test for curl',NULL,NULL),('003','2017-02-20 10:31:36,849','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes',NULL,NULL),('001','2017-02-20 10:45:06,890','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1001','NORMAL','[]','Privacy settings discrepancy','The privacy settings for user 001 with OSP 12 are not as required. This requires action.',NULL,NULL),('001','2017-02-20 12:47:03,888','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','Log on 07/12','Log on 07/12','Log on 07/12 for testing purposes',NULL,NULL),('001','2017-02-21 17:12:29,491','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','Log on 07/12','Log on 07/12','Log on 07/12 for testing purposes',NULL,NULL),('001','2017-02-22 10:57:28,094','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','3420A3','LOW','[test1, test2]','logDBtest','just a test for curl',NULL,NULL),('','2017-02-22 17:03:44,238','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('','2017-02-22 17:04:15,802','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('','2017-02-22 17:05:05,343','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('','2017-02-22 17:06:05,024','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('','2017-02-22 17:30:14,697','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('','2017-02-22 19:43:29,248','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('','2017-02-22 19:43:30,007','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('','2017-02-22 19:44:28,693','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('','2017-02-22 20:05:51,697','io.swagger.api.impl.LogApiServiceImpl','ERROR','','','','','','Service Ticket validation failed: {0}',NULL,NULL),('001','2017-02-24 15:49:25,375','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1001','NORMAL','[]','Privacy settings discrepancy','The privacy settings for user 001 with OSP 12 are not as required. This requires action.',NULL,NULL),('001','2017-02-24 16:05:27,949','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1001','NORMAL','[]','Privacy settings discrepancy','The privacy settings for user 001 with OSP 12 are not as required. This requires action.',NULL,NULL),('001','2017-03-17 15:58:10,887','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-17 15:59:49,318','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-17 15:59:49,328','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','27','NOTIFICATION','27'),('001','2017-03-20 11:41:22,993','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 11:41:23,664','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','27','NOTIFICATION','27'),('001','2017-03-20 14:28:31,026','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 14:28:31,491','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','28','NOTIFICATION','28'),('001','2017-03-20 14:30:24,513','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 14:32:19,237','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 14:37:53,980','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 14:39:35,700','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 14:45:18,015','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 14:47:27,481','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 14:49:59,277','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keywordA, keywordB, keywordC]','Log on 07/12','Log on 07/12 for testing purposes','NOTIFICATION','28'),('001','2017-03-20 16:53:36,900','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 17:07:30,280','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 17:09:24,154','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 17:10:47,880','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 17:15:27,529','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','',''),('001','2017-03-20 17:24:56,570','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','NOTIFICATION','28'),('001','2017-03-20 17:25:48,982','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','NOTIFICATION','28'),('001','2017-03-20 17:26:27,397','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','NOTIFICATION','28'),('001','2017-03-22 14:03:46,362','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','NOTIFICATION','43'),('001','2017-03-22 14:41:12,650','io.swagger.api.impl.LogApiServiceImpl','INFO','MODULE','1007','LOW','[keyword1]','Log on 07/12','Log on 07/12 for testing purposes','','');
/*!40000 ALTER TABLE `LOGS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-23 11:53:44
