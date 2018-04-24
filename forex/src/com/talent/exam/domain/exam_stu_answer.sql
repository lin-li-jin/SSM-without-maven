/*
Navicat MySQL Data Transfer

Source Server         : forex
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-10-12 09:15:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_stu_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_stu_answer`;
CREATE TABLE `exam_stu_answer` (
  `stu_answer_NO` int(12) NOT NULL COMMENT '答题记录号',
  `stu_id` int(12) NOT NULL COMMENT '学生标志，指向users表的主键',
  `paper_id` int(12) NOT NULL COMMENT '某次考试',
  `paper_exam_status` char(1) NOT NULL COMMENT '考题状态,初始状态为N,即暂未做的状态;当看到题目，则更新状态为A;当点击重做，则更新原来的,状态为R;当完整提交试卷，则更新为D',
  `stu_answer` varchar(25) NOT NULL DEFAULT '' COMMENT '提交的答案,形式如10,23,指向exam_*',
  `stu_answer_table` varchar(15) NOT NULL DEFAULT '' COMMENT '对应的表',
  `paper_grade` int(3) NOT NULL DEFAULT '0' COMMENT '答题分数',
  PRIMARY KEY (`stu_answer_NO`),
  KEY `exam_stu_answer_stu_id` (`stu_id`),
  KEY `exam_stu_answer_paper_id` (`paper_id`),
  CONSTRAINT `exam_stu_answer_paper_id` FOREIGN KEY (`paper_id`) REFERENCES `exam_paper_distribute` (`paper_id`),
  CONSTRAINT `exam_stu_answer_stu_id` FOREIGN KEY (`stu_id`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生答题记录';
