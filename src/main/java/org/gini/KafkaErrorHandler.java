package org.gini;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public record KafkaErrorHandler() {

    public DefaultErrorHandler defaultErrorHandler() {

       // var ignoredExceptions = List.of(IllegalArgumentException.class);

        //face retry de 2 ori la un interval de o secunda
        // var fixedBackOff = new FixedBackOff(1000L, 2);

        //exponential backoff
        var exponentialBackOff = new ExponentialBackOffWithMaxRetries(2);
        exponentialBackOff.setInitialInterval(500L); // primul retry dupa 1 secunda
        exponentialBackOff.setMultiplier(2.0); //al doilea retry dupa 2 secunde
        exponentialBackOff.setMaxInterval(2_000L);



        var errorHandler = new DefaultErrorHandler(exponentialBackOff);


        //exceptii pentru care nu vrem sa le facem retry
        //  errorHandler.addNotRetryableExceptions();
        // ignoredExceptions.forEach(x -> errorHandler.addNotRetryableExceptions(x));
       // ignoredExceptions.forEach(x -> errorHandler.addRetryableExceptions(x)); //-> pentru astea vrem sa facem retry

        //nu se prea foloseste listener decat pentru debug
        errorHandler.setRetryListeners(((record, ex, deliveryAttempt) -> {
            log.info("Failed Record in Retry Listener, Exception: {}, deliveryAttempt: {}", ex.getMessage(), deliveryAttempt);

        }));



        //errorHandler.addRetryableExceptions();

        return errorHandler;


        //face retry de 10 ori
        // return new DefaultErrorHandler();
    }
}
