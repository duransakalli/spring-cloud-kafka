package com.drn.springcloud;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class Producer {

    private final KafkaTemplate<String, Person> template;
    Faker faker;

    @EventListener(ApplicationStartedEvent.class)
    public void generate() {
        final Flux<Long> interval = Flux.interval(Duration.ofMillis(3_000));

        final Flux<Person> person = Flux.fromStream(Stream.generate(this::generatePerson));

        Flux.zip(person, interval).map(it -> template.send("person", it.getT1().getSsn(), it.getT1())).blockLast();
    }

    private Person generatePerson() {
        faker = Faker.instance();

        return Person.builder()
                .ssn(faker.idNumber().ssnValid())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .address(generateAddress())
                .build();
    }

    private Address generateAddress() {
        faker = Faker.instance();
        return Address.builder()
                .buildingNumber(faker.address().buildingNumber())
                .streetName(faker.address().streetName())
                .city(faker.address().city())
                .country(faker.address().country())
                .postalCode(faker.address().zipCode())
                .build();
    }

}
