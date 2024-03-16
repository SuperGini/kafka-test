package org.gini;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.gini.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record Consumer(
        ObjectMapper objectMapper,
        ServiceTest serviceTest
) {

    @KafkaListener(
            topics = "test-vehicle",
            groupId = "group54",
            containerFactory = "kafkaListenerContainerFactoryx"
    )
    public void consume(ConsumerRecord<String, String> record) {

        try {

            var user = objectMapper.readValue(record.value(), User.class);
            serviceTest.doSomething(user);

        } catch (Exception e) {
            log.error("blala bla bla bla bla bla bla bla bla ");
            //save
         //   throw new IllegalArgumentException("Something went wrong");

        }


    }
}
