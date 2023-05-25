SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_test
-- ----------------------------
DROP TABLE IF EXISTS `t_user_test`;
CREATE TABLE `t_user_test` (
  `id`   bigint unsigned auto_increment comment '自增id'  primary key,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;


