package com.fatesaikou.test.jooq.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;

@NativeHint(
    options = "--enable-url-protocols=http,https",
    types = @TypeHint(types = org.postgresql.Driver.class)
)
@SpringBootApplication
@MapperScan("com.fatesaikou.test.jooq.learn.mapper")
public class JooqtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JooqtestApplication.class, args);
	}

}
