package com.omnius.tms.service;

import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.omnius.tms.model.Task;
import com.omnius.tms.repository.TaskRepository;

/** The Class KafkaConsumer. */
@Service
public class KafkaConsumer {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    /** The repository. */
    @Autowired
    private TaskRepository repository;

    /** Consume.
     *
     * @param task
     *            the task */
    @KafkaListener(topics = "#{'${spring.kafka.topic}'}", groupId = "group_id")
    public void consume(Task task) {
        logger.info(String.format("#### -> Consumed message -> %s", task));
        repository.save(task);
    }
}
