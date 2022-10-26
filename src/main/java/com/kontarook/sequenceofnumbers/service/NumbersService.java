package com.kontarook.sequenceofnumbers.service;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public interface NumbersService {

    void parseNumbersFromFile() throws NoSuchFileException;

    Integer getMinValue();

    Integer getMaxValue();

    Double getMedianNumber();

    default List<List<Integer>> longestSequence(List<Integer> numbers) {

        List<List<Integer>> subsequences = new ArrayList<>();

        List<int[]> indexes = new ArrayList<>();

        int maxCount = 0;
        int anchor = 0;

        for (int i = 0; i < numbers.size(); i++) {
            if (i > 0 && numbers.get(i - 1) >= numbers.get(i)) {
                anchor = i;
            }

            if (i - anchor + 1 > maxCount) {
                indexes.clear();
                indexes.add(new int[]{anchor, i});
                maxCount = Math.max(maxCount, i - anchor + 1);

            } else if (i - anchor + 1 == maxCount) {
                indexes.add(new int[]{anchor, i});
            }
        }
        for (int[] index : indexes) {
            subsequences.add(new ArrayList<>(numbers.subList(index[0], index[1] + 1)));
        }
        return subsequences;
    }

    List<List<Integer>> longestSequenceAsc();

    List<List<Integer>> longestSequenceDesc();

    Double getAverage();
}
