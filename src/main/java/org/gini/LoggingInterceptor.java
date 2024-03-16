package org.gini;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.gini.model.User;
import org.slf4j.MDC;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record LoggingInterceptor(ObjectMapper objectMapper) implements RecordInterceptor<String, String> {


    @Override
    public ConsumerRecord<String, String> intercept(ConsumerRecord<String, String> record, Consumer<String, String> consumer) {
        log.info("intercepting record ------------------------- {}", record.value());

        try {
            var user = objectMapper.readValue(record.value(), User.class);

            MDC.put("username", user.username());
            MDC.put("age", ""+ user.age());


        } catch (IllegalArgumentException | JsonProcessingException e) {

            log.error("null+++++++++++++ {}", e.getCause());

        }

        return record;
    }

    @Override
    public void afterRecord(ConsumerRecord<String, String> record, Consumer<String, String> consumer) {
      //  RecordInterceptor.super.afterRecord(record, consumer);

        log.info("record successfully processed ++++++++++++++++++++++++++ {}", record.value());

        MDC.clear();

        log.info("////////////////////////////////");
    }
}
