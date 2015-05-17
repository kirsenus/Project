package net.kirsa;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Properties;
import java.util.Scanner;



@ImportResource("classpath:integration.xml")
@SpringBootApplication
public class MyTestProjectApplication {

    private static final Logger LOGGER = Logger.getLogger(MyTestProjectApplication.class);
    private static final String HORIZONTAL_LINE =
            "\n=========================================================";


    public static void main(String[] args) throws IOException, URISyntaxException {
        final Scanner scanner = new Scanner(System.in);


        SpringApplication.run(MyTestProjectApplication.class, args);

        LOGGER.info(HORIZONTAL_LINE
                + "\n"
                + "\n    Please press 'q + Enter' to quit the application.    "
                + "\n"
                + HORIZONTAL_LINE );

        while (true) {

            final String input = scanner.nextLine();

            if("q".equals(input.trim())) {
                break;
            }

        }

        LOGGER.info("Exiting application...bye.");

        System.exit(0);
    }
}