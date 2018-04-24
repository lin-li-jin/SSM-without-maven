/*
Navicat MySQL Data Transfer

Source Server         : forex
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-10-12 09:27:50
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
