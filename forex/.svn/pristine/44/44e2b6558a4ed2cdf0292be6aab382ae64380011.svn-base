/*
Navicat MySQL Data Transfer

Source Server         : forex
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-10-12 09:15:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper` (
  `paper_NO` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷编号',
  `t_id` int(12) NOT NULL COMMENT '教师编号',
  `paper_title` blob NOT NULL COMMENT '试卷题目编号',
  `paper_exam_content` varchar(250) DEFAULT NULL COMMENT '暂不考虑过多的题目,形式如10,13,20,30',
  PRIMARY KEY (`paper_NO`),
  KEY `exam_paper_t_id` (`t_id`),
  CONSTRAINT `exam_paper_t_id` FOREIGN KEY (`t_id`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='试卷表';
