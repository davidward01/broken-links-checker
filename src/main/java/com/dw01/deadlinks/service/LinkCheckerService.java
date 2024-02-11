package com.dw01.deadlinks.service;

import com.dw01.deadlinks.model.LinkStatus;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinkCheckerService {

    public List<LinkStatus> checkLinks(String input) throws IOException {
        List<String> urls = new ArrayList<>();

        if (UrlValidator.getInstance().isValid(input)) {
            urls.add(input); // Single URL input
        } else {
            Document doc = Jsoup.parse(input);
            Elements links = doc.select("a[href]");
            links.forEach(link -> urls.add(link.attr("abs:href")));
        }

        List<LinkStatus> results = new ArrayList<>();
        for (String url : urls) {
            results.add(checkLinkStatus(url));
        }
        return results;
    }

    private LinkStatus checkLinkStatus(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();
            String message = connection.getResponseMessage();
            return new LinkStatus(url, responseCode, message);
        } catch (Exception e) {
            return new LinkStatus(url, 0, "Invalid URL or connection failed");
        }
    }
}