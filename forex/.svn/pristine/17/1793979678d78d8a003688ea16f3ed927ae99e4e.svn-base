-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: forex
-- ------------------------------------------------------
-- Server version	5.7.13

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
-- Table structure for table `margin_spot_info`
--

DROP TABLE IF EXISTS `margin_spot_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `margin_spot_info` (
  `ID` int(12) NOT NULL AUTO_INCREMENT COMMENT '即期保证金单号id',
  `TRAN_TYPE` char(10) NOT NULL COMMENT '即期保证金的账户类型，一般是B',
  `TRAN_NO` varchar(14) NOT NULL COMMENT '即期保证金交易单号，默认以MS开头',
  `USER_NUM` varchar(20) NOT NULL COMMENT '交易的用户账号',
  `WE_CCY` char(3) NOT NULL COMMENT '卖出的货币',
  `ANA_CCY` char(3) NOT NULL COMMENT '买入的货币',
  `ACCOUNT` varchar(20) NOT NULL COMMENT '使用用户的账号',
  `ACC_AMOUNT` varchar(30) NOT NULL COMMENT '用户保证金的金额',
  `DEAL_AMT` varchar(30) NOT NULL COMMENT '用户扩大的资金，单位是保证金的单位',
  `PRICE` varchar(30) NOT NULL COMMENT '买入时刻的市场价格',
  `STATUE` char(1) NOT NULL COMMENT '保证金状态 A：激活 E：平仓 D：完成 L：锁仓 R:归还',
  `CREATE_DATATIME` varchar(14) NOT NULL COMMENT '创建单号的时间',
  `DIRECTION` char(1) NOT NULL COMMENT '买卖方向 1、买入 2、卖出',
  `ACCRUAL` varchar(30) NOT NULL COMMENT '需向银行交纳的利息，即10个基点的买入货币',
  PRIMARY KEY (`ID`),
  KEY `margin_spot_info_index` (`TRAN_TYPE`,`TRAN_NO`,`USER_NUM`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `margin_spot_info`
--

LOCK TABLES `margin_spot_info` WRITE;
/*!40000 ALTER TABLE `margin_spot_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `margin_spot_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq`
--

DROP TABLE IF EXISTS `seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seq` (
  `TRAN_TYPE` varchar(14) NOT NULL,
  `SEQ_NO` decimal(15,4) NOT NULL,
  PRIMARY KEY (`TRAN_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq`
--

LOCK TABLES `seq` WRITE;
/*!40000 ALTER TABLE `seq` DISABLE KEYS */;
INSERT INTO `seq` VALUES ('FC',11.0000),('FD',2.0000),('MB',1.0000),('MO',8.0000),('MS',0.0000),('OC',21.0000),('OO',2.0000),('SL',1013.0000),('SP',1.0000),('ST',6.0000),('TP',8.0000);
/*!40000 ALTER TABLE `seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-25 17:32:31
