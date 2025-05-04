package com.fatesaikou.test.jooq.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fatesaikou.test.jooq.learn.mapper")
public class JooqtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JooqtestApplication.class, args);
	}

}
