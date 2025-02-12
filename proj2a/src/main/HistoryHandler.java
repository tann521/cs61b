package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Utils.TOP_14337_WORDS_FILE;
import static utils.Utils.TOTAL_COUNTS_FILE;

public class HistoryHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        NGramMap ngm = new NGramMap(TOP_14337_WORDS_FILE, TOTAL_COUNTS_FILE);
        String[] wordsArray = q.words().get(0).split("\\s+");
        List<String> words = Arrays.asList(wordsArray);
        ArrayList<TimeSeries> lts = new ArrayList<>();
        for (String word: q.words()) {
            words.add(word);
            lts.add(ngm.weightHistory(word, q.startYear(), q.endYear()));
        }
        XYChart chart = Plotter.generateTimeSeriesChart(words, lts);
        String s = Plotter.encodeChartAsString(chart);
        return s;
    }
}
