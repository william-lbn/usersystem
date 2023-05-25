CREATE TABLE `t_os_average_qps` (
	`id` BIGINT ( 64 ) NOT NULL,
	`sn` VARCHAR ( 45 ) NOT NULL COMMENT '服务标识',
	`value` DOUBLE NOT NULL COMMENT '平均QPS',
	`time` DATETIME ( 0 ) NOT NULL COMMENT '数据生成时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	INDEX name_index ( `time` )
) ENGINE = INNODB CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '平均QPS表' ROW_FORMAT = Dynamic;