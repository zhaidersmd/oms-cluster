package com.foundation.event;

import com.foundation.dao.OutboxRepository;
import com.foundation.model.OutboxEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OutboxEventPublisher {


    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OutboxEventPublisher(OutboxRepository outboxRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.outboxRepository = outboxRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishEvents() {

        List<OutboxEvent> events = outboxRepository.findByStatus("NEW");
        System.out.println("Getting events from repo...");
        for (OutboxEvent event : events) {
            try {
                kafkaTemplate.send("customer-events", event.getPayload());
                event.setStatus("SENT");
                outboxRepository.save(event);
                System.out.println("Event sent to Kafka");
            } catch (Exception e) {
                // log and retry next cycle
            }
        }
    }
}
