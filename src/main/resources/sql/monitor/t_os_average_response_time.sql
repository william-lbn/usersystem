CREATE TABLE `t_os_average_response_time` (
	`id` BIGINT ( 64 ) NOT NULL,
	`sn` VARCHAR ( 45 ) NOT NULL COMMENT '服务标识',
	`value` DOUBLE NOT NULL COMMENT '平均响应耗时',
	`time` DATETIME ( 0 ) NOT NULL COMMENT '数据生成时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	INDEX name_index ( `time` )
) ENGINE = INNODB CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '平均响应耗时表' ROW_FORMAT = Dynamic;