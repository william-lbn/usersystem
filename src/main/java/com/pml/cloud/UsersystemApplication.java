package com.pml.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.pml.cloud.dao.mapper")
@SpringBootApplication
public class UsersystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersystemApplication.class, args);
	}

}
