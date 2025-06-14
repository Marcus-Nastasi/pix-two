package com.open.pix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PixApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixApplication.class, args);
	}

}
