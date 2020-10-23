/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ureportdemo.config;

import com.bstek.ureport.exception.ReportException;
import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/10/23 14:23
 */
@Component
public class MyReportProvider implements ReportProvider {

    private static String filePath = "D:/ureportfiles";
    private String prefix = "file:";
    private boolean disabled;

    static {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    public InputStream loadReport(String file) {
        if (file.startsWith(prefix)){
            file = filePath.substring(prefix.length(), file.length());
        }

        String fullPath = filePath + "/" + file;

        try {
            return new FileInputStream(fullPath);
        } catch (FileNotFoundException e) {
            throw new ReportException(e);
        }
    }

    @Override
    public void deleteReport(String file) {
        if (file.startsWith(prefix)){
            file = filePath.substring(prefix.length(), file.length());
        }

        String fullPath = filePath + "/" + file;
        File f = new File(fullPath);
        if (f.exists()) {
            f.delete();
        }
    }

    @Override
    public List<ReportFile> getReportFiles() {
        File file = new File(filePath);
        ArrayList<ReportFile> list = new ArrayList<>();

        for (File f : file.listFiles()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(f.lastModified());
            list.add(new ReportFile(f.getName(), calendar.getTime()));
        }
        Collections.sort(list, (f1, f2) -> f2.getUpdateDate().compareTo(f1.getUpdateDate()));
        return list;
    }

    @Override
    public void saveReport(String file, String content) {
        if (file.startsWith(prefix)){
            file = filePath.substring(prefix.length(), file.length());
        }

        String fullPath = filePath + "/" + file;

        try (FileOutputStream outputStream = new FileOutputStream(new File(fullPath))){
            IOUtils.write(content, outputStream, "utf-8");
        } catch (Exception ex) {
            throw new ReportException(ex);
        }
    }

    @Override
    public String getName() {
        return "UreportSystem";
    }

    @Override
    public boolean disabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }
}