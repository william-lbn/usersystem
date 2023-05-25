CREATE TABLE `t_os_pressure_task` (
    `id` bigint(64) NOT NULL ,
    `app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
    `env_name` varchar(200) NOT NULL COMMENT '环境名称',
    `sid` VARCHAR ( 200 ) DEFAULT NULL COMMENT '标识ID',
    `response_body` TEXT  COMMENT '响应体',
    `team_id` BIGINT ( 20 ) NOT NULL COMMENT '团队Id',
    `create_user` varchar(255) NOT NULL COMMENT '创建人',
    `create_time` datetime(0) NOT NULL COMMENT '执行任务创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '执行任务表' ROW_FORMAT = Dynamic;