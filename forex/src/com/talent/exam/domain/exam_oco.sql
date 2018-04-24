/*
Navicat MySQL Data Transfer

Source Server         : forex
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-10-12 09:15:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_oco
-- ----------------------------
DROP TABLE IF EXISTS `exam_oco`;
CREATE TABLE `exam_oco` (
  `exam_oco_NO` int(5) NOT NULL AUTO_INCREMENT COMMENT 'oco类型题目要素编号',
  `exam_NO` int(11) NOT NULL COMMENT '考试题目编号',
  `acc_type_no` char(1) NOT NULL COMMENT '冗余字段，方便查询',
  `DIRECTION` char(1) NOT NULL COMMENT '买卖方向',
  `ACC` char(3) NOT NULL COMMENT '买卖币种',
  `TAKE_PROFIT_AMOUNT` varchar(30) NOT NULL COMMENT 'take profit交易数量',
  `TAKE_PROFIT_PRICE` varchar(30) NOT NULL COMMENT 'take profit买卖的价格',
  `STOP_LOSS_AMOUNT` varchar(30) NOT NULL COMMENT 'stop loss交易数量',
  `STOP_LOSS_PRICE` varchar(30) NOT NULL COMMENT 'stop loss 买卖的价格',
  `MONITOR_PRICE` char(3) NOT NULL COMMENT '模拟类型,包括ask、bid',
  `GOOD_FROM` varchar(8) NOT NULL COMMENT '开始日期,格式20150918',
  `GOOD_TILL` varchar(8) NOT NULL COMMENT '结束日期,格式20160817',
  `STEP` int(1) NOT NULL COMMENT '步骤，最多10步',
  `exam_score` varchar(100) NOT NULL COMMENT '存放老师或者学生的分数值，格式形如20,0,50,0,0,30,0,100',
  `user_type` char(1) NOT NULL COMMENT '使用者类型,S或者T',
  PRIMARY KEY (`exam_oco_NO`),
  UNIQUE KEY `exam_NO_STEP` (`exam_NO`,`STEP`) COMMENT '保证题目与步骤的唯一性',
  CONSTRAINT `exam_oco_ibfk_1` FOREIGN KEY (`exam_NO`) REFERENCES `exam_content` (`exam_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oco交易要素表';
