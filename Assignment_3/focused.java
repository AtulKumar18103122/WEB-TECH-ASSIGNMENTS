package com.company;
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter;
public class Main {
    private static final List<String> faculty_Words= new LinkedList<>();
    public static boolean link_related(String link) {
        for(String s : faculty_Words)
        {
            if(link.contains(s) ) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        ArrayList < String > urlList = new ArrayList<>();
        Set < String > urlSet = new HashSet<>();
        String url = "https://pec.ac.in/";
        urlList.add(url);
        urlSet.add(url);
        String currUrl;
        faculty_Words.addAll(Arrays.asList(("faculty research programmes campus administration members committee institute department centres professor prof assistant teacher teaching engineer doctor scholar chairman qualification director phd ").split(" ")));

        CSVWriter csvwriterUrl = new CSVWriter(new FileWriter(new File("faculty_urls.csv")));
        CSVWriter csvwriterTag = new CSVWriter(new FileWriter(new File("faculty_text.csv")));
        String[] headerUrl = { "TextForURL", "URL" };
        String[] headerTag = { "TypeOfTag", "TextEnclosed" };
        csvwriterUrl.writeNext(headerUrl);
        csvwriterTag.writeNext(headerTag);

        for (int i = 0; i < urlList.size(); i++) {
            try {
                currUrl = urlList.get(i);
                Document document = Jsoup.connect(currUrl).get();
                String title = document.title();
                if (currUrl.contains("faculty")) {
                    csvwriterTag.writeNext(new String[] {});
                    csvwriterTag.writeNext(new String[] { "url: " + (i + 1), currUrl });
                    csvwriterTag.writeNext(new String[] { "titleTag", title });
                }
                Elements links = document.select("a[href]");
                for (Element link: links) {
                    String pageurl, urlText;
                    pageurl = link.absUrl("href");
                    if(!pageurl.contains("http")) {
                        pageurl = "https://pec.ac.in" + pageurl;
                    }
                    urlText = link.text();
                    if ((!urlSet.contains(pageurl)) && urlText.length() > 0) {
                        if (link_related(pageurl)) {

                            if (currUrl.contains("faculty")) {
                                csvwriterUrl.writeNext(new String[] {});
                                csvwriterUrl.writeNext(new String[]{urlText, pageurl});
                            }
                            urlSet.add(pageurl);
                            urlList.add(pageurl);
                        }
                    }
                }
                if (currUrl.contains("faculty")) {
                    Elements paragraphTag = document.select("p");
                    for (Element p: paragraphTag) {
                        String text;
                        text = p.text();
                        if (text.length() > 2) {
                            csvwriterTag.writeNext(new String[] { "p", text });
                        }
                    }

                    Elements h1Tag = document.select("h1");
                    for (Element h: h1Tag) {
                        String text;
                        text = h.text();
                        if (text.length() > 0) {
                            csvwriterTag.writeNext(new String[] { "h1", text });
                        }
                    }
                    System.out.println("URL: " + currUrl);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        csvwriterTag.close();
        csvwriterUrl.close();
    }
}