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
package com.example.ureportdemo.bean;

import com.example.ureportdemo.entity.JavaBeanEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/10/26 16:17
 * 用法：数据源中添加：SpringBean来源
 *      添加数据集：自定义数据集名+ 下方的方法名 + 返回的内容（如果设置了POJO，则添加全限定名）
 */
@Component("myJavaBean")
public class MyJavaBean {
    public List<JavaBeanEntity> loadReportData(String dsName, String datasetName, Map<String,Object> parameters){
        JavaBeanEntity javaBeanEntity = new JavaBeanEntity();
        javaBeanEntity.setName(parameters.getOrDefault("name", "hello").toString());
        javaBeanEntity.setAge(Integer.valueOf(parameters.getOrDefault("age", 1).toString()));

        ArrayList<JavaBeanEntity> result = new ArrayList<>();
        result.add(javaBeanEntity);
        // 这边可以查询数据库内容
        return result;
    }
    public List<JavaBeanEntity> buildReport(String dsName, String datasetName, Map<String,Object> parameters){
        return loadReportData(dsName, datasetName, parameters);
    }
}