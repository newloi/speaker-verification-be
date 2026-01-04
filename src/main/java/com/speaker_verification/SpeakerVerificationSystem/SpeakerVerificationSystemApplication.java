package com.speaker_verification.SpeakerVerificationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpeakerVerificationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeakerVerificationSystemApplication.class, args);
	}

}
