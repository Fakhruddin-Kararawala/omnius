package com.omnius.tms.config;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnius.tms.model.Task;

/** The Class TaskDeserializer. */
public class TaskDeserializer implements Deserializer<Task> {

    /*
     * (non-Javadoc)
     * @see org.apache.kafka.common.serialization.Deserializer#deserialize(java.lang.String, byte[])
     */
    @Override
    public Task deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Task task = null;
        try {
            task = mapper.readValue(arg1, Task.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }
}
