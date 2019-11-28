package com.omnius.tms.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnius.tms.dal.ITaskDal;
import com.omnius.tms.dto.TaskDTO;
import com.omnius.tms.model.Task;
import com.omnius.tms.repository.TaskRepository;
import com.omnius.tms.service.KafkaProducer;

/** The Class TaskController. */
@RestController
@RequestMapping(value = "/task")
public class TaskController {

    /** The producer. */
    private final KafkaProducer producer;

    /** The repository. */
    @Autowired
    private TaskRepository repository;

    /** The task dal. */
    @Autowired
    private ITaskDal taskDal;

    /** Instantiates a new task controller.
     *
     * @param producer
     *            the producer */
    @Autowired
    TaskController(KafkaProducer producer) {
        this.producer = producer;
    }

    /** Creates the task.
     *
     * @param taskDTO
     *            the task DTO */
    @PostMapping(value = "/create")
    public void createTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        this.producer.sendMessage(task);
    }

    /** Find all.
     *
     * @return the list */
    @GetMapping(value = "/list")
    public List<TaskDTO> findAll() {
        return this.taskDal.findAll();
    }

    /** Update task.
     *
     * @param taskDTO
     *            the task DTO */
    @PostMapping(value = "/update")
    public void updateTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        UUID.fromString(taskDTO.getId().toString());
        BeanUtils.copyProperties(taskDTO, task);
        this.repository.save(task);
    }

    /** Find one.
     *
     * @param uuid
     *            the uuid
     * @return the task DTO */
    @GetMapping(value = "/uuid/{uuid}")
    public TaskDTO findOne(@PathVariable("uuid") String uuid) {
        return this.taskDal.findOne(UUID.fromString(uuid));
    }

    /** Delete task.
     *
     * @param uuid
     *            the uuid */
    @PostMapping(value = "/delete/{uuid}")
    public void deleteTask(@PathVariable("uuid") String uuid) {
        this.repository.deleteById(UUID.fromString(uuid));
    }
}