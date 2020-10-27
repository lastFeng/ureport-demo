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
package com.example.ureportdemo.service;

import com.bstek.ureport.export.ExportConfigure;
import com.bstek.ureport.export.ExportConfigureImpl;
import com.bstek.ureport.export.ExportManager;
import com.example.ureportdemo.entity.UreportFileEntity;
import com.example.ureportdemo.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/10/27 9:25
 */
@Service
public class ReportBuildService {

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private ExportManager exportManager;

    public void buildWordDocx(String docxFileName, Map<String, Object> parameters){
        UreportFileEntity ureportFileEntity = reportMapper.queryReportFileList().get(0);
        String file = ureportFileEntity.getName();
        byte[] content = ureportFileEntity.getContent();
        OutputStream fileOutput = null;

        try {
            //file = new String(file.getBytes("UTF-8"), "ISO8859-1");
            File f = new File(file);
            fileOutput = new FileOutputStream(f);
            fileOutput.write(content);
            fileOutput.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutput != null) {
                try {
                    fileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        BufferedOutputStream outputStream = null;

        try {
            docxFileName = new String(docxFileName.getBytes("UTF-8"), "ISO8859-1");
            File f = new File(docxFileName + ".docx");
            outputStream = new BufferedOutputStream(new FileOutputStream(f));
            ExportConfigure configure = new ExportConfigureImpl(file, parameters, outputStream);
            exportManager.exportWord(configure);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}