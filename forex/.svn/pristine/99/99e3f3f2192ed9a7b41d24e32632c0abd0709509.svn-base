/*
Navicat MySQL Data Transfer

Source Server         : forex
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-09-07 09:54:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_acc_type
-- ----------------------------
DROP TABLE IF EXISTS `exam_acc_type`;
CREATE TABLE `exam_acc_type` (
  `acc_type_NO` char(1) NOT NULL COMMENT '交易类型编号',
  `acc_type` varchar(15) NOT NULL COMMENT '交易类型名称',
  PRIMARY KEY (`acc_type_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易类型表';
DROP TABLE IF EXISTS `exam_content`;
CREATE TABLE `exam_content` (
  `exam_NO` int(5) NOT NULL AUTO_INCREMENT COMMENT '考试题目表',
  `exam_content` blob NOT NULL COMMENT '考试题目不能超过250个字符',
  `acc_type_NO` char(1) NOT NULL COMMENT '考试类型',
  PRIMARY KEY (`exam_NO`),
  KEY `exam_content_type` (`acc_type_NO`),
  CONSTRAINT `exam_content_type` FOREIGN KEY (`acc_type_NO`) REFERENCES `exam_acc_type` (`acc_type_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='考试题目表';
INSERT INTO `exam_acc_type` VALUES ('B', '保证金交易');
INSERT INTO `exam_acc_type` VALUES ('C', '人民币交易');
INSERT INTO `exam_acc_type` VALUES ('W', '外汇交易');