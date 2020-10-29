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
package com.example.ureportdemo.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.example.ureportdemo.entity.MyDataModel;
import com.example.ureportdemo.service.ReportBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
 * @create: 2020/10/27 9:45
 */
@RestController
public class ReportManagerController {

    @Autowired
    private ReportBuildService reportBuildService;

    @GetMapping("/getUreport")
    public String getReport() {
        String docxName = String.valueOf(System.currentTimeMillis());
        reportBuildService.buildWordDocx(docxName, null);

        return "success!";
    }

    @GetMapping("/getPoi")
    public String getPoi() throws Exception {

        RowRenderData header = new RowRenderData(
            Arrays.asList(new TextRenderData("FFFFFF", "Word处理解决方案"),
                new TextRenderData("FFFFFFF", "是否跨平台"),
                new TextRenderData("FFFFFFF", "易用性")),
            "ff980");
        RowRenderData row0 = RowRenderData.build("Poi-tl", "纯Java组件，跨平台", "简单：模板引擎功能，并对POI进行了一些封装");
        RowRenderData row1 = RowRenderData.build("Apache Poi", "纯Java组件，跨平台", "简单，缺少一些功能的封装");
        RowRenderData row2 = RowRenderData.build("Freemarker", "XML操作，跨平台", "复杂，需要理解XML结构");
        RowRenderData row3 = RowRenderData.build("OpenOffice", "需要安装OpenOffice软件",
            "复杂，需要了解OpenOffice的API");
        RowRenderData row4 = RowRenderData.build("Jacob、winlib", "Windows平台", "复杂，不推荐使用");
        List<RowRenderData> tableDatas = Arrays.asList(row0, row1, row2, row3, row4);

        Map<String, Object> datas = new HashMap<String, Object>(){
            {
                put("header", "Hello");
                put("name", "POI-TL");
                put("word", "模板引擎");
                put("time", new Date());
                put("what", "Java Word模板引擎： Minimal Microsoft word(docx) templating with {{template}} in Java. It works by expanding tags in a template using values provided in a JavaMap or JavaObject.");
                put("compare", new MiniTableRenderData(header, tableDatas,
                    MiniTableRenderData.WIDTH_A4_FULL));

                put("feature", new NumbericRenderData(new ArrayList<TextRenderData>() {
                    {
                        add(new TextRenderData("Plug-in grammar, add new grammar by yourself"));
                        add(new TextRenderData(
                            "Supports word text, local pictures, web pictures, table, list, header, footer..."));
                        add(new TextRenderData(
                            "Templates, not just templates, but also style templates"));
                    }
                }));
            }
        };

        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/template/template.docx").render(datas);
        FileOutputStream out = new FileOutputStream("out_template.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
        return "Success";
    }

    @GetMapping("/getPoiObj")
    public String getPoiObj() throws Exception {
        RowRenderData header = new RowRenderData(
            Arrays.asList(new TextRenderData("FFFFFF", "Word处理解决方案"),
                new TextRenderData("FFFFFFF", "是否跨平台"),
                new TextRenderData("FFFFFFF", "易用性")),
            "ff980");
        RowRenderData row0 = RowRenderData.build("Poi-tl", "纯Java组件，跨平台", "简单：模板引擎功能，并对POI进行了一些封装");
        RowRenderData row1 = RowRenderData.build("Apache Poi", "纯Java组件，跨平台", "简单，缺少一些功能的封装");
        RowRenderData row2 = RowRenderData.build("Freemarker", "XML操作，跨平台", "复杂，需要理解XML结构");
        RowRenderData row3 = RowRenderData.build("OpenOffice", "需要安装OpenOffice软件",
            "复杂，需要了解OpenOffice的API");
        RowRenderData row4 = RowRenderData.build("Jacob、winlib", "Windows平台", "复杂，不推荐使用");
        List<RowRenderData> tableDatas = Arrays.asList(row0, row1, row2, row3, row4);
        MyDataModel obj = new MyDataModel();
        obj.setHeader("Deeply love what you love.");
        obj.setName("Poi-tl");
        obj.setWord("模板引擎");
        obj.setTime("2019-05-31");
        obj.setWhat(
            "Java Word模板引擎： Minimal Microsoft word(docx) templating with {{template}} in Java. It works by expanding tags in a template using values provided in a JavaMap or JavaObject.");
        obj.setCompare(new MiniTableRenderData(header, tableDatas));
        obj.setFeature(new NumbericRenderData(new ArrayList<TextRenderData>() {
            {
                add(new TextRenderData("Plug-in grammar, add new grammar by yourself"));
                add(new TextRenderData(
                    "Supports word text, local pictures, web pictures, table, list, header, footer..."));
                add(new TextRenderData("Templates, not just templates, but also style templates"));
            }
        }));

        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/template/template.docx")
            .render(obj);
        FileOutputStream out = new FileOutputStream("out_template_obj.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();

        return "Success!";
    }
}