package com.brgl.assignment;

import com.brgl.upload.UploadFileImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InMemoryDbCrudAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(InMemoryDbCrudAssignmentApplication.class, args);

		UploadFileImpl uploadFile = new UploadFileImpl();
		uploadFile.uploadExerciseFile();


	}

}
