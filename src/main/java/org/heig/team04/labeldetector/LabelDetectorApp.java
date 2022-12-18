package org.heig.team04.labeldetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootApplication
public class LabelDetectorApp {

    public static void main(String[] args) throws IOException {


        File file = new File("src/main/resources/voiture.png");
        byte[] array = Files.readAllBytes(file.toPath());

        System.out.print("[");
        for(int i = 0; i < array.length; ++i){
            System.out.print(array[i]);
            System.out.print(",");
        }
        System.out.println("]");
        SpringApplication.run(LabelDetectorApp.class, args);
    }

}
