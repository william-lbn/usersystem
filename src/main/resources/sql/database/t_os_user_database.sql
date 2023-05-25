CREATE TABLE `t_os_user_database` (
	`id` BIGINT ( 64 ) NOT NULL,
	`team_id` BIGINT ( 20 ) NOT NULL COMMENT '团队Id',
	`service_type` VARCHAR ( 45 ) NOT NULL COMMENT '服务类型',
	`sid` VARCHAR ( 200 ) DEFAULT NULL COMMENT '标识ID',
	`env_name` VARCHAR ( 200 ) NOT NULL COMMENT '环境名称',
	`mysql_user` VARCHAR ( 45 ) NOT NULL COMMENT 'mysql用户开户名称',
	`mysql_secret` INT NOT NULL COMMENT 'mysql用户开户密码',
	`app_name` VARCHAR ( 64 ) DEFAULT NULL COMMENT '已经授权的应用名称',
	`app_enname` VARCHAR ( 64 ) DEFAULT NULL COMMENT '已经授权的应用英文名称，用于蓝图发布',
	`delete_flag` INT ( 1 ) NOT NULL DEFAULT 0 COMMENT '是否删除，0在使用，1删除',
	`create_user` VARCHAR ( 255 ) NOT NULL COMMENT '创建人',
	`create_time` DATETIME ( 0 ) NOT NULL COMMENT '数据库开户创建时间',
	`update_time` DATETIME ( 0 ) NOT NULL COMMENT '数据库开户更新时间',
	PRIMARY KEY ( `id` ) USING BTREE
) ENGINE = INNODB CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据库开户表' ROW_FORMAT = Dynamic;





