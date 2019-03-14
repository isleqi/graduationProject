/*
 Navicat Premium Data Transfer

 Source Server         : isleqi
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : graduation_project

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 13/03/2019 20:56:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ans_comment
-- ----------------------------
DROP TABLE IF EXISTS `ans_comment`;
CREATE TABLE `ans_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ans_id` int(11) NULL DEFAULT NULL,
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `like_num` int(11) NULL DEFAULT 0,
  `creat_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `reply_num` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ans_reply
-- ----------------------------
DROP TABLE IF EXISTS `ans_reply`;
CREATE TABLE `ans_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ans_comment_id` int(11) NULL DEFAULT NULL,
  `reply_id` int(11) NULL DEFAULT NULL,
  `reply_user_id` int(11) NULL DEFAULT NULL,
  `replyed_user_id` int(11) NULL DEFAULT NULL,
  `reply_comtent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `like_num` int(11) NULL DEFAULT 0,
  `creat_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `ans_id` int(11) NOT NULL AUTO_INCREMENT,
  `ques_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `ans_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `like_num` int(11) NULL DEFAULT 0,
  `comment_num` int(11) NULL DEFAULT 0,
  `creat_time` datetime(0) NULL DEFAULT NULL,
  `edit_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ans_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NULL DEFAULT NULL,
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `article_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '0代表免费，1代表收费',
  `value` int(255) NULL DEFAULT 0,
  `like_num` int(255) NULL DEFAULT 0,
  `comment_num` int(255) NULL DEFAULT 0,
  `browse` int(255) NULL DEFAULT 0,
  `creat_time` datetime(0) NULL DEFAULT NULL,
  `edit_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL,
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `like_num` int(11) NULL DEFAULT 0,
  `creat_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `reply_num` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_reply
-- ----------------------------
DROP TABLE IF EXISTS `article_reply`;
CREATE TABLE `article_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_comment_id` int(11) NULL DEFAULT NULL,
  `reply_id` int(11) NULL DEFAULT NULL,
  `reply_user_id` int(11) NULL DEFAULT NULL,
  `replyed_user_id` int(11) NULL DEFAULT NULL,
  `reply_comtent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `like_num` int(11) NULL DEFAULT 0,
  `creat_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_id` int(11) NULL DEFAULT NULL,
  `target_type` int(255) NULL DEFAULT NULL COMMENT '1 回答\r\n2 问题\r\n3 文章\r\n4 回答评论\r\n5 文章评论\r\n',
  `send_user_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ques_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题标题',
  `ques_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题描述',
  `ques_user_id` int(11) NULL DEFAULT NULL COMMENT '提问用户id',
  `quesd_user_id` int(11) NULL DEFAULT NULL COMMENT '问题标签集合',
  `answer_num` int(11) NULL DEFAULT 0,
  `follow_num` int(11) NULL DEFAULT 1,
  `creat_time` datetime(0) NULL DEFAULT NULL COMMENT '被提问用户id',
  `edit_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `num` int(11) NULL DEFAULT 0 COMMENT '当前引用个数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tagmap
-- ----------------------------
DROP TABLE IF EXISTS `tagmap`;
CREATE TABLE `tagmap`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_id` int(11) NULL DEFAULT NULL,
  `ques_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `identity_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）',
  `identifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 标识（手机号 邮箱 用户名或第三方应用的唯一标识）',
  `credential` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码凭证（站内的保存密码，站外的不保存或保存token）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_follow_ans
-- ----------------------------
DROP TABLE IF EXISTS `user_follow_ans`;
CREATE TABLE `user_follow_ans`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `answer_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_follow_article
-- ----------------------------
DROP TABLE IF EXISTS `user_follow_article`;
CREATE TABLE `user_follow_article`  (
  `user_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `value` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`user_id`, `article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_follow_ques
-- ----------------------------
DROP TABLE IF EXISTS `user_follow_ques`;
CREATE TABLE `user_follow_ques`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NULL DEFAULT NULL,
  `ques_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户简介',
  `sex` int(255) NULL DEFAULT NULL COMMENT '1 男 0 女',
  `user_icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_like_ans
-- ----------------------------
DROP TABLE IF EXISTS `user_like_ans`;
CREATE TABLE `user_like_ans`  (
  `user_id` int(11) NOT NULL,
  `ans_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `ans_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_notify
-- ----------------------------
DROP TABLE IF EXISTS `user_notify`;
CREATE TABLE `user_notify`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `has_read` int(255) NULL DEFAULT 0,
  `user_id` int(11) NULL DEFAULT NULL,
  `notify_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_pay
-- ----------------------------
DROP TABLE IF EXISTS `user_pay`;
CREATE TABLE `user_pay`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_relations
-- ----------------------------
DROP TABLE IF EXISTS `user_relations`;
CREATE TABLE `user_relations`  (
  `user_id` int(11) NOT NULL,
  `follow_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `follow_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_value
-- ----------------------------
DROP TABLE IF EXISTS `user_value`;
CREATE TABLE `user_value`  (
  `user_id` int(11) NOT NULL,
  `value` int(255) NULL DEFAULT 20,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
