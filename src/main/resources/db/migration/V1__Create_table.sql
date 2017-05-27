/*
MySQL Data Transfer
Source Host: localhost
Source Database: eduweb
Target Host: localhost
Target Database: eduweb
Date: 2017/1/2 9:48:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password1` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `level1` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `school` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `cour_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `coll_time` datetime DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cour2` (`cour_id`),
  KEY `userid5` (`user_id`),
  CONSTRAINT `cour2` FOREIGN KEY (`cour_id`) REFERENCES `t_course` (`id`),
  CONSTRAINT `userid5` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `user_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `video_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `com_time` datetime DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`user_id`),
  KEY `videoid` (`video_id`),
  CONSTRAINT `userid` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `videoid` FOREIGN KEY (`video_id`) REFERENCES `t_video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `cour_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `img_url` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `teacher_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `cour_intro` varchar(400) COLLATE utf8_bin DEFAULT NULL,
  `teacher_intro` varchar(400) COLLATE utf8_bin DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `school` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sub_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `top` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `scroll_img` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courseId` (`sub_id`),
  CONSTRAINT `courseId` FOREIGN KEY (`sub_id`) REFERENCES `t_subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_play
-- ----------------------------
DROP TABLE IF EXISTS `t_play`;
CREATE TABLE `t_play` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `user_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `video_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `time_long` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid1` (`user_id`),
  KEY `videoid1` (`video_id`),
  CONSTRAINT `userid1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `videoid1` FOREIGN KEY (`video_id`) REFERENCES `t_video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `video_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `score` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid3` (`user_id`),
  KEY `video4` (`video_id`),
  CONSTRAINT `userid3` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `video4` FOREIGN KEY (`video_id`) REFERENCES `t_video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `sub_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `nick_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `real_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sex` bit(1) DEFAULT NULL,
  `qq_number` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone_state` bit(1) DEFAULT NULL,
  `email_state` bit(1) DEFAULT NULL,
  `user_password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `head_image` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `personal_intro` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `cour_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `video_desc` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `video_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `time_long` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `chapter` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `upload_time` date DEFAULT NULL,
  `thumb` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `size` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courId` (`cour_id`),
  CONSTRAINT `courId` FOREIGN KEY (`cour_id`) REFERENCES `t_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
  
INSERT INTO t_admin(id,user_name,password1,level1,school,state)  VALUES ('297e386a56410ddd0156411a94c50010', 'tadmin', '123456', '2', '湘潭大学', 1);
INSERT INTO t_admin(id,user_name,password1,level1,school,state) VALUES ('297e386a56410ddd0156411a94c50011', 'admin1', '123456', '1', '湖南大学', 1);
INSERT INTO t_admin(id,user_name,password1,level1,school,state) VALUES ('297e386a56410ddd0156411a94c50012', 'admin2', '123456', '1', '中南大学', 1);
INSERT INTO t_admin(id,user_name,password1,level1,school,state) VALUES ('297e386a56410ddd0156411a94c50013', 'admin3', '123456', '1', '华中科技大学', 1);
INSERT INTO t_subject(id,sub_name,state) VALUES ('297e386a56410ddd0156411a94c50001', '经济', 1);
INSERT INTO t_subject(id,sub_name,state) VALUES ('297e386a56410ddd0156411a94c50002', '人文', 1);
INSERT INTO t_subject(id,sub_name,state) VALUES ('297e386a56410ddd0156411a94c50003', '哲学', 1);
INSERT INTO t_subject(id,sub_name,state) VALUES ('297e386a56410ddd0156411a94c50004', '工科', 1);
INSERT INTO t_subject(id,sub_name,state) VALUES ('297e386a56410ddd0156411a94c50005', '计算机', 1);
INSERT INTO t_subject(id,sub_name,state) VALUES ('297e386a56410ddd0156411a94c50006', '思政', 1);
INSERT INTO t_subject(id,sub_name,state) VALUES ('297e386a56410ddd0156411a94c50007', '理科', 1);
INSERT INTO t_subject(id,sub_name,state) VALUES ('297e386a56410ddd0156411a94c50008', '其他', 1);

INSERT INTO `t_user` VALUES ('297e386a56410ddd0156411a94c50020', 'xhw', '肖华文', '', '11111111111', 1, '1370755152', 1, 1, '123456', 'fdhfdg', 'sadfs', 1);
INSERT INTO `t_user` VALUES ('297e386a56410ddd0156411a94c50021', 'hepan', '何攀', '13257499906@163.com', '13257499906', 1, '1370755152', 1, 1, '123456', 'wretwret', 'ewrtew˿', 1);
INSERT INTO `t_user` VALUES ('297e386a56410ddd0156411a94c50022', 'user', 'user', '1253421@163.com', '14231443215', 1, '42134', 1, 1, '123456', '1114325432', 'ewrtew ', 1);
