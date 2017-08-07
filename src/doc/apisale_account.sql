/*
Navicat MySQL Data Transfer

Source Server         : mydatabase
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : apisale_account

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-07 17:50:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `acountcoin`
-- ----------------------------
DROP TABLE IF EXISTS `acountcoin`;
CREATE TABLE `acountcoin` (
  `userid` bigint(64) NOT NULL,
  `acoin` int(32) NOT NULL COMMENT 'A币 --- 与人民币兑换率为 1:10',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of acountcoin
-- ----------------------------
INSERT INTO `acountcoin` VALUES ('893072515272937472', '1000');
INSERT INTO `acountcoin` VALUES ('893497472280825856', '0');

-- ----------------------------
-- Table structure for `acountflow`
-- ----------------------------
DROP TABLE IF EXISTS `acountflow`;
CREATE TABLE `acountflow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `date` datetime NOT NULL,
  `userid` bigint(64) NOT NULL COMMENT '用户编号',
  `type` tinyint(10) NOT NULL DEFAULT '0' COMMENT '操作类型',
  `cv` int(30) NOT NULL COMMENT '改变值',
  `pcoin` int(30) NOT NULL COMMENT '原始账户值',
  `ncoin` int(30) NOT NULL COMMENT '当前账户值',
  `orderid` bigint(64) DEFAULT NULL COMMENT '冗余字段，订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of acountflow
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `oid` bigint(64) NOT NULL COMMENT '订单号',
  `date` datetime NOT NULL COMMENT '订单日期',
  `ostatus` tinyint(6) NOT NULL COMMENT '订单状态',
  `sid` int(11) NOT NULL COMMENT '服务id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `price` bigint(64) NOT NULL COMMENT '价格',
  `back` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `userbasis`
-- ----------------------------
DROP TABLE IF EXISTS `userbasis`;
CREATE TABLE `userbasis` (
  `id` bigint(64) NOT NULL COMMENT '帐号id',
  `name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `phone` char(11) NOT NULL,
  `status` int(4) NOT NULL,
  `createtime` datetime NOT NULL,
  `updatetime` datetime NOT NULL,
  `permission` int(4) NOT NULL DEFAULT '0',
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userbasis
-- ----------------------------
INSERT INTO `userbasis` VALUES ('893072515272937472', 'dforel', '7c4879d19074f576f0ba124f81f59a73', '13671177326', '1', '2017-08-03 19:34:14', '2017-08-03 19:34:14', '0', null);
INSERT INTO `userbasis` VALUES ('893497472280825856', 'dforel2', 'e10adc3949ba59abbe56e057f20f883e', '13671177328', '1', '2017-08-04 23:42:42', '2017-08-04 23:42:42', '0', null);
