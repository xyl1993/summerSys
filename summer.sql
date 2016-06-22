/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : summer

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-06-22 17:02:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `op_module`
-- ----------------------------
DROP TABLE IF EXISTS `op_module`;
CREATE TABLE `op_module` (
  `id` varchar(30) NOT NULL,
  `text` varchar(50) DEFAULT NULL,
  `parent_id` varchar(30) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `isleaf` int(11) DEFAULT NULL,
  `icon_cls` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL,
  `creater_time` date DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modifier_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of op_module
-- ----------------------------
INSERT INTO `op_module` VALUES ('1', '系统设置', '0', '', '8', '0', 'icon-mini-system', '', '1', '2014-04-12', '0', '2014-04-12');
INSERT INTO `op_module` VALUES ('2', '模块管理', '1', '../module/module.html', '9', '1', null, '5', null, null, null, null);
INSERT INTO `op_module` VALUES ('op_md044059716088190', '工作信息', '1', '../experience/experienceList.html', '2', '1', null, '', null, null, null, null);
INSERT INTO `op_module` VALUES ('op_md227249162583097', 'about-me', '1', '../me/me.html', '1', '1', null, '', null, null, null, null);
INSERT INTO `op_module` VALUES ('op_md695101154448642', '个人技能', '1', '../skills/skills.html', '0', '1', null, '', null, null, null, null);

-- ----------------------------
-- Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` varchar(22) NOT NULL,
  `start_time` varchar(10) DEFAULT NULL,
  `end_time` varchar(10) DEFAULT NULL,
  `name` varchar(22) DEFAULT NULL,
  `nature` varchar(22) DEFAULT NULL COMMENT '性质',
  `size` varchar(22) DEFAULT NULL COMMENT '规模',
  `industry` varchar(22) DEFAULT NULL COMMENT '行业',
  `address` varchar(22) DEFAULT NULL COMMENT '地址',
  `duty` varchar(100) DEFAULT NULL COMMENT '职责',
  `remarks` varchar(100) DEFAULT NULL,
  `creater` varchar(22) DEFAULT NULL,
  `creater_time` datetime DEFAULT NULL,
  `modifier` varchar(22) DEFAULT NULL,
  `modifier_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('op_exp001328896170992', '', '', '1', '1', '', '', '1', '', '', 'op_user395778148538308', '2016-06-22 16:01:21', 'op_user395778148538308', '2016-06-22 16:01:21');
INSERT INTO `t_company` VALUES ('op_exp457927707082562', '', '123123123', '1213213', '1213213', '31321231', '123123', '1213213', '123123123', 'op_user395778148538308', '12312312312312', '2016-06-21 15:03:31', '', '2016-06-21 15:03:31');
INSERT INTO `t_company` VALUES ('op_exp788349709117301', '', '', '2222', '2222', '', '', '2222', '', '', 'op_user395778148538308', '2016-06-22 16:01:42', 'op_user395778148538308', '2016-06-22 16:01:42');
INSERT INTO `t_company` VALUES ('op_exp798441757369709', '', '111', '11111', '11111', '1111', '111', '11111', '11', 'op_user395778148538308', '111', '2016-06-21 15:00:27', '', '2016-06-21 15:00:27');
INSERT INTO `t_company` VALUES ('op_exp983999714476588', '', '', '3333', '3333', '', '', '3333', '', '', 'op_user395778148538308', '2016-06-22 16:01:29', 'op_user395778148538308', '2016-06-22 16:01:29');

-- ----------------------------
-- Table structure for `t_me`
-- ----------------------------
DROP TABLE IF EXISTS `t_me`;
CREATE TABLE `t_me` (
  `id` varchar(30) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `token` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `blog` varchar(50) DEFAULT NULL,
  `pic_url` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `creater` varchar(30) DEFAULT NULL,
  `creater_time` datetime DEFAULT NULL,
  `modifier` varchar(30) DEFAULT NULL,
  `modifier_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  `job` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_me
-- ----------------------------
INSERT INTO `t_me` VALUES ('op_user218518509059386', '1111', '96e79218965eb72c92a549dd5a330112', '11111', null, null, null, null, null, null, '', null, 'admin', '2016-06-20 10:10:11', null, null, null, null, null);
INSERT INTO `t_me` VALUES ('op_user274085938328193', 'qwe', '96e79218965eb72c92a549dd5a330112', '121212', null, null, null, null, null, null, '', null, 'admin', '2016-06-20 10:04:45', null, null, null, null, null);
INSERT INTO `t_me` VALUES ('op_user395778148538308', '18306271627', '96e79218965eb72c92a549dd5a330112', '夏映柳', 'oW+UufdXiHWZK9lUpvHda3y10iI=', '1993-06-07', '南通，如皋', '18306271627', '602165057@qq.com', '11111111111111111111111111', '/summerSys/upload/images/HPd0JD9ntme+G+HuWVKIYF7mEsQ=.jpg', '2222', 'admin', '2016-06-21 09:00:00', null, null, null, '1', '软件工程师');
INSERT INTO `t_me` VALUES ('op_user405693695056090', '1112', '96e79218965eb72c92a549dd5a330112', '111111', null, null, null, null, null, null, '', null, 'admin', '2016-06-20 10:38:04', null, null, null, null, null);
INSERT INTO `t_me` VALUES ('op_user464669551362248', null, null, 'xyl', 'FVuG2KJvwj6+PNaeAyFWQmr9WKA=', '2016-06-11', '123', '18306271627', '123124@qq.com', '1231', '/summerSys/upload/images/1iEwLcFucvNpP+j12tL6rsqiDLA=.jpg', '23123132', null, null, null, null, null, '0', null);

-- ----------------------------
-- Table structure for `t_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` varchar(22) NOT NULL DEFAULT '',
  `prj_name` varchar(22) DEFAULT NULL COMMENT '项目名称',
  `start_time` varchar(10) DEFAULT NULL COMMENT '项目开始日期',
  `end_time` varchar(10) DEFAULT NULL,
  `prj_describe` varchar(100) DEFAULT NULL COMMENT '项目描述',
  `duty` varchar(100) DEFAULT NULL COMMENT '职责',
  `company_id` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES ('1', '', '', '', '', '', '');
INSERT INTO `t_project` VALUES ('op_prj728516860274776', '12121', '2016年06月', '2016年06月', '121', '21212', 'op_exp788349709117301');

-- ----------------------------
-- Table structure for `t_skills`
-- ----------------------------
DROP TABLE IF EXISTS `t_skills`;
CREATE TABLE `t_skills` (
  `id` varchar(22) NOT NULL DEFAULT '',
  `text` varchar(255) DEFAULT NULL,
  `creater` varchar(22) DEFAULT NULL,
  `creater_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_skills
-- ----------------------------
INSERT INTO `t_skills` VALUES ('op_skil406161013119089', '333', 'op_user395778148538308', '2016-06-22 15:19:33');

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
