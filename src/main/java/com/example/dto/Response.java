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
package com.example.dto;

import com.example.bo.TaskDefinition;
import io.micronaut.core.annotation.Introspected;

/**
 * @author Pavol Gressa
 */
@Introspected
public class Response {

    private final String jobId;
    private final TaskDefinition taskDefinition;
    private final String delay;

    public Response(String jobId, TaskDefinition taskDefinition, String delay) {
        this.jobId = jobId;
        this.taskDefinition = taskDefinition;
        this.delay = delay;
    }

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public String getDelay() {
        return delay;
    }

    public String getJobId() {
        return jobId;
    }
}
