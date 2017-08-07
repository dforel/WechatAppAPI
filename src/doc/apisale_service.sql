/*
Navicat MySQL Data Transfer

Source Server         : mydatabase
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : apisale_service

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-07 17:51:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `instance`
-- ----------------------------
DROP TABLE IF EXISTS `instance`;
CREATE TABLE `instance` (
  `iid` int(11) NOT NULL COMMENT '实例id',
  `sid` bigint(64) NOT NULL COMMENT '对应的服务id',
  `times` bigint(64) NOT NULL COMMENT '实例剩余次数',
  `count` bigint(64) NOT NULL DEFAULT '0' COMMENT '已经使用次数',
  `itoken` varchar(32) NOT NULL,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of instance
-- ----------------------------
INSERT INTO `instance` VALUES ('1', '1', '100000', '9961', '123456789');

-- ----------------------------
-- Table structure for `instancebase`
-- ----------------------------
DROP TABLE IF EXISTS `instancebase`;
CREATE TABLE `instancebase` (
  `iid` int(11) NOT NULL COMMENT '订单id',
  `userid` bigint(64) NOT NULL COMMENT '用户id',
  `renew` tinyint(2) DEFAULT '1' COMMENT '是否续订，1是，2否',
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of instancebase
-- ----------------------------

-- ----------------------------
-- Table structure for `services`
-- ----------------------------
DROP TABLE IF EXISTS `services`;
CREATE TABLE `services` (
  `sid` int(11) NOT NULL,
  `sname` varchar(30) DEFAULT NULL COMMENT '服务名称',
  `status` tinyint(5) DEFAULT NULL COMMENT '状态',
  `ptype` tinyint(5) DEFAULT NULL COMMENT '计费类型',
  `coincost` bigint(64) DEFAULT NULL COMMENT '站内币花费',
  `times` bigint(64) DEFAULT NULL COMMENT '使用次数',
  PRIMARY KEY (`sid`),
  UNIQUE KEY `sy1` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of services
-- ----------------------------
INSERT INTO `services` VALUES ('1', 'expresscheck', '1', '2', '450', '10000');
