CREATE TABLE `t_os_user_database_app` (
	`id` BIGINT ( 64 ) NOT NULL,
	`user_database_id` BIGINT ( 64 ) NOT NULL COMMENT '关联t_os_user_database表中id',
	`team_id` BIGINT ( 20 ) NOT NULL COMMENT '团队Id',
	`app_name` VARCHAR ( 64 ) DEFAULT NULL COMMENT '已经授权的应用名称',
	`app_enname` VARCHAR ( 64 ) DEFAULT NULL COMMENT '已经授权的应用英文名称，用于蓝图发布',
	`create_user` VARCHAR ( 255 ) NOT NULL COMMENT '创建人',
	`create_time` DATETIME ( 0 ) NOT NULL COMMENT '数据库开户创建时间',
	`update_time` DATETIME ( 0 ) NOT NULL COMMENT '数据库开户更新时间',
	PRIMARY KEY ( `id` ) ,
	INDEX (`user_database_id`) USING BTREE
) ENGINE = INNODB CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据库应用授权表' ROW_FORMAT = Dynamic;