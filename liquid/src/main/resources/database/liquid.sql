/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : lemon

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2017-10-11 10:23:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `lemon_liquid_auth_operation`
-- ----------------------------
DROP TABLE IF EXISTS `lemon_liquid_auth_operation`;
CREATE TABLE `lemon_liquid_auth_operation` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT '操作ID',
  `guid` varchar(255) collate utf8_bin NOT NULL,
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  `operationName` varchar(255) default NULL COMMENT '操作名',
  `displayName` varchar(255) default NULL COMMENT '操作中文名',
  `operationURL` varchar(255) default NULL COMMENT '操作链接',
  `resourceId` int(11) not NULL COMMENT '资源ID',
  `operationRId` int(11) not null default -1 COMMENT '操作依赖ID',
  `permission`  varchar(255) COMMENT '许可',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `lemon_liquid_auth_resource`
-- ----------------------------
DROP TABLE IF EXISTS `lemon_liquid_auth_resource`;
CREATE TABLE `lemon_liquid_auth_resource` (
  `id` int(11) unsigned NOT NULL auto_increment COMMENT '资源ID',
  `guid` varchar(255) collate utf8_bin NOT NULL,
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  `resourceName` varchar(255) default NULL COMMENT '资源名称',
  `resourceType` varchar(255) default NULL COMMENT '资源类型 1,菜单   2,文件',
  `resourceURL` varchar(255) default NULL COMMENT '资源链接',
  `resourcePId` int(11) NOT NULL default -1 COMMENT '资源父ID',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `lemon_liquid_auth_role`
-- ----------------------------
DROP TABLE IF EXISTS `lemon_liquid_auth_role`;
CREATE TABLE `lemon_liquid_auth_role` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT '角色ID',
  `guid` varchar(255) collate utf8_bin NOT NULL,
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  `roleName` varchar(255) default NULL COMMENT '角色名称',
  `displayName` varchar(255) default NULL COMMENT '角色中文名称',
  `roleExtendPId` int(11) not NULL default -1 COMMENT '角色父ID',
  `roleRelyId` int(11) not NULL default -1 COMMENT '角色依赖ID',
  `roleMaxNum` int(11) default NULL COMMENT '最大限制用户数',
  `remarks` varchar(255) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `lemon_liquid_auth_role_operation`
-- ----------------------------
DROP TABLE IF EXISTS `lemon_liquid_auth_role_operation`;
CREATE TABLE `lemon_liquid_auth_role_operation` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT '角色和操作关联表ID',
  `roleId` int(11) default NULL COMMENT '角色ID',
  `operationId` int(11) default NULL COMMENT '操作ID',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lemon_liquid_auth_role_operation
-- ----------------------------

-- ----------------------------
-- Table structure for `lemon_liquid_auth_user`
-- ----------------------------
DROP TABLE IF EXISTS `lemon_liquid_auth_user`;
CREATE TABLE `lemon_liquid_auth_user` (
  `id` int(11) unsigned NOT NULL auto_increment COMMENT '用户ID',
  `guid` varchar(255) collate utf8_bin NOT NULL,
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  `userName` varchar(255) default NULL COMMENT '用户名称',
  `userPass` varchar(255) default NULL COMMENT '用户密码',
  `phone` varchar(255) default NULL COMMENT '用户手机号',
  `email` varchar(255) default NULL COMMENT '用户邮箱',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lemon_liquid_auth_user
-- ----------------------------
INSERT INTO `lemon_liquid_auth_user` VALUES ('1', 'admin', 'admin', null, null, null,null,null);

-- ----------------------------
-- Table structure for `lemon_liquid_auth_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `lemon_liquid_auth_user_role`;
CREATE TABLE `lemon_liquid_auth_user_role` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT '用户和角色操作表ID',
  `userId` tinyint(4) default NULL COMMENT '用户ID',
  `roleId` tinyint(4) default NULL COMMENT '角色ID',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lemon_liquid_auth_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `lemon_liquid_log_systemlog`
-- ----------------------------
DROP TABLE IF EXISTS `lemon_liquid_log_systemlog`;
CREATE TABLE `lemon_liquid_log_systemlog` (
  `id` int(11) unsigned NOT NULL auto_increment COMMENT '日志ID',
  `guid` varchar(255) collate utf8_bin NOT NULL,
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  `logType` varchar(255) collate utf8_bin default NULL COMMENT '日志类型  1,info  2,error',
  `logTitle` varchar(255) collate utf8_bin default NULL COMMENT '日志标题',
  `remoteAddr` varchar(255) collate utf8_bin default NULL COMMENT '请求地址',
  `requestUri` varchar(255) collate utf8_bin default NULL COMMENT '请求URI',
  `method` varchar(255) collate utf8_bin default NULL COMMENT '请求方式',
  `params` varchar(255) collate utf8_bin default NULL COMMENT '请求参数',
  `exception` text collate utf8_bin default NULL COMMENT '异常',
  `authStatus` int(1) default NULL COMMENT '权限状态 1,代表不需权限  2,无权限  3,享有权限',
  `operateDate` date default NULL COMMENT '开始时间',
  `timeout` varchar(255) collate utf8_bin default NULL COMMENT '耗时',
  `userName` varchar(255) collate utf8_bin default NULL COMMENT '用户名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=557 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `lemon_liquid_database_backupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `lemon_liquid_database_backupinfo`;
CREATE TABLE `lemon_liquid_database_backupinfo` (
  `id` int(11) unsigned NOT NULL auto_increment COMMENT '备份数据库id',
  `guid` varchar(255) collate utf8_bin NOT NULL,
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  `infoName` varchar(255) collate utf8_bin default NULL COMMENT '备份名称',
  `path` varchar(255) collate utf8_bin default NULL COMMENT '备份路径',
  `remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `fileSize` varchar(255) collate utf8_bin default NULL COMMENT '文件大小',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of lemon_liquid_database_backupinfo
-- ----------------------------

