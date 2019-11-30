package com.omnius.tms.dal;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.omnius.tms.dto.TaskDTO;
import com.omnius.tms.service.KafkaProducer;

/** The Class TaskDalImpl. */
@Repository
public class TaskDalImpl implements ITaskDal {
	
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(TaskDalImpl.class);

    /** The jdbc template. */
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /*
     * (non-Javadoc)
     * @see com.omnius.tms.dal.ITaskDal#findAll()
     */
    @Override
    public List<TaskDTO> findAll() {
        String sql = "SELECT * FROM task";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new TaskDTO(UUID.fromString(rs.getString("id")), rs.getDate("due_date"),
                rs.getDate("resolved_on"),
                rs.getString("title"),
                rs.getString("description"), rs.getInt("priority"), rs.getString("status"), rs.getDate("created_on"), rs.getDate("updated_on")));
    }

    /** Find one.
     *
     * @param uuid
     *            the uuid
     * @return the task DTO */
    @Override
    public TaskDTO findOne(UUID uuid) {
        String sql = "SELECT * FROM task where id = '" + uuid + "'";
        try {
        	return jdbcTemplate.queryForObject(sql,
        			(rs, rowNum) -> new TaskDTO(UUID.fromString(rs.getString("id")), rs.getDate("due_date"), rs.getDate("resolved_on"),
        					rs.getString("title"), rs.getString("description"), rs.getInt("priority"), rs.getString("status"), rs.getDate("created_on"),
        					rs.getDate("updated_on")));
        } catch(EmptyResultDataAccessException e) {
        	logger.error("No record found for the id "+uuid);
        	return null;
        }
    }

}
