/*
Navicat MySQL Data Transfer

Source Server         : forex
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-09-13 17:19:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_one_click
-- ----------------------------
DROP TABLE IF EXISTS `exam_one_click`;
CREATE TABLE `exam_one_click` (
  `exam_one_click_no` int(12) NOT NULL AUTO_INCREMENT COMMENT 'one_click类型题目编号',
  `acc_type_no` char(1) NOT NULL COMMENT '冗余字段，方便查询',
  `exam_NO` int(11) NOT NULL COMMENT '考试题目编号',
  `DIRECTION` char(1) NOT NULL COMMENT '买卖方向',
  `ACC` char(3) NOT NULL COMMENT '买卖币种',
  `ACC_AMOUNT` varchar(30) NOT NULL COMMENT '买卖数量',
  `STEP` int(5) NOT NULL COMMENT '步骤,最多5步',
  `exam_score` varchar(100) NOT NULL COMMENT '存放老师或者学生的分数值，格式形如20,0,50,0,0,30,0,100',
  `user_type` char(1) NOT NULL COMMENT '使用者类型,S或者T',
  PRIMARY KEY (`exam_one_click_no`),
  UNIQUE KEY `exam_NO_STEP` (`exam_NO`,`STEP`) COMMENT '保证题目与步骤的唯一性',
  CONSTRAINT `exam_one_click_ibfk_1` FOREIGN KEY (`exam_NO`) REFERENCES `exam_content` (`exam_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='one click题目要素表';
