CREATE TABLE `t_os_pressure_test` (
  `id` bigint(64) NOT NULL ,
`task_number` bigint(20) NOT NULL COMMENT '任务编号',
`app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
`env_name` varchar(200) NOT NULL COMMENT '环境名称',
`single_replica_capacity` varchar(200) NOT NULL COMMENT '单副本容量',
`task_status` varchar(200) NOT NULL COMMENT '任务状态',
`team_id` BIGINT ( 20 ) NOT NULL COMMENT '团队Id',
`create_user` varchar(255) NOT NULL COMMENT '创建人',
`create_time` datetime(0) NOT NULL COMMENT '压力测试创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '压力测试表' ROW_FORMAT = Dynamic;