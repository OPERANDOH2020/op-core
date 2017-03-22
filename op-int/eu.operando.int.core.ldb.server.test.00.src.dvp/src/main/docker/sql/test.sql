-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: operando_logdb
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
-- Dumping data for table `LOGS`
--

LOCK TABLES `LOGS` WRITE;
/*!40000 ALTER TABLE `LOGS` DISABLE KEYS */;
INSERT INTO `LOGS` (`USER_ID`, `DATED`, `LOGGER`, `LEVEL`, `REQUESTERTYPE`, `REQUESTERID`, `LOGPRIORITY`, `KEYWORDS`, `TITLE`, `MESSAGE`) VALUES
('001', '2016-12-09 09:10:22,174', 'io.swagger.api.impl.WdTaskUserPrivacySettingsCheck', 'INFO', 'MODULE', '1001', 'NORMAL', '[privacy]', 'Privacy settings discrepancy', 'The privacy settings for user 001 with OSP 12 are not as required. This requires action.');


INSERT INTO `LOGS` (`USER_ID`, `DATED`, `LOGGER`, `LEVEL`, `REQUESTERTYPE`, `REQUESTERID`, `LOGPRIORITY`, `KEYWORDS`, `TITLE`, `MESSAGE`) VALUES
('002', '2016-12-10 10:11:21,075', 'io.swagger.api.impl.UsersApiServiceImpl', 'ERROR', 'MODULE', '1002', 'NORMAL', '[users,delete]', 'Delete UPP', 'Error. No document exits to be deleted.');

INSERT INTO `LOGS` (`USER_ID`, `DATED`, `LOGGER`, `LEVEL`, `REQUESTERTYPE`, `REQUESTERID`, `LOGPRIORITY`, `KEYWORDS`, `TITLE`, `MESSAGE`) VALUES
('002', '2016-12-11 10:11:21,075', 'io.swagger.api.impl.OSPApiServiceImpl', 'INFO', 'PROCESS', '1003', 'NORMAL', '[osp,privacy]', 'OSP PUT', 'OSP PUT received.');
INSERT INTO `LOGS` (`USER_ID`, `DATED`, `LOGGER`, `LEVEL`, `REQUESTERTYPE`, `REQUESTERID`, `LOGPRIORITY`, `KEYWORDS`, `TITLE`, `MESSAGE`) VALUES
('003', '2016-12-12 09:10:22,174', 'io.swagger.api.impl.OffersApiServiceImpl', 'INFO', 'MODULE', '1004', 'NORMAL', '[offer,update]', 'Update Offer', 'The offer has been updated succesfully.');


INSERT INTO `LOGS` (`USER_ID`, `DATED`, `LOGGER`, `LEVEL`, `REQUESTERTYPE`, `REQUESTERID`, `LOGPRIORITY`, `KEYWORDS`, `TITLE`, `MESSAGE`) VALUES
('004', '2016-12-13 10:11:21,075', 'io.swagger.api.impl.PersonaldataApiServiceImpl', 'INFO', 'MODULE', '1004', 'NORMAL', '[annonymization,personal]', 'Anonymized Personal Data', 'The personal data has been anonymized.');
/*!40000 ALTER TABLE `LOGS` ENABLE KEYS */;
UNLOCK TABLES;