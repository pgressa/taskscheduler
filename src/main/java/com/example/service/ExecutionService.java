/*
 * Copyright 2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.service;

import com.example.bo.JobParam;
import com.example.bo.TaskDefinition;
import com.example.jobs.Job;
import io.micronaut.context.BeanContext;
import io.micronaut.inject.BeanConfiguration;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Optional;

/**
 * @author Pavol Gressa
 */
@Singleton
public class ExecutionService {

    public static final Logger log = LoggerFactory.getLogger(ExecutionService.class);

    private final BeanContext beanContext;

    public ExecutionService(BeanContext beanContext) {
        this.beanContext = beanContext;
    }

    public void execute(TaskDefinition taskDefinition) {
        log.info("EXECUTING: {}", taskDefinition);
        try {
            try {
                Class c = Class.forName(taskDefinition.getJobClass());
                Object bean = beanContext.createBean(c);
                if (bean instanceof Job) {
                    Job job = (Job) bean;
                    job.execute(new JobParam(taskDefinition.getParamsInfo()));
                } else {
                    log.error("Failed to create a bean");
                }

            } catch (ClassNotFoundException e) {
                log.error("Failed to find class " + taskDefinition.getJobClass());
            }
            Thread.currentThread().wait(Duration.ofSeconds(5).toMillis());
        } catch (InterruptedException e) {
            log.info("Execution of " + taskDefinition + " interrupted ");
        }
    }
}
