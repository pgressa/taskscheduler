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
package com.example.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * @author Pavol Gressa
 */
public class ScheduledTaskContext {

    private final String jobId;
    private TaskDefinition taskDefinition;
    private ScheduledFuture<?> scheduledFuture;
    private List<TaskExecution> taskExecutions;

    public ScheduledTaskContext(String jobId, TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
        this.jobId = jobId;
        taskExecutions = new ArrayList<>();
    }

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public ScheduledFuture<?> getScheduledFuture() {
        return scheduledFuture;
    }

    public List<TaskExecution> getTaskExecutions() {
        return taskExecutions;
    }

    public String getJobId() {
        return jobId;
    }

    public void setTaskDefinition(TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
    }

    public void setScheduledFuture(ScheduledFuture<?> scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }
}
