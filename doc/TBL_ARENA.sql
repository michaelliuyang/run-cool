/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql5.5
 Source Server Type    : MySQL
 Source Server Version : 50524
 Source Host           : 127.0.0.1
 Source Database       : runcool

 Target Server Type    : MySQL
 Target Server Version : 50524
 File Encoding         : utf-8

 Date: 09/25/2014 00:43:39 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `TBL_ARENA`
-- ----------------------------
DROP TABLE IF EXISTS `TBL_ARENA`;
CREATE TABLE `TBL_ARENA` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ARENA_TYPE` varchar(255) NOT NULL COMMENT '竞技场类型,GOLDEN,DIAMOND',
  `ARENA_LEVEL` varchar(255) NOT NULL COMMENT '竞技场等级,ONE,TWO,THREE',
  `CONSUME_MONEY` int(11) NOT NULL DEFAULT '0' COMMENT '消耗的金钱',
  `REWARD_MONEY` int(11) NOT NULL DEFAULT '0' COMMENT '奖励的金钱',
  `REWARD_SCORE` int(11) NOT NULL DEFAULT '0' COMMENT '奖励的积分',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `CONTINUE_WIN_ADD_PERCENT` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '连胜加成百分比',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `TBL_ARENA`
-- ----------------------------
BEGIN;
INSERT INTO `TBL_ARENA` VALUES ('1', 'GOLDEN', 'THREE', '10', '50', '100', '2014-09-21 13:49:29', '2014-09-23 00:24:26', '0.10');
COMMIT;

