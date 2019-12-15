package com.qarepo.testdata;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataSupplier {
    private static Logger logger = LogManager.getLogger(DataSupplier.class);

    /**
     * Extremely simplified DataProvider example. Typically tests data would be provided from an external source such
     * as a csv, xls, or DB. That data is then parsed and returned in methods with @DataProvider
     * @return DataProvider that will execute the same test with different test data
     */
    @DataProvider(name = "searchTerms")
    public Object[][] multipleSearchTerms() {
        return new Object[][]{
                new Object[]{"youtube"},
                new Object[]{"google drive"},
                new Object[]{"google cloud"}
        };
    }

    @DataProvider(name = "searchTermsExternalSource")
    public Object[][] multipleSearchTermsFromExternalSource() {
        List<Map<String, String>> testDataFromSource = csvToMap("./src/main/resources/test-data/search-terms.csv");
        Object[][] testDataToTest = new Object[testDataFromSource.size()][];
        for (int i = 0; i < testDataFromSource.size(); i++) {
            List<String> keySet = new ArrayList<>(testDataFromSource.get(i).keySet());
            for (int j = 0; j < keySet.size(); j++) {
                testDataToTest[i] = new String[]{testDataFromSource.get(i).get(keySet.get(j))};
            }
        }
        return testDataToTest;
    }

    public List<Map<String, String>> csvToMap(String fileName) {
        List<Map<String, String>> testDataList = new ArrayList<>();
        try (CSVParser csvParser = new CSVParser(new BufferedReader(new FileReader(new File(fileName))), CSVFormat.DEFAULT)) {
            List<CSVRecord> list = csvParser.getRecords();
            for (int i = 1; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    Map<String, String> tempMap = new HashMap<>();
                    tempMap.put(list.get(0).get(j), list.get(i).get(j));
                    testDataList.add(tempMap);
                }
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File error " + e.toString());
        }
        return testDataList;
    }
}
