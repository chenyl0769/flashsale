/*
 Navicat Premium Data Transfer

 Source Server         : mm
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : flashsale

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 07/05/2020 18:19:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for discount
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dis_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `discount` double(255, 0) NULL DEFAULT NULL,
  `starttime` datetime(0) NULL DEFAULT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discount
-- ----------------------------
INSERT INTO `discount` VALUES (26, 'eee79729-eea7-4766-bfd0-bcf316939852', '154356', 4, 111, '2020-04-17 16:00:00', '2020-04-19 16:00:00');
INSERT INTO `discount` VALUES (27, '7c5643fc-32f5-4003-a717-b8bed5e0fa24', '122456', 3, 123, '2020-04-18 16:00:00', '2020-04-20 16:00:00');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `money` double NOT NULL,
  `statu` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (3, '5c0a57df-cefd-4d92-b90a-fc005ffb1784', '154356', '13475', '2020-04-19 16:00:00', 111, 1);
INSERT INTO `orders` VALUES (4, '61612226-f4e2-4f38-9453-674b28f96e12', '154356', '13475', '2020-04-19 16:00:00', 111, 1);
INSERT INTO `orders` VALUES (5, 'b875bce9-8634-4f80-9ee3-115f300b340d', '122456', '13475', '2020-04-19 16:00:00', 123, 0);

-- ----------------------------
-- Table structure for shop_detail
-- ----------------------------
DROP TABLE IF EXISTS `shop_detail`;
CREATE TABLE `shop_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `shop_id`(`shop_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_detail
-- ----------------------------
INSERT INTO `shop_detail` VALUES (1, '154356', '我是详细图片地址串');
INSERT INTO `shop_detail` VALUES (2, '122456', '第二个商品');
INSERT INTO `shop_detail` VALUES (3, '1111', '第三个');
INSERT INTO `shop_detail` VALUES (4, 'b582bf58-0b2b-40c7-a1cf-0958d3635b93', '/详细');
INSERT INTO `shop_detail` VALUES (5, '0eb6b9dc-1ee4-4181-8173-36fe5aba6009', '11');

-- ----------------------------
-- Table structure for shops
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `price` double(10, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shops
-- ----------------------------
INSERT INTO `shops` VALUES (1, '154356', '垃圾货', 'imaimage', 98, 889.00);
INSERT INTO `shops` VALUES (2, '122456', '第二', 'ddd', 112, 234.00);
INSERT INTO `shops` VALUES (3, '1111', '阿三打', 'sddd', 3, 1000.00);
INSERT INTO `shops` VALUES (4, 'b582bf58-0b2b-40c7-a1cf-0958d3635b93', '新增商品标题', '/大大', 81, 888.00);
INSERT INTO `shops` VALUES (5, '0eb6b9dc-1ee4-4181-8173-36fe5aba6009', '打算', '11', 11, 11.00);

-- ----------------------------
-- Table structure for stocklog
-- ----------------------------
DROP TABLE IF EXISTS `stocklog`;
CREATE TABLE `stocklog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `incr` int(11) NOT NULL,
  `decr` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stocklog
-- ----------------------------
INSERT INTO `stocklog` VALUES (1, '154356', 99, 0);
INSERT INTO `stocklog` VALUES (2, '122456', 111, 0);
INSERT INTO `stocklog` VALUES (3, '1111', 3, 0);
INSERT INTO `stocklog` VALUES (4, '154356', 1, 0);
INSERT INTO `stocklog` VALUES (5, '154356', 0, 3);
INSERT INTO `stocklog` VALUES (6, '154356', 3, 3);
INSERT INTO `stocklog` VALUES (7, '122456', 3, 2);
INSERT INTO `stocklog` VALUES (8, '154356', 1, 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 13475, 'yong', '123');
INSERT INTO `users` VALUES (2, 65455, 'cyl', '124');

SET FOREIGN_KEY_CHECKS = 1;
