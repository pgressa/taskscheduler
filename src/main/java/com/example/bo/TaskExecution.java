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

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Pavol Gressa
 */
public class TaskExecution {
    private String jobClass;
    private LocalDateTime executionStart;
    private Duration executionDuration;

    public TaskExecution(String jobClass, LocalDateTime executionStart) {
        this.jobClass = jobClass;
        this.executionStart = executionStart;
    }

    public void setExecutionDuration(Duration executionDuration) {
        if (this.executionDuration == null) {
            this.executionDuration = executionDuration;
        } else {
            throw new IllegalArgumentException("Task duration already set");
        }
    }

    public String getJobClass() {
        return jobClass;
    }

    public LocalDateTime getExecutionStart() {
        return executionStart;
    }

    public Duration getExecutionDuration() {
        return executionDuration;
    }
}
