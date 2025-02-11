package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.TreeMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    TreeMap<String, TimeSeries> word_YearCount;
    TreeMap<Integer, Long> yearTotalCount;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        word_YearCount = new TreeMap<>();
        yearTotalCount = new TreeMap<>();
        In wordsIn = new In(wordsFilename);
        In countsIn = new In(countsFilename);
        while (wordsIn.hasNextLine()) {
            String nextlineForWords = wordsIn.readLine();
            String[] splitLine = nextlineForWords.split("\t");
            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            double count = Double.parseDouble(splitLine[2]);
            TimeSeries ts = word_YearCount.getOrDefault(word, new TimeSeries());
            ts.put(year, count);
            word_YearCount.put(word, ts);
        }

        while (countsIn.hasNextLine()) {
            String nextlineForCounts = countsIn.readLine();
            String[] splitline = nextlineForCounts.split(",");
            int year = Integer.parseInt(splitline[0]);
            long totalCount = Long.parseLong(splitline[1]);
            yearTotalCount.put(year, totalCount);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries();
        if (!word_YearCount.containsKey(word)) return ts;
        TimeSeries tmp = word_YearCount.get(word);
        for (int year: tmp.keySet()) {
            if (year >= startYear && year <= endYear) {
                ts.put(year, tmp.get(year));
            }
        }
        return ts;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries();
        if (!word_YearCount.containsKey(word)) return ts;
        TimeSeries tmp = word_YearCount.get(word);
        for (int year: tmp.keySet()) {
                ts.put(year, tmp.get(year));
        }
        return ts;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries();
        for (int key: this.yearTotalCount.keySet()) {
            ts.put(key, Double.valueOf(this.yearTotalCount.get(key)));
        }
        return ts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return calculateWeightHistory(word, startYear, endYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    private TimeSeries calculateWeightHistory(String word, int startYear, int endYear) {
        TimeSeries ts = new TimeSeries();
        if (!this.word_YearCount.containsKey(word)) return ts;
        TimeSeries tmp = word_YearCount.get(word);
        for (int year : tmp.keySet()) {
            if (year >= startYear && year <= endYear) {
                ts.put(year, tmp.get(year) / yearTotalCount.get(year));
            }
        }
        return ts;
    }

    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return calculateWeightHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        return summedWeightHistoryHelper(words, startYear, endYear);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return summedWeightHistoryHelper(words, MIN_YEAR, MAX_YEAR);
    }

    private TimeSeries summedWeightHistoryHelper(Collection<String> words, int startYear, int endYear) {
        TimeSeries ts = new TimeSeries();
        for (String word: words) {
            TimeSeries wordT = weightHistory(word, startYear, endYear);
            for (int year: wordT.keySet()) {
                if (ts.containsKey(year)) ts.put(year, ts.get(year) + wordT.get(year));
                else ts.put(year, wordT.get(year));
            }
        } return ts;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
