CREATE TABLE `t_os_peek_qps` (
	`id` BIGINT ( 64 ) NOT NULL,
	`sn` VARCHAR ( 45 ) NOT NULL COMMENT '服务标识',
	`value` DOUBLE NOT NULL COMMENT '峰值QPS',
	`time` DATETIME ( 0 ) NOT NULL COMMENT '数据生成时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	INDEX name_index ( `time` )
) ENGINE = INNODB CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '峰值QPS表' ROW_FORMAT = Dynamic;