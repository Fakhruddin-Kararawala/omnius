package com.omnius.tms.service;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import com.omnius.tms.model.Task;

/** The Class TaskSchedular. */
@Service
public class TaskSchedular {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(TaskSchedular.class);

    /** The producer. */
    @Autowired
    private KafkaProducer producer;

    /** The r. Generating random values between 1 to 5 */
    Random r = new Random();

    /** Schedule. create new tasks at random interval */
    @Scheduled(fixedRate = 5000)
    public void schedule() {
        logger.info("Schedular called");
        Task task = new Task();
        task.setPriority(r.nextInt(4) + 1);
        task.setTitle(RandomStringUtils.randomAlphabetic(10));
        task.setStatus("New");
        task.setDueDate(new Date());
        producer.sendMessage(task);
    }
}
