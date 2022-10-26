package com.kontarook.sequenceofnumbers.service.impl;

import com.kontarook.sequenceofnumbers.service.NumbersService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;

@Service
public class NumbersServiceImpl implements NumbersService {

    private String filePath;

    private List<Integer> listOfNumbersFromFile;

    // returns serializable copy of listOfNumbersFromFile
    @Cacheable("numbersFromFile")
    public List<Integer> getListOfNumbersFromFile() {
        return new ArrayList<>(listOfNumbersFromFile);
    }

    // parses numbers from file and updates cache
    @Override
    @CacheEvict(value = "numbersFromFile", allEntries = true)
    public void parseNumbersFromFile() throws NoSuchFileException {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }

        } catch (IOException | NullPointerException e) {
            throw new NoSuchFileException("File wasn't found");
        }
        this.listOfNumbersFromFile = numbers;
    }

    // returns min value from list
    @Override
    @Cacheable("minValue")
    public Integer getMinValue() {
        return Collections.min(getListOfNumbersFromFile());
    }

    // returns max value from list
    @Override
    @Cacheable("maxValue")
    public Integer getMaxValue() {
        return Collections.max(getListOfNumbersFromFile());
    }

    // returns median value from list
    @Override
    @Cacheable("medianNumber")
    public Double getMedianNumber() {
        List<Integer> numbers = getListOfNumbersFromFile();

        if (numbers.size() == 0)
            return 0.;
        if (numbers.size() == 1)
            return (double) numbers.get(0);

        Collections.sort(numbers);

        if (numbers.size() % 2 == 0) {
            return (double) (numbers.get(numbers.size() / 2)
                    + numbers.get(numbers.size() / 2 - 1)) / 2;
        } else {
            return (double) numbers.get(numbers.size() / 2);
        }
    }

    // returns the longest ascending sequence of numbers from list. uses default method from NumberService interface
    @Override
    @Cacheable("longestSequenceAsc")
    public List<List<Integer>> longestSequenceAsc() {
        return longestSequence(getListOfNumbersFromFile()); // default interface method
    }

    // returns the longest descending sequence of numbers from list. uses default method from NumberService interface
    @Override
    @Cacheable("longestSequenceDesc")
    public List<List<Integer>> longestSequenceDesc() {
        List<Integer> numbers = getListOfNumbersFromFile();
        Collections.reverse(numbers);
        List<List<Integer>> resultList = longestSequence(numbers); // default interface method
        for (List list : resultList) {
            Collections.reverse(list);
        }
        return resultList;
    }

    // returns average value from list
    @Override
    @Cacheable("average")
    public Double getAverage() {
        OptionalDouble average = getListOfNumbersFromFile()
                .stream()
                .mapToDouble(a -> a)
                .average();

        return average.isPresent() ? average.getAsDouble() : 0;
    }

    // sets filePath + name of the file was uploaded
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}