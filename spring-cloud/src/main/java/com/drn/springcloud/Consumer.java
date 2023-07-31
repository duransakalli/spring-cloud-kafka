package com.drn.springcloud;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "person", groupId = "spring-kafka")
    public void consume(Person person) {
        System.out.println("Received: " + person.toString());
    }

}

