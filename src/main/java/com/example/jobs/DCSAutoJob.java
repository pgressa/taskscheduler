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
package com.example.jobs;

import com.example.bo.JobParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pavol Gressa
 * @since 2.5
 */
public abstract class DCSAutoJob implements Job{

    private static final Logger log = LoggerFactory.getLogger(DCSAutoJob.class);

    @Override
    public void execute(JobParam params){
        log.debug("DCSAutoJob called!");
        Map<String, String> map = new HashMap<>();
        if (params != null) {
            map = params.getParamMap();
            if (map == null) {
                throw new NullPointerException("job data map does not exist, ERROR");
            }
        }
        executeJob(params);
        List<String> dcsJobId = getDcsJobIds();
        if (dcsJobId != null && !dcsJobId.isEmpty()){
            map.put(JobParam.DCS_JOB_ID_KEY, String.join(",",dcsJobId));
            log.debug("Set " + map.get(JobParam.DCS_JOB_ID_KEY)
                    + " as the jobIds ");
        }else{
            log.debug("This execution doesn't create DCS job");
        }
    }

    /**
     * return dcs job IDs created by this auto job, if no jobs are created,
     * return NULL
     * @return
     */
    public abstract List<String> getDcsJobIds();

    public abstract void executeJob(JobParam params);
}