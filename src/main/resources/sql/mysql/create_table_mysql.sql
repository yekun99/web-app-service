/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : itech

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-12-10 15:24:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comm_id_card
-- ----------------------------
DROP TABLE IF EXISTS `comm_id_card`;
CREATE TABLE `comm_id_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_type` varchar(20) DEFAULT NULL,
  `id_card` varchar(18) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `nation` varchar(20) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `effective_date` date DEFAULT NULL,
  `expired_date` date DEFAULT NULL,
  `sign_office` varchar(200) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_id_card` (`id_card`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comm_mail_template
-- ----------------------------
DROP TABLE IF EXISTS `comm_mail_template`;
CREATE TABLE `comm_mail_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `template_title` text,
  `template_desc` text,
  `mail_subject` text,
  `mail_sender` text,
  `mail_recievers` text,
  `mail_cc_recievers` text,
  `mail_body` text,
  `module_code` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comm_number_generate
-- ----------------------------
DROP TABLE IF EXISTS `comm_number_generate`;
CREATE TABLE `comm_number_generate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number_name` varchar(200) DEFAULT NULL,
  `current_value` int(11) DEFAULT NULL,
  `span` int(11) DEFAULT '1',
  `number_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_number_generate` (`number_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comm_reference
-- ----------------------------
DROP TABLE IF EXISTS `comm_reference`;
CREATE TABLE `comm_reference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(90) DEFAULT NULL,
  `code` varchar(200) DEFAULT NULL,
  `language_id` varchar(20) DEFAULT NULL,
  `category_cd` varchar(200) DEFAULT NULL,
  `code_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_reference` (`type`,`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comm_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `comm_sys_role`;
CREATE TABLE `comm_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(150) NOT NULL,
  `role_name` varchar(200) DEFAULT NULL,
  `role_desc` varchar(500) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comm_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `comm_sys_user`;
CREATE TABLE `comm_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `id_type` varchar(20) DEFAULT NULL,
  `id_card` varchar(18) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `verify_ind` varchar(1) NOT NULL DEFAULT 'N',
  `pass_expire_date` datetime DEFAULT NULL,
  `login_wrong_times` int(11) DEFAULT '0',
  `lock_ind` char(1) DEFAULT 'N',
  `password_his` varchar(600) DEFAULT NULL,
  `lock_date` datetime DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_task
-- ----------------------------
DROP TABLE IF EXISTS `pms_task`;
CREATE TABLE `pms_task` (
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
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `indx_task_no` (`task_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_task_his
-- ----------------------------
DROP TABLE IF EXISTS `pms_task_his`;
CREATE TABLE `pms_task_his` (
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
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pms_timesheet
-- ----------------------------
DROP TABLE IF EXISTS `pms_timesheet`;
CREATE TABLE `pms_timesheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `work_day` date DEFAULT NULL,
  `actual_effort` decimal(12,2) DEFAULT NULL,
  `task_no` varchar(50) DEFAULT NULL,
  `task_type` varchar(50) DEFAULT NULL,
  `task_subject` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `task_content` text,
  `system_name` varchar(100) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `insert_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_def
-- ----------------------------
DROP TABLE IF EXISTS `wf_def`;
CREATE TABLE `wf_def` (
  `wf_id` int(11) NOT NULL AUTO_INCREMENT,
  `wf_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `wf_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `wf_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`wf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_flow_step_def
-- ----------------------------
DROP TABLE IF EXISTS `wf_flow_step_def`;
CREATE TABLE `wf_flow_step_def` (
  `flow_step_id` int(11) NOT NULL AUTO_INCREMENT,
  `wf_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `step_code` varchar(255) NOT NULL,
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `assignee` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `assignee_group` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `step_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `step_repeat_no` int(11) DEFAULT NULL,
  `order_no` int(11) NOT NULL,
  `from_step_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `to_step_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `flow_step_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_multi` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`flow_step_id`),
  UNIQUE KEY `indx_flow_step_def` (`wf_code`,`step_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_proc
-- ----------------------------
DROP TABLE IF EXISTS `wf_proc`;
CREATE TABLE `wf_proc` (
  `proc_id` int(11) NOT NULL AUTO_INCREMENT,
  `wf_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `step_code` varchar(255) NOT NULL,
  `business_id` varchar(100) DEFAULT NULL,
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `assignee` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `assignee_group` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `owner_id` varchar(100) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`proc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_proc_his
-- ----------------------------
DROP TABLE IF EXISTS `wf_proc_his`;
CREATE TABLE `wf_proc_his` (
  `proc_his_id` int(11) NOT NULL AUTO_INCREMENT,
  `wf_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `step_code` varchar(255) NOT NULL,
  `business_id` varchar(100) DEFAULT NULL,
  `owner_id` varchar(100) NOT NULL,
  `assignee` varchar(100) NOT NULL,
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `approve_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`proc_his_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_step_def
-- ----------------------------
DROP TABLE IF EXISTS `wf_step_def`;
CREATE TABLE `wf_step_def` (
  `step_id` int(11) NOT NULL AUTO_INCREMENT,
  `step_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `step_name` varchar(255) NOT NULL,
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `step_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`step_id`),
  UNIQUE KEY `indx_step_code` (`step_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
