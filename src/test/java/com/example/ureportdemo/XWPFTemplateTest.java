package com.example.ureportdemo;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.util.*;

/**
 * @author : guoweifeng
 * @date : 2020/10/26
 */
public class XWPFTemplateTest {
//    @Test
//    public void testRenderTemplateByMap() throws Exception {
//        // create table
//        RowRenderData header = Rows.of("Word处理方案", "是否跨平台", "易用性").textColor("FFFFFF").bgColor("ff9800").center()
//                .rowHeight(2.5f).create();
//        RowRenderData row0 = Rows.create("Poi-tl", "纯Java组件，跨平台", "简单：模板引擎功能，并对POI进行了一些封装");
//        RowRenderData row1 = Rows.create("Apache Poi", "纯Java组件，跨平台", "简单，缺少一些功能的封装");
//        RowRenderData row2 = Rows.create("Freemarker", "XML操作，跨平台", "复杂，需要理解XML结构");
//        RowRenderData row3 = Rows.create("OpenOffice", "需要安装OpenOffice软件", "复杂，需要了解OpenOffice的API");
//        RowRenderData row4 = Rows.create("Jacob、winlib", "Windows平台", "复杂，不推荐使用");
//        TableRenderData table = Tables.create(header, row0, row1, row2, row3, row4);
//
//        Map<String, Object> datas = new HashMap<String, Object>();
//        // text
//        datas.put("header", "Deeply love what you love.");
//        datas.put("name", "Poi-tl");
//        datas.put("word", "模板引擎");
//        datas.put("time", "2020-08-31");
//        datas.put("what",
//                "Java Word模板引擎： Minimal Microsoft word(docx) templating with {{template}} in Java. It works by expanding tags in a template using values provided in a JavaMap or JavaObject.");
//        datas.put("author", Texts.of("Sayi卅一").color("000000").create());
//
//        // hyperlink
//        datas.put("introduce", Texts.of("http://www.deepoove.com").link("http://www.deepoove.com").create());
//        // picture
//        datas.put("portrait", Pictures.ofLocal("src/test/resources/sayi.png").size(60, 60).create());
//        // table
//        datas.put("solution_compare", table);
//        // numbering
//        datas.put("feature",
//                Numberings.create("Plug-in grammar, add new grammar by yourself",
//                        "Supports word text, local pictures, web pictures, table, list, header, footer...",
//                        "Templates, not just templates, but also style templates"));
//
//        XWPFTemplate.compile("src/test/resources/template/template.docx").render(datas)
//                .writeToFile("out_template.docx");
//    }

    RowRenderData header;

    List<RowRenderData> tableDatas;

    @BeforeEach
    public void init() {
        header = new RowRenderData(
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
        tableDatas = Arrays.asList(row0, row1, row2, row3, row4);
    }

    @SuppressWarnings("serial")
    @Test
    public void testRenderMap() throws Exception {
        Map<String, Object> datas = new HashMap<String, Object>(){
            {
                put("header", "Hello");
                put("name", "POI-TL");
                put("word", "模板引擎");
                put("time", new Date());
                put("what", "Java Word模板引擎： Minimal Microsoft word(docx) templating with {{template}} in Java. It works by expanding tags in a template using values provided in a JavaMap or JavaObject.");
                put("campare", new MiniTableRenderData(header, tableDatas,
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
    }

    @SuppressWarnings("serial")
    @Test
    public void testRenderObject() throws Exception {
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
    }
}
