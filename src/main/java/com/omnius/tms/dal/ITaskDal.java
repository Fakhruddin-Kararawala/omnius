package com.omnius.tms.dal;

import java.util.List;
import java.util.UUID;

import com.omnius.tms.dto.TaskDTO;

/** The Interface ITaskDal. */
public interface ITaskDal {

    /** Find all.
     *
     * @return the list */
    List<TaskDTO> findAll();

    /** Find one.
     *
     * @param uuid
     *            the uuid
     * @return the task DTO */
    TaskDTO findOne(UUID uuid);
}
