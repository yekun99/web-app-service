/*
Navicat MySQL Data Transfer

Source Server         : ydc
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-06-18 10:42:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comm_task
-- ----------------------------
DROP TABLE IF EXISTS `comm_task`;
CREATE TABLE `comm_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_no` varchar(50) NOT NULL,
  `task_type` varchar(50) DEFAULT NULL,
  `task_subject` varchar(200) DEFAULT NULL,
  `task_content` text,
  `system_name` varchar(100) DEFAULT NULL,
  `estimated_effort` decimal(12,2) DEFAULT NULL,
  `actual_effort` decimal(12,2) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT NULL,
  `assignee` varchar(150) DEFAULT NULL,
  `severity` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `attached_id` varchar(20) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `submission_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_task_no` (`task_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comm_task_his
-- ----------------------------
DROP TABLE IF EXISTS `comm_task_his`;
CREATE TABLE `comm_task_his` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_no` varchar(50) NOT NULL,
  `task_type` varchar(50) DEFAULT NULL,
  `task_subject` varchar(200) DEFAULT NULL,
  `task_content` text,
  `system_name` varchar(100) DEFAULT NULL,
  `estimated_effort` decimal(12,2) DEFAULT NULL,
  `actual_effort` decimal(12,2) DEFAULT NULL,
  `created_by` varchar(150) DEFAULT NULL,
  `assignee` varchar(150) DEFAULT NULL,
  `severity` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `attached_id` varchar(20) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `submission_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

