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

 Date: 09/25/2014 00:42:34 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `TBL_USER`
-- ----------------------------
DROP TABLE IF EXISTS `TBL_USER`;
CREATE TABLE `TBL_USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MOBILE_PHONE` varchar(20) NOT NULL COMMENT '电话号码',
  `NICK_NAME` varchar(100) NOT NULL COMMENT '昵称',
  `ROLE_NAME` varchar(255) NOT NULL COMMENT '角色名称',
  `PET_NAME` varchar(255) NOT NULL COMMENT '宠物名称',
  `MOUNTS_NAME` varchar(255) NOT NULL COMMENT '坐骑名称',
  `SCORE` int(10) NOT NULL DEFAULT '0' COMMENT '积分',
  `MAX_BATTLE_SCORE` int(10) NOT NULL DEFAULT '0' COMMENT '最高对战得分',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `ROLE_RANK` varchar(255) NOT NULL COMMENT '角色等级',
  `MOUNTS_RANK` varchar(255) NOT NULL COMMENT '坐骑等级',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `JOIN_ARENA_COUNT` int(11) NOT NULL DEFAULT '0',
  `IS_CONTINUE_WIN` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDX_USER_ID` (`USER_ID`),
  KEY `IDX_SCORE` (`SCORE`),
  KEY `IDX_MAX_BATTLE_SCORE` (`MAX_BATTLE_SCORE`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `TBL_USER`
-- ----------------------------
BEGIN;
INSERT INTO `TBL_USER` VALUES ('2', '13811312012', 'michael', 'magic', 'peter', 'bird', '701', '320', '2014-09-21 13:10:45', '2014-09-24 23:20:56', '2', '6', 'bc928dfd2aa911f65580b6a3e2f5192e', '3', '0'), ('3', '13811312031', 'a1', 'warrior', 'hh', 'vv', '56', '76', '2014-09-21 22:05:13', '2014-09-21 22:06:35', '3', '1', 'bc928dfd2aa911f65580b6a3e2f5192q', '1', '0'), ('4', '13811312013', 'a2', 'warrior', 'gg', 'gg', '87', '101', '2014-09-21 22:05:16', '2014-09-21 22:16:25', '1', '2', 'bc928dfd2aa911f65580b6a3e2f5192o', '1', '1'), ('5', '13811312055', 'a3', 'warrior', 'zz', 'hh', '100', '98', '2014-09-21 22:05:19', '2014-09-21 22:16:33', '3', '3', 'bc928dfd2aa911f65580b6a3e2f5192p', '1', '0'), ('6', '13811312066', 'a4', 'warrior', 'qq', 'kk', '20', '105', '2014-09-21 22:05:22', '2014-09-21 22:16:20', '1', '5', 'bc928dfd2aa911f65580b6a3e2f5192m', '1', '1'), ('7', '12312312312', 'a5', 'warrior', 'zzx', 'eqe', '200', '120', '2014-09-23 23:32:50', '2014-09-23 23:33:11', '1', '12', 'bc928dfd2aa911f65580b6a3e2f5192l', '0', '0'), ('8', '23123123111', 'a6', 'warrior', 'eeeq', 'eqeqw', '98', '97', '2014-09-23 23:32:54', '2014-09-23 23:33:14', '1', '2', 'bc928dfd2aa911f65580b6a3e2f5192x', '0', '0'), ('9', '13811312013', 'michael1', 'magic1', 'peter1', 'bird1', '0', '0', '2014-09-25 00:36:41', '2014-09-25 00:36:41', '3', '8', 'd2920c2eae982afd5eb40f29316e9b08', '0', '0');
COMMIT;

