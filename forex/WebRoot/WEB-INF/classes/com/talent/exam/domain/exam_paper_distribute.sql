/*
Navicat MySQL Data Transfer

Source Server         : forex
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-10-12 09:15:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_paper_distribute
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_distribute`;
CREATE TABLE `exam_paper_distribute` (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标记某次考试',
  `paper_NO` int(11) NOT NULL COMMENT '分配试卷编号',
  `class_id` int(12) NOT NULL COMMENT '班级编号',
  `class_paper_status` char(1) NOT NULL COMMENT '分配后的试卷状态,U:刚分配试卷处于可用状态;A：分发后试卷处于激活状态;D:收卷时处于完成状态;C:主动取消收卷',
  `create_date` varchar(8) NOT NULL COMMENT '分配试卷的开始时间',
  `till_date` varchar(8) NOT NULL COMMENT '分配试卷的结束时间',
  PRIMARY KEY (`paper_id`),
  UNIQUE KEY `paper_NO` (`paper_NO`,`class_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `exam_paper_distribute_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `t_class` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷分配表';
