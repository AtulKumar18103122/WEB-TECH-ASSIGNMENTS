package com.company;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter;
public class Main {
    static boolean getOccuringChar(String str) {
        int count = 0;
        for(int i=0; i < str.length(); ++i) {
            if(str.charAt(i)=='/') {
                if(count++ > 7) return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        ArrayList < String > urlList = new ArrayList<>();
        Set < String > urlSet = new HashSet<>();
        Set < String > pdfSet = new HashSet<>();
        String url = "https://pec.ac.in";
        urlList.add(url);
        urlSet.add(url);
        String currUrl;

        CSVWriter csvwriterPdf = new CSVWriter(new FileWriter(new File("pdf.csv")));
        String[] headerPdf = { "pdfURL" };
        csvwriterPdf.writeNext(headerPdf);

        for (int i = 0; i < urlList.size(); i++) {
            try {
                currUrl = urlList.get(i);
                Document document = Jsoup.connect(currUrl).get();
                Elements links = document.select("a[href]");
                for (Element link: links) {
                    String pageurl = link.absUrl("href");
                    if(!pageurl.contains("http")) {
                        pageurl = "https://pec.ac.in" + pageurl;
                    }
                    if ((!urlSet.contains(pageurl)) && pageurl.contains("https://pec.ac.in/") && getOccuringChar(pageurl)) {
                        if (pageurl.endsWith(".pdf") && !pdfSet.contains(pageurl)) {
                            csvwriterPdf.writeNext(new String[] { pageurl });
                            pdfSet.add(pageurl);
                        } else {
                            urlList.add(pageurl);
                            urlSet.add(pageurl);
                        }
                    }
                }
                Elements iframe = document.select("iframe");
                for (Element frame: iframe) {
                    String pageurl = frame.attr("data-src");

                    if (!urlSet.contains(pageurl) && pageurl.contains("https://pec.ac.in/") && pageurl.endsWith(".pdf") && !pdfSet.contains(pageurl) && getOccuringChar(pageurl)) {
                        csvwriterPdf.writeNext(new String[] { pageurl });
                        pdfSet.add(pageurl);
                    }
                }
                System.out.println("URL: " + currUrl);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        csvwriterPdf.close();
    }
}