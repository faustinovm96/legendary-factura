package com.spingboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spingboot.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringBootDatajpaApplication implements CommandLineRunner {

	@Autowired
	private IUploadFileService upload;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatajpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		upload.deleteAll();
		upload.init();
		String password = "12345";

		for (int i = 0; i < 2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}

}
