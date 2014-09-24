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

 Date: 09/25/2014 00:43:32 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `TBL_BATTLE_HISTORY`
-- ----------------------------
DROP TABLE IF EXISTS `TBL_BATTLE_HISTORY`;
CREATE TABLE `TBL_BATTLE_HISTORY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `TARGET_USER_ID` varchar(255) NOT NULL COMMENT '对手ID',
  `RESULT` varchar(255) NOT NULL COMMENT '对战结果',
  `SCORE` int(10) NOT NULL DEFAULT '0' COMMENT '得分',
  `CREATE_DATE` varchar(255) NOT NULL COMMENT '创建日期',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `ARENA_ID` bigint(20) NOT NULL COMMENT '竞技场ID',
  PRIMARY KEY (`ID`),
  KEY `IDX_USER_ID` (`USER_ID`),
  KEY `IDX_TARGET_USER_ID` (`TARGET_USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `TBL_BATTLE_HISTORY`
-- ----------------------------
BEGIN;
INSERT INTO `TBL_BATTLE_HISTORY` VALUES ('2', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192q', 'WIN', '98', '2014-09-21', '2014-09-21 14:47:56', '2014-09-22 23:04:42', '1'), ('3', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192q', 'LOSE', '50', '2014-09-21', '2014-09-21 14:48:29', '2014-09-22 23:04:43', '1'), ('4', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192o', 'WIN', '65', '2014-09-22', '2014-09-22 22:36:14', '2014-09-23 10:43:45', '1'), ('5', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192q', 'WIN', '76', '2014-09-22', '2014-09-22 22:36:19', '2014-09-22 23:04:46', '1'), ('6', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192q', 'LOSE', '43', '2014-09-22', '2014-09-22 22:36:16', '2014-09-22 23:04:47', '1'), ('7', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192q', 'LOSE', '99', '2014-09-22', '2014-09-22 22:36:22', '2014-09-22 23:04:48', '1'), ('8', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192q', 'LOSE', '102', '2014-09-22', '2014-09-22 22:37:26', '2014-09-22 23:04:50', '1'), ('9', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192x', 'WIN', '77', '2014-09-24', '2014-09-24 23:08:37', '2014-09-24 23:08:37', '1'), ('10', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192x', 'WIN', '77', '2014-09-24', '2014-09-24 23:09:39', '2014-09-24 23:09:39', '1'), ('11', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192x', 'WIN', '320', '2014-09-24', '2014-09-24 23:10:44', '2014-09-24 23:10:44', '1'), ('12', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192x', 'WIN', '320', '2014-09-24', '2014-09-24 23:11:37', '2014-09-24 23:11:37', '1'), ('13', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192x', 'WIN', '320', '2014-09-24', '2014-09-24 23:19:06', '2014-09-24 23:19:06', '1'), ('14', 'bc928dfd2aa911f65580b6a3e2f5192e', 'bc928dfd2aa911f65580b6a3e2f5192x', 'WIN', '320', '2014-09-24', '2014-09-24 23:20:56', '2014-09-24 23:20:56', '1');
COMMIT;

