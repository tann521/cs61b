package main;

import browser.NgordnetQuery;
import ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler {
    String response;
    public String handle(NgordnetQuery q, NGramMap map) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        response = "";
        for (String word: words) {
            response += word + ": " + map.countHistory(word, startYear, endYear) + "\n";
        }
        return response;
    }

}
