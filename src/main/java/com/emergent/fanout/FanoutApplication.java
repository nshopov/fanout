package com.emergent.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class FanoutApplication {
	private static final Logger logger = LoggerFactory.getLogger(FanoutApplication.class);

	public static void main(String[] args) {
		var context = SpringApplication.run(FanoutApplication.class, args);
		Environment env = context.getEnvironment();

		var activeProfiles = env.getActiveProfiles();
		if (activeProfiles.length == 0) {
			logger.info("⚠\uFE0F No active Spring profile set. Using default profile: 'h2'");
		} else {
			logger.info("✅ Active Spring profile(s): {}", String.join(", ", activeProfiles));
		}
	}

}
