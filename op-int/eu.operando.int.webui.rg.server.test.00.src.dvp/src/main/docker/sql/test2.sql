-- phpMyAdmin SQL Dump
-- version 4.0.10.10
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Mag 22, 2017 alle 17:00
-- Versione del server: 5.1.73
-- Versione PHP: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `operando_report`
--

CREATE DATABASE IF NOT EXISTS `operando_report` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `operando_report`;
-- --------------------------------------------------------

--
-- Struttura della tabella `t_report_mng_list`
--

DROP TABLE IF EXISTS `t_report_mng_list`;
CREATE TABLE IF NOT EXISTS `t_report_mng_list` (
  `Report` varchar(200) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Version` varchar(100) NOT NULL,
  `Location` varchar(500) NOT NULL,
  `Parameters` varchar(500) DEFAULT NULL,
  `OSPs` varchar(100) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dump dei dati per la tabella `t_report_mng_list`
--

INSERT INTO `t_report_mng_list` (`Report`, `Description`, `Version`, `Location`, `Parameters`, `OSPs`, `ID`) VALUES
('Age', 'Age range', '1', 'http://www.birt.sassuolo.info/birt-viewer_3_7/frameset', '__report=pdi/report_operando/age.rptdesign&age ranges min=20&age ranges max=50', 'ASLBG,GASLINI', 1),
('Pathology', 'Pathology', '1', 'http://www.birt.sassuolo.info/birt-viewer_3_7/frameset', '__report=pdi/report_operando/pathology.rptdesign&alcohol while=1', 'ASLBG', 2),
('Province', 'Province', '1', 'http://www.birt.sassuolo.info/birt-viewer_3_7/frameset', '__report=pdi/report_operando/province.rptdesign&province=Bologna', 'ASLBG,GASLINI', 3),
('Familiar disease', 'Familiar disease', '1', 'http://www.birt.sassuolo.info/birt-viewer_3_7/frameset', '__report=pdi/report_operando/Familiardisease.rptdesign&Familiar disease=1', 'ASLBG,GASLINI', 4),
('Sex', 'Sex', '1', 'http://www.birt.sassuolo.info/birt-viewer_3_7/frameset', '__report=pdi/report_operando/sex.rptdesign&sex=M', 'GASLINI', 5),
('Related diseases', 'Related diseases', '1', 'http://www.birt.sassuolo.info/birt-viewer_3_7/frameset', '__report=pdi/report_operando/Relateddiseases.rptdesign&Related diseases=cirrhosis', 'ASLBG,GASLINI', 6),
('Age of onset disease', 'Age of onset disease', '1', 'http://www.birt.sassuolo.info/birt-viewer_3_7/frameset', '__report=pdi/report_operando/Ageofonsetdisease.rptdesign&Age of onset disease=14', 'ASLBG,GASLINI', 7),
('Trial ASLBG', 'Trial ASLBG', '1', 'http://www.birt.sassuolo.info/birt-viewer_3_7/frameset', '__report=pdi/report_operando/trialASLBG.rptdesign', 'ASLBG', 8),
('Volunteer Breakdown Report', 'Breakdown of employment information and volunteering preferences ', '1', 'http://integration.operando.esilab.org:8120/operando/webui/birt/frameset', '__report=pdi/report_operando/VolunteerEmploymentDetailsAndAssistancePreferences.rptdesign', 'Ami', '9');

-- --------------------------------------------------------

--
-- Struttura della tabella `t_report_mng_request`
--

DROP TABLE IF EXISTS `t_report_mng_request`;
CREATE TABLE IF NOT EXISTS `t_report_mng_request` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `InsertDate` datetime NOT NULL,
  `Name` varchar(200) NOT NULL,
  `Email` varchar(200) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dump dei dati per la tabella `t_report_mng_request`
--

INSERT INTO `t_report_mng_request` (`ID`, `InsertDate`, `Name`, `Email`, `Description`) VALUES
(1, '0000-00-00 00:00:00', 'Giulia', 'giulia@progettidiimpresa.it', 'Prova'),
(2, '0000-00-00 00:00:00', 'Daniele', 'daniele.detecterror@progettidiimpresa.it', 'test'),
(3, '0000-00-00 00:00:00', 'Daneiel', 'daniele@progettidiimpresa.it', 'test'),
(4, '0000-00-00 00:00:00', 'test', 'testtest', 'lalala'),
(19, '0000-00-00 00:00:00', 'test', 'federico.dibernardo@progettidiimpresa.it', 'Richiesta di test di un report'),
(13, '2016-11-01 00:00:00', 'Luigi De Luigi', 'luigi.deluigi@email.it', 'Richiedo la creazione di un report personalizzato per me'),
(14, '2016-10-11 00:00:00', 'Federico De Federichi', 'federico.defederichi@email.it', 'Richiedo la creazione di un report personalizzato per la mamma'),
(15, '2016-12-04 00:00:00', 'Alessandro De Alessandri', 'alessandro.dealessandri@email.it', 'Richiedo la creazione di un report personalizzato per la figlia'),
(18, '2017-02-06 15:39:56', 'Daniele', 'daniele.detecterror@progettidiimpresa.it', 'La mia richiesta mira a richiedere la creazione di un report che risponda alle mie esigenze');

-- --------------------------------------------------------

--
-- Struttura della tabella `t_report_mng_results`
--

DROP TABLE IF EXISTS `t_report_mng_results`;
CREATE TABLE IF NOT EXISTS `t_report_mng_results` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ExecutionDate` datetime NOT NULL,
  `Report` varchar(200) NOT NULL,
  `ReportDescription` varchar(200) NOT NULL,
  `ReportVersion` varchar(100) NOT NULL,
  `OSP` varchar(200) NOT NULL,
  `FileName` varchar(500) NOT NULL,
  `IDReport` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dump dei dati per la tabella `t_report_mng_results`
--

INSERT INTO `t_report_mng_results` (`ID`, `ExecutionDate`, `Report`, `ReportDescription`, `ReportVersion`, `OSP`, `FileName`, `IDReport`) VALUES
(17, '2016-10-18 17:54:46', 'Age', 'Age range', '1', 'ASLBG-GASLINI', 'Age_53_20161018.pdf', 1),
(16, '2016-10-18 17:54:46', 'Age', 'Age range', '1', 'ASLBG-GASLINI', 'Age_52_20161018.pdf', 1),
(15, '2016-10-18 17:54:46', 'Age', 'Age range', '1', 'ASLBG-GASLINI', 'Age_46_20161018.pdf', 1),
(14, '2016-10-18 17:54:46', 'Age', 'Age range', '1', 'ASLBG-GASLINI', 'Age_44_20161018.pdf', 1),
(8, '2016-10-18 13:57:50', 'Age', 'Age range', '1', 'ASLBG-GASLINI', 'Age_1_20161018.pdf', 1),
(9, '2016-10-18 13:57:50', 'Province', 'Province', '1', 'ASLBG-GASLINI', 'Province_3_20161018.pdf', 3),
(10, '2016-10-18 13:57:50', 'Familiar disease', 'Familiar disease', '1', 'ASLBG-GASLINI', 'Familiar disease_4_20161018.pdf', 4),
(11, '2016-10-18 13:57:50', 'Sex', 'Sex', '1', 'ASLBG-GASLINI', 'Sex_17_20161018.pdf', 5),
(12, '2016-10-18 13:57:50', 'Sex', 'Sex', '1', 'ASLBG-GASLINI', 'Sex_18_20161018.pdf', 5),
(13, '2016-10-18 13:57:50', 'Sex', 'Sex', '1', 'ASLBG-GASLINI', 'Sex_19_20161018.pdf', 5),
(18, '2016-10-18 20:54:46', 'Age', 'Age range', '1', 'ASLBG-GASLINI', 'Age_54_20161018.pdf', 1),
(19, '2016-10-18 21:54:46', 'Pathology', 'Pathology', '1', 'ASLBG', 'Pathology_55_20161018.pdf', 2),
(20, '2016-10-18 19:54:46', 'Age', 'Age range', '1', 'ASLBG-GASLINI', 'Age_56_20161018.pdf', 1),
(21, '2016-10-18 22:53:46', 'Trial ASLBG', 'Trial ASLBG', '1', 'ASLBG', 'Trial ASLBG_20170413.pdf', 8),
(22, '2017-05-24 22:53:46', 'Trial ASLBG', 'Trial ASLBG', '1', 'ASLBG', 'Trial ASLBG_20170524.pdf', 8);

-- --------------------------------------------------------

--
-- Struttura della tabella `t_report_mng_schedules`
--

DROP TABLE IF EXISTS `t_report_mng_schedules`;
CREATE TABLE IF NOT EXISTS `t_report_mng_schedules` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OSPs` varchar(200) NOT NULL,
  `Report` varchar(200) NOT NULL,
  `StartDate` datetime NOT NULL,
  `RepeatEveryNumb` int(11) NOT NULL,
  `RepeatEveryType` varchar(200) NOT NULL,
  `DayOfWeek` varchar(200) NOT NULL,
  `StoragePeriodNumb` int(11) NOT NULL,
  `StoragePeriodType` varchar(200) NOT NULL,
  `DescriptionSchedules` varchar(200) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Version` varchar(200) NOT NULL,
  `Lastrun` datetime DEFAULT NULL,
  `NextScheduled` datetime NOT NULL,
  `GiornoMese` int(11) NOT NULL,
  `GiornoAnno` date NOT NULL,
  `IDReport` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=229 ;

--
-- Dump dei dati per la tabella `t_report_mng_schedules`
--

INSERT INTO `t_report_mng_schedules` (`ID`, `OSPs`, `Report`, `StartDate`, `RepeatEveryNumb`, `RepeatEveryType`, `DayOfWeek`, `StoragePeriodNumb`, `StoragePeriodType`, `DescriptionSchedules`, `Description`, `Version`, `Lastrun`, `NextScheduled`, `GiornoMese`, `GiornoAnno`, `IDReport`) VALUES
(3, 'GASLINI', 'Province', '2016-10-13 00:00:00', 1, 'WEEK(s)', 'Mon,Tue', 1, 'DAY(s)', '', '', '', '2016-10-18 13:57:50', '2016-10-25 13:57:50', 0, '0000-00-00', 3),
(4, 'ASLBG,GASLINI', 'Familiar disease', '2016-10-14 12:00:00', 5, 'WEEK(s)', '', 1, 'MONTHS(s)', '', '', '', '2016-10-18 13:57:50', '2016-11-18 12:00:00', 0, '2017-01-09', 4),
(18, 'GASLINI', 'Sex', '2016-10-18 11:15:00', 2, 'WEEK(s)', 'Tue,Wed', 3, 'DAY(s)', '', '', '', '2016-10-18 13:57:50', '2016-11-01 11:15:00', 0, '2016-11-10', 5),
(53, 'ASLBG,GASLINI', 'Age', '2016-11-16 11:55:00', 2, 'MONTH(s)', '', 3, 'MONTH(s)', '', '', '', '2016-10-18 17:54:46', '2017-01-16 11:55:00', 3, '2017-03-09', 1),
(55, 'ASLBG,GASLINI', 'Pathology', '2016-10-18 17:40:00', 2, 'DAY(s)', 'Mon,Tue', 1, 'MONTH(s)', '', '', '', '2016-10-18 17:54:46', '2016-10-20 17:40:00', 0, '2016-10-27', 2),
(91, 'ASLBG,GASLINI', 'Related diseases', '2016-10-27 10:45:00', 1, 'YEAR(s)', '', 1, 'MONTH(s)', '', '', '', NULL, '2017-10-27 10:45:00', 0, '2016-10-27', 0),
(158, 'ASLBG,GASLINI', 'Age', '2016-10-27 14:45:00', 1, 'MONTH(s)', '', 6, 'MONTH(s)', '', '', '', NULL, '2016-11-27 14:45:00', 6, '2016-11-03', 0),
(167, 'ASLBG,GASLINI', 'Age', '2016-11-02 12:30:00', 2, 'WEEK(s)', 'Mon,Tue', 1, 'MONTHS(s)', '', '', '', NULL, '2016-11-16 12:30:00', 0, '2016-11-07', 0),
(210, 'ASLBG,GASLINI', 'Age', '2016-11-09 06:30:00', 2, 'WEEK(s)', 'Fri', 1, 'MONTHS(s)', '', '', '', NULL, '2016-11-23 06:30:00', 0, '2016-11-07', 0),
(213, 'GASLINI', 'Age', '2016-11-09 05:55:00', 3, 'DAY(s)', '', 5, 'DAY(s)', '', '', '', NULL, '2016-11-12 05:55:00', 0, '2016-11-07', 0),
(220, 'ASLBG,GASLINI', 'Sex', '2016-11-10 15:50:58', 0, 'DAY(s)', '', 0, 'DAY(s)', '', '', '', NULL, '2016-11-10 15:50:58', 0, '2016-11-10', 0),
(223, 'ASLBG,GASLINI', 'Age', '2016-12-13 14:50:00', 1, 'DAY(s)', '', 1, 'MONTH(s)', '', '', '', NULL, '2016-12-14 14:50:00', 0, '2016-12-03', 0),
(224, 'ASLBG,GASLINI', 'Age', '2016-12-05 11:32:00', 1, 'DAY(s)', '', 1, 'MONTH(s)', '', '', '', NULL, '2016-12-06 11:32:00', 0, '2016-12-05', 0),
(225, 'ASLBG,GASLINI', 'Familiar disease', '2017-01-09 16:49:39', 0, 'DAY(s)', '', 0, 'DAY(s)', '', '', '', NULL, '2017-01-09 16:49:39', 0, '2017-01-09', 0),
(226, 'ASLBG,GASLINI', 'Age', '2017-01-09 05:34:00', 0, 'DAY(s)', '', 1, 'MONTH(s)', '', '', '', NULL, '2017-01-09 05:34:00', 0, '2017-01-09', 0),
(227, 'ASLBG,GASLINI', 'Sex', '2017-02-03 03:48:00', 0, 'DAY(s)', '', 1, 'MONTH(s)', '', '', '', NULL, '2017-02-03 03:48:00', 0, '2017-02-03', 0),
(228, 'ASLBG,GASLINI', 'Trial ASLBG', '2017-05-24 02:48:00', 1, 'MONTH(s)', '', 1, 'MONTH(s)', '', '', '', '2017-05-24 02:48:00', '2017-06-24 02:48:00', 0, '2017-06-24', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
