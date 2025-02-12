package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

import java.util.Arrays;
import java.util.List;

import static utils.Utils.*;

public class HistoryTextHandler extends NgordnetQueryHandler {
    String response;
    NGramMap map;

    public HistoryTextHandler() {
        this.map = new NGramMap(TOP_14337_WORDS_FILE, TOTAL_COUNTS_FILE);
    }
    private String handle(NgordnetQuery q, NGramMap map) {
        String[] wordsArray = q.words().get(0).split("\\s+");
        List<String> words = Arrays.asList(wordsArray);
        int startYear = q.startYear();
        int endYear = q.endYear();
        response = "";
        System.out.println(q.words());
        for (String word: words) {
            response = response + word + ": " + map.weightHistory(word, startYear, endYear).toString() + "\n";
        }
        return response;
    }

    @Override
    public String handle(NgordnetQuery q) {
        return handle(q, map);
    }
}
