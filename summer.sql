/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : informaction_2014_12_25

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-06-15 14:44:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `op_module`
-- ----------------------------
DROP TABLE IF EXISTS `op_module`;
CREATE TABLE `op_module` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `isleaf` int(11) DEFAULT NULL,
  `icon_cls` varbinary(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creater_id` varchar(50) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modifier_id` varchar(50) DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of op_module
-- ----------------------------
INSERT INTO `op_module` VALUES ('1', '系统设置', '0', '', '8', '0', 0x69636F6E2D6D696E692D73797374656D, '', '1', '2014-04-12', '0', '2014-04-12');
INSERT INTO `op_module` VALUES ('2', '模块管理', '1', 'module/module.html', '3', '1', null, '', '1', '2014-04-12', '1', '2014-04-12');
INSERT INTO `op_module` VALUES ('20', '用户信息', '1', 'yhxx/yhxx.html', null, '1', '', '222222e333', 'user311422850666439', '2016-04-27', 'user311422850666439', '2016-04-27');


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_t`
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_t
-- ----------------------------
INSERT INTO `user_t` VALUES ('1', '测试', 'sfasgfaf', '24');
