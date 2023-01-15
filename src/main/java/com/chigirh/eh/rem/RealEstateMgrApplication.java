package com.chigirh.eh.rem;

import com.chigirh.eh.rem.domain.common.ReMgrContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RealEstateMgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstateMgrApplication.class, args);
	}

	@Bean
	public ReMgrContext reMgrContext() {
		return new ReMgrContext();
	}

}
