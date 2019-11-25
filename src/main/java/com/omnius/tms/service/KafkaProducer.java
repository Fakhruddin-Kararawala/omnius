package com.omnius.tms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.omnius.tms.model.Task;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, Task> kafkaTemplate;

    public void sendMessage(Task task) {
        logger.info(String.format("#### -> Producing message -> %s", task));
        this.kafkaTemplate.send(TOPIC, task);
    }
}