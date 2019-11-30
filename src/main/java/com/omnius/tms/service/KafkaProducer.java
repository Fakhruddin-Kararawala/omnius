package com.omnius.tms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.omnius.tms.model.Task;

@Service
public class KafkaProducer {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    /** The topic. */
    @Value("${spring.kafka.topic}")
    private String topic;

    /** The kafka template. */
    @Autowired
    private KafkaTemplate<String, Task> kafkaTemplate;

    /** Send message.
     *
     * @param task
     *            the task */
    public void sendMessage(Task task) {
        logger.info(String.format("#### -> Producing message -> %s", task));
        this.kafkaTemplate.send(topic, task);
    }
}