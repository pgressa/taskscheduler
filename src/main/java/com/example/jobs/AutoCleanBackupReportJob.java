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
import com.example.jobs.report.BackupReport;
import com.example.service.LocalObjectStore;
import io.micronaut.context.annotation.Prototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author Pavol Gressa
 * @since 2.5
 */
@Prototype
public class AutoCleanBackupReportJob extends DCSAutoJob {

    public final static String SCHEDULE_NAME = "backupreport maintenance";
    public final static String SCHEDULE_GROUP = "maintenance";
    private static final String DESCI = "backup reports deletion";

    public final static String TABLE_KEY = "BackupReport";
    public static final String GROUP = "auto_clean_backup_report";
    private final static Logger log = LoggerFactory.getLogger(AutoCleanBackupReportJob.class);
    //    private final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"; //used in derby db
    private final static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final LocalObjectStore localObjectStore;

    /**
     * Note that the object storage is injected by micronaut
     */
    public AutoCleanBackupReportJob(LocalObjectStore localObjectStore) {
        this.localObjectStore = localObjectStore;
    }

    public static String getName() {
        return SCHEDULE_NAME;
    }

    public static String getDescription() {
        return DESCI;
    }

    @Override
    public void executeJob(JobParam params) {

        log.debug("Executejob of AutoCleanBackupReportJob called!!");

        if (params != null) {
            Map<String, String> map = params.getParamMap();
            String tbl = map.get(TABLE_KEY);
            log.debug("tbl -> " + tbl);
            if (tbl == null) {
                throw new NullPointerException("Unable to get 'BackupReport' parameter for AutoCleanBackupReportJob");
            }
            LocalDateTime cur = LocalDateTime.now();
            String daysEarlier = cur.minusDays(3).format(DATE_TIME_FORMAT);

            log.info("Going to delete BACKUPREPORT where UPDATEDTIME is older than " + daysEarlier);
            try {
                List list = localObjectStore.list(BackupReport.class, "UPDATEDTIME < timestamp('" + daysEarlier + "')", null);
                log.debug("objects to delete  " + list);
                if (list != null && !list.isEmpty()) {
                    Object[] objs = new Object[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        objs[i] = list.get(i);
                    }

                    localObjectStore.delete(objs);
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("The front fell off");
            }

        }

    }

    @Override
    public List<String> getDcsJobIds() {
        return null;
    }
}
