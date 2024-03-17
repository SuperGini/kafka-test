package org.gini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
public class KafkaConsumerTestConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerTestConfigApplication.class, args);
    }

}
