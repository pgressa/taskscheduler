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

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;

import java.util.List;
import java.util.Objects;

/**
 * @author Pavol Gressa
 */
@Introspected
public class TaskDefinition {

    private final List<ParamValue> paramsInfo;
    private final String cronExpression;
    private final String jobClass;

    public TaskDefinition(@NonNull String jobClass, @NonNull String cronExpression, @NonNull List<ParamValue> paramsInfo) {
        this.paramsInfo = paramsInfo;
        this.cronExpression = cronExpression;
        this.jobClass = jobClass;
    }

    public List<ParamValue> getParamsInfo() {
        return paramsInfo;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public String getJobClass() {
        return jobClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDefinition)) return false;
        TaskDefinition that = (TaskDefinition) o;
        return paramsInfo.equals(that.paramsInfo) && cronExpression.equals(that.cronExpression) && jobClass.equals(that.jobClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paramsInfo, cronExpression, jobClass);
    }

    @Override
    public String toString() {
        return "TaskDefinition{" +
                "paramsInfo=" + paramsInfo +
                ", cronExpression='" + cronExpression + '\'' +
                ", jobClass='" + jobClass + '\'' +
                '}';
    }
}
