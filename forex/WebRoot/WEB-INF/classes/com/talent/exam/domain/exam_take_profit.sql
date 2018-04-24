/*
Navicat MySQL Data Transfer

Source Server         : forex
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-09-13 17:20:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_take_profit
-- ----------------------------
DROP TABLE IF EXISTS `exam_take_profit`;
CREATE TABLE `exam_take_profit` (
  `exam_take_profit_no` int(5) NOT NULL AUTO_INCREMENT,
  `exam_NO` int(11) NOT NULL COMMENT '考试题目编号',
  `acc_type_no` char(1) NOT NULL COMMENT '冗余字段，方便查询',
  `DIRECTION` char(1) NOT NULL COMMENT '买卖方向',
  `ACC` char(3) NOT NULL COMMENT '买卖币种',
  `ACC_AMOUNT` varchar(30) NOT NULL COMMENT '买卖数量',
  `price` varchar(30) NOT NULL COMMENT '买卖的价格',
  `GOOD_FROM` varchar(20) NOT NULL COMMENT '开始时间',
  `GOOD_TILL` varchar(20) NOT NULL COMMENT '结束时间',
  `STEP` int(1) NOT NULL COMMENT '步骤，最多不超过10',
  `exam_score` varchar(100) NOT NULL COMMENT '存放老师或者学生的分数值，格式形如20,0,50,0,0,30,0,100',
  `user_type` char(1) NOT NULL COMMENT '使用者类型,S或者T',
  PRIMARY KEY (`exam_take_profit_no`),
  UNIQUE KEY `exam_no_step` (`exam_NO`,`STEP`) COMMENT '保证题目与步骤的一致性',
  CONSTRAINT `exam_take_profit_ibfk_1` FOREIGN KEY (`exam_NO`) REFERENCES `exam_content` (`exam_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
