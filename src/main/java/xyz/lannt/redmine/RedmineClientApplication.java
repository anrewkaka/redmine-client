package xyz.lannt.redmine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RedmineClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedmineClientApplication.class, args);
    }

}
