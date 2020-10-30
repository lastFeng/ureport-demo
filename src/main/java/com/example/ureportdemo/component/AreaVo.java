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
package com.example.ureportdemo.component;

import com.example.ureportdemo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/10/30 15:01
 */
@Component("areaVo")
public class AreaVo {

    @Autowired
    private AreaService areaService;

    public List<Map> loadReportData(String dsName, String datasetName, Map<String, Object> params) {
        String key = "name";
        List<String> names = areaService.selectAreaName();

        List<Map> list = new ArrayList<>();
        for (String name : names) {
            HashMap<String, String> map = new HashMap<>();
            map.put(key, name);
            list.add(map);
        }
        return list;
    }

    public List<Map> buildReport(String dsName, String datasetName, Map<String, Object> params){
        String groupName = params.get("name") == null ? "东苑" : params.get("name").toString();

        return areaService.selectAreaByAreaName();
    }
}