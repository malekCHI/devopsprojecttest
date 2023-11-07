package tn.esprit.devops_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "tn.esprit")
public class DevOps_ProjectSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevOps_ProjectSpringBootApplication.class, args);
    }

}