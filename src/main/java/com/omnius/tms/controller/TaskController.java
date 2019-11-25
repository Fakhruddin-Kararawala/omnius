package com.omnius.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnius.tms.model.Task;
import com.omnius.tms.service.KafkaProducer;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    /** The producer. */
    private final KafkaProducer producer;

    /** Instantiates a new task controller.
     *
     * @param producer
     *            the producer */
    @Autowired
    TaskController(KafkaProducer producer) {
        this.producer = producer;
    }

    /** Send message to kafka topic.
     *
     * @param task
     *            the task */
    @PostMapping(value = "/create")
    public void sendMessageToKafkaTopic(@RequestBody Task task) {
        this.producer.sendMessage(task);
    }
}