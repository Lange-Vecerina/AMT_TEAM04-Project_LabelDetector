package org.heig.team04.labeldetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabelDetectorApp {

    public static void main(String[] args) {

        String str = "bonjour";
        String []array = str.split("/", 2);
        System.out.println(array.length);
        System.out.println(array[0]);
        System.out.println(array[1].length());

        SpringApplication.run(LabelDetectorApp.class, args);
    }

}
