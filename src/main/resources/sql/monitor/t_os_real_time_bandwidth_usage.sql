CREATE TABLE `t_os_real_time_bandwidth_usage` (
	`id` BIGINT ( 64 ) NOT NULL,
	`sn` VARCHAR ( 45 ) NOT NULL COMMENT '服务标识',
	`value` DOUBLE NOT NULL COMMENT '实时带宽占用',
	`time` DATETIME ( 0 ) NOT NULL COMMENT '数据生成时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	INDEX name_index ( `time` )
) ENGINE = INNODB CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '实时带宽占用表' ROW_FORMAT = Dynamic;