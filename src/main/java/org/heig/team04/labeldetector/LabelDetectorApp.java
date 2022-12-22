package org.heig.team04.labeldetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootApplication
public class LabelDetectorApp {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(LabelDetectorApp.class, args);
    }

}
