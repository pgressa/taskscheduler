package com.example.jobs;

import com.example.bo.JobParam;

/**
 * @author Pavol Gressa
 * @since 2.5
 */
public interface Job {

    void execute(JobParam params);
}
