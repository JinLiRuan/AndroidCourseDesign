/*
 Navicat Premium Data Transfer

 Source Server         : Mysql3308
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3308
 Source Schema         : android

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 02/06/2021 21:30:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acd_course
-- ----------------------------
DROP TABLE IF EXISTS `acd_course`;
CREATE TABLE `acd_course`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `course1` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `course2` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `course3` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acd_course
-- ----------------------------
INSERT INTO `acd_course` VALUES (1, 'Spring架构与设计', 'Android开发', 'Hibernate实战', '软件工程');
INSERT INTO `acd_course` VALUES (2, 'Hadoop大数据处理', 'Android开发', 'Spark技术', '大数据');
INSERT INTO `acd_course` VALUES (3, 'JavaWeb开发', 'Android开发', '单片机入门', '电子与计算机工程');

-- ----------------------------
-- Table structure for acd_score
-- ----------------------------
DROP TABLE IF EXISTS `acd_score`;
CREATE TABLE `acd_score`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `major` varchar(10) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `score1` varchar(10) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `score2` varchar(10) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `score3` varchar(10) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `sno` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acd_score
-- ----------------------------
INSERT INTO `acd_score` VALUES (1, '大数据', 'test', '54', '67', '98', 'test');
INSERT INTO `acd_score` VALUES (2, '大数据', 'test', '54', '67', '98', 'test');
INSERT INTO `acd_score` VALUES (3, '软件工程', '张三', '88', '56', '56', '1940131256');
INSERT INTO `acd_score` VALUES (4, '大数据', '阮锦利', '96', '86', '94', '1940131249');
INSERT INTO `acd_score` VALUES (5, '电子计算机工程', '李四', '56', '32', '75', '19401235');
INSERT INTO `acd_score` VALUES (6, '大数据', '陈卓航', '56', '88', '36', '1940131247');
INSERT INTO `acd_score` VALUES (7, '电子计算机工程', '毛海威', '50', '60', '40', '1940131250');
INSERT INTO `acd_score` VALUES (8, '大数据', 'Joy', '45', '45', '45', '1940131251');

-- ----------------------------
-- Table structure for acd_student
-- ----------------------------
DROP TABLE IF EXISTS `acd_student`;
CREATE TABLE `acd_student`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `major` varchar(12) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `name` varchar(12) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `sno` varchar(12) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `teacher` varchar(12) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acd_student
-- ----------------------------
INSERT INTO `acd_student` VALUES (1, '大数据', '阮锦利', '1940131249', '黄金飞');
INSERT INTO `acd_student` VALUES (2, '大数据', '陈卓航', '1940131247', '黄金飞');
INSERT INTO `acd_student` VALUES (3, '软件工程', '张三', '1940131250', '王红勤');
INSERT INTO `acd_student` VALUES (4, '电子与计算机工程', '李四', '1940131246', '王红勤');
INSERT INTO `acd_student` VALUES (5, '大数据', 'joy', '1940131251', '黄金飞');
INSERT INTO `acd_student` VALUES (6, '大数据', 'Mark', '1940131252', '黄金飞');

-- ----------------------------
-- Table structure for acd_user
-- ----------------------------
DROP TABLE IF EXISTS `acd_user`;
CREATE TABLE `acd_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(12) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `username` varchar(12) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acd_user
-- ----------------------------
INSERT INTO `acd_user` VALUES (1, 'admin', 'admin');
INSERT INTO `acd_user` VALUES (2, 'test', 'test');
INSERT INTO `acd_user` VALUES (3, '123456', '1940131249');
INSERT INTO `acd_user` VALUES (4, 'test', '1940131266');
INSERT INTO `acd_user` VALUES (5, '123456', '1940131247');
INSERT INTO `acd_user` VALUES (6, 'test', '1940131250');
INSERT INTO `acd_user` VALUES (7, 'test', '1940131251');
INSERT INTO `acd_user` VALUES (8, 'test', '1940131252');
INSERT INTO `acd_user` VALUES (9, 'test', '1940131252');
INSERT INTO `acd_user` VALUES (10, 'test', '1940131252');

SET FOREIGN_KEY_CHECKS = 1;
