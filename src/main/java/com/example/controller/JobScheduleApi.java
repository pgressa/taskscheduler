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
package com.example.controller;

import com.example.bo.ScheduledTaskContext;
import com.example.bo.TaskDefinition;
import com.example.dto.Response;
import com.example.service.SchedulingService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Pavol Gressa
 */
@Controller("/jobSchedules")
public class JobScheduleApi {

    public static final Logger log = LoggerFactory.getLogger(JobScheduleApi.class);
    private final SchedulingService schedulingService;

    public JobScheduleApi(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @Post()
    public Response createJobSchedule(@Body TaskDefinition body) {
        log.info("Received POST request to createJobSchedule on JobScheduleApi with input : " + body.toString());
        ScheduledTaskContext scheduledTaskContext = schedulingService.createSchedule(body);
        return responseFrom(scheduledTaskContext);
    }

    @Put("/{jobId}")
    public Response updateJobSchedule(@Body TaskDefinition body, @PathVariable String jobId) {
        log.info("Received PUT request to updateJobSchedule on JobScheduleApi with input : " + body.toString() + " jobId: " + jobId);
        ScheduledTaskContext scheduledTaskContext = schedulingService.updateSchedule(jobId, body);
        return responseFrom(scheduledTaskContext);
    }

    static Response responseFrom(ScheduledTaskContext task){
        return new Response(
                task.getJobId(),
                task.getTaskDefinition(),
                String.valueOf(task.getScheduledFuture().getDelay(TimeUnit.SECONDS))
        );
    }
}
