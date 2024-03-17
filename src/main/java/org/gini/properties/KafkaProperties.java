package org.gini.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "spring.kafka.consumer")
public record KafkaProperties(
        String bootstrapServers,
        String keyDeserializer,
        String valueDeserializer,
        String autoOffsetReset,
        String groupId
) {}
