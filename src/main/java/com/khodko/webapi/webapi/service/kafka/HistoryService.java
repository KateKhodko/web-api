package com.khodko.webapi.webapi.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class HistoryService {

    private final KafkaTemplate<Long, HttpServletRequest> kafkaTemplate;

    @Autowired
    public HistoryService(KafkaTemplate<Long, HttpServletRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = {"request"}, groupId = "${kafka.group.id}")
    public void produce(HttpServletRequest request) {
        kafkaTemplate.send("request", request);
    }
}
