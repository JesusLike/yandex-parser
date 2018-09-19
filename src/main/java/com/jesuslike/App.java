package com.jesuslike;

import java.io.*;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    public static void main(String[] args) throws IOException {
        new App().run();
    }

    void run() throws IOException {
        String url = "http://yandex.ru/yandsearch?text=%D0%9C%D0%B0%D0%B9%D1%81%D0%BA" +    //Can be easily reworked
                "%D0%B0%D1%8F+%D1%82%D1%80%D0%B0%D0%B2%D0%BA%D0%B0+" +                      //to get url from user input
                "%D0%B3%D0%BE%D0%BB%D0%BE%D0%B4%D0%BD%D0%BE%D0%B3%D0%BE+" +
                "%D0%BA%D0%BE%D1%80%D0%BC%D0%B8%D1%82&nl=1&lr=2";
        Document doc = Jsoup.connect(url).get();

        Elements items = doc.getElementsByClass("serp-item");

        File dir = new File("response" + new Date().getTime());
        dir.mkdir();

        int position = 0;
        for (Element item : items) {
            Element header = item.getElementsByClass("organic__url").get(0);
            String href = header.attr("href");
            String title = header.text();

            Element subtitleElement = item.getElementsByClass("organic__path").get(0);
            String subtitle = subtitleElement.text();

            String annotation;
            try {
                Element annotationElement = item.getElementsByClass("text-container").get(0);
                if (annotationElement.getElementsByClass("extended-text__short").size() > 0) {
                    annotationElement = annotationElement.getElementsByClass("extended-text__short").get(0);
                }
                annotation = annotationElement.text();
            } catch (IndexOutOfBoundsException e) {
                continue;
            }

            File responseFile = new File(dir + "\\" + ++position + ".txt");
            responseFile.createNewFile();
            PrintWriter out = new PrintWriter(responseFile);
            out.println("URL: " + href);
            out.println("Title: " + title);
            out.println("Subtitle: " + subtitle);
            out.println("Annotation: " + annotation);
            out.close();
            File responseDocument = new File(dir + "\\" + position + "-doc.html");
            out = new PrintWriter(responseDocument);
            out.println(Jsoup.connect(href).get().toString());
            out.close();
        }
    }
}
