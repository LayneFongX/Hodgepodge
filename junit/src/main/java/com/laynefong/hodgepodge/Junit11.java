package com.laynefong.hodgepodge;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

public class Junit11 {

    public static void main(String[] args) throws IOException {
        String text = "这里是第一行 这里是第二行 这里是有序序号： 有序1 有序2 有序3 这里是无序序号： 无序1 无序2 这里是表格： 列1 列2 值1 值2 这里是图片： 这里是加粗字体 ok,到此结束";
        System.out.println(text);

        String html =
                "<div><div style=\"\" class=\"tag-paragraph\"><div style=\"\" class=\"tag-paragraph\"><div style=\"\" class=\"tag-paragraph\"><span>这里是第一行</span></div><div style=\"\" class=\"tag-paragraph\"><span>这里是第二行</span></div><div style=\"\" class=\"tag-paragraph\"><span>这里是有序序号：</span></div><ol><li><span>有序1</span></li><li><span>有序2</span></li><li><span>有序3</span></li></ol><div style=\"\" class=\"tag-paragraph\"><span>这里是无序序号：</span></div><ul><li><span>无序1</span></li><li><span>无序2</span></li></ul><div style=\"\" class=\"tag-paragraph\"><span>这里是表格：</span></div><div style=\"\" class=\"tag-paragraph\"><span></span></div><table style=\"border-spacing: 0px;word-break: break-word;border-collapse:collapse;white-space: pre;\"><thead><tr><th style=\"width:80px;height:38px;;background-color:#f4f4f4;\"><div style=\"margin:0;min-height: 0;white-space:normal;\" class=\"tag-paragraph\"><span>列1</span></div></th><th style=\"width:80px;height:38px;;background-color:#f4f4f4;\"><div style=\"margin:0;min-height: 0;white-space:normal;\" class=\"tag-paragraph\"><span>列2</span></div></th></tr></thead><tbody><tr><td style=\"width:80px;height:38px;\"><div style=\"margin:0;min-height: 0;white-space:normal;\" class=\"tag-paragraph\"><span>值1</span></div></td><td style=\"width:80px;height:38px;\"><div style=\"margin:0;min-height: 0;white-space:normal;\" class=\"tag-paragraph\"><span>值2</span></div></td></tr></tbody></table><div style=\"\" class=\"tag-paragraph\"><span>这里是图片：</span></div><div class=\"image-align\"><img src=\"https://airtake-public-data-1254153901.cos.ap-shanghai.myqcloud.com/goat/bay1617161624805e6w0/164663745505e459f1525.png\"></div><div style=\"\" class=\"tag-paragraph\"><span><strong>这里是加粗字体</strong></span></div><div style=\"\" class=\"tag-paragraph\"><span>ok,到此结束</span></div></div></div></div>";

        String htmlText = StripHT(html);
        System.out.println(htmlText);
        System.out.println(Objects.equals(text, htmlText));
    }


    public static String StripHT(String strHtml) {
        Document document = Jsoup.parse(strHtml);
        document.outputSettings().prettyPrint(false);
        Element body = document.body();
        body.select("div.doc-title").remove();
        // 图片相对路径添加host
        appenHost2PicRelativeUrl(document, "https//");
        return document.text();
    }

    private static void appenHost2PicRelativeUrl(Document document, String bucketUrl) {
        Element body = document.body();
        org.jsoup.select.Elements elements = body.children();
        appendRecursive(elements, bucketUrl);
    }

    private static void appendRecursive(Elements elements, String bucketUrl) {
        if (elements == null) {
            return;
        }

        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            String tagName = element.tag().toString();

            if ("img".equals(tagName)) {
                Attributes attributes = element.attributes();
                attributes.forEach(item -> {
                    // 图片img链接改为相对路径
                    if ("src".equals(item.getKey())) {
                        String url = item.getValue();
                        item.setValue(bucketUrl + url);
                    }
                });
            }
            appendRecursive(element.children(), bucketUrl);
        }
    }


}
