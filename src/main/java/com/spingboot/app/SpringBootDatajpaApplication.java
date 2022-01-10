package com.spingboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spingboot.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringBootDatajpaApplication implements CommandLineRunner{

	@Autowired
	private IUploadFileService upload;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatajpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		upload.deleteAll();
		upload.init();	
	}

}
