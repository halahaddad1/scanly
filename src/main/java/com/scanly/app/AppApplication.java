package com.scanly.app;

import com.scanly.app.CanonicalProducts.ParseFullCSV;
import com.scanly.app.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

    @Autowired
    FirebaseService firebaseService;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public CommandLineRunner postStartupLauncher() {
        return args -> {
            boolean seeded = false;
            boolean ranCmd = false;

            for (String arg : args) {
                if (! seeded && arg.equals("-seed")) {
                    seeded = true;
                    ranCmd = true;
                    ParseFullCSV runner = new ParseFullCSV();
                    runner.parseCSV(firebaseService);
                }
            }

            if (ranCmd) {
                System.exit(0);
            }
        };
    }
}
