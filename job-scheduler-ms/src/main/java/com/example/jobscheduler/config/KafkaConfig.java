package com.example.jobscheduler.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    NewTopic jobExecutionsTopic(@Value("${app.kafka.topic}") String topic){
        return TopicBuilder.name(topic).partitions(6).replicas(1).build();
    }
}
