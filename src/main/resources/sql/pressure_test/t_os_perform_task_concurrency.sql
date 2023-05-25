CREATE TABLE `t_os_perform_task_concurrency` (
    `id` bigint(64) NOT NULL ,
    `perform_task_id` bigint(64) NOT NULL  COMMENT '关联t_os_pressure_task表中的id;有多个并发配置',
    `qps` int(64) NOT NULL COMMENT 'qps设置',
    `duration`  int(64) NOT NULL COMMENT '持续时间，单位秒(s)',
    `cpu` int(64) DEFAULT NULL COMMENT 'CPU使用率，测试完后返回的结果',
    `memory`  int(64) DEFAULT NULL COMMENT '内存使用率，测试完后返回的结果',
    `success_rate` int(64) DEFAULT NULL COMMENT '成功率，测试完后返回的结果',
    `team_id` BIGINT ( 20 ) NOT NULL COMMENT '团队Id',
    `create_user` varchar(255) NOT NULL COMMENT '创建人',
    `create_time` datetime(0) NOT NULL COMMENT '并发配置创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '并发制品配置创建任务表' ROW_FORMAT = Dynamic;