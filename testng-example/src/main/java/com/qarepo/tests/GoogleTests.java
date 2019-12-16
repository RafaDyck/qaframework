package com.qarepo.tests;

import com.qarepo.testdata.DataSupplier;
import com.qarepo.testdata.GoogleSearchResult;
import com.qarepo.webactions.GoogleActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GoogleTests {

    @Test(groups = "search", description = "Perform a search and validate that results appear")
    public void searchReturnsResults() {
        GoogleActions ga = new GoogleActions();
        ga.search("youtube");
        List<GoogleSearchResult> results = ga.getList_GoogleSearchResults();
        Assert.assertTrue(results.size() > 0);
    }

    @Test(groups = "search", description = "Perform a search and validate that first result is expected")
    public void searchReturnsExpectedResult() {
        GoogleActions ga = new GoogleActions();
        ga.search("youtube");
        List<GoogleSearchResult> results = ga.getList_GoogleSearchResults();
        Assert.assertTrue(results.get(0).getTitle().contains("YouTube"));
        Assert.assertTrue(results.get(0).getURL().contains("https://www.youtube.com"));
        Assert.assertTrue(results.get(0).getDescription().contains("YouTube"));
    }

    /**
     * Test shows how to pass a DataProvider to run same test with multiple test data values
     * Missing validation because SEO changes page rankings so there is no true Expected Result
     * When results are expected the results.forEach would be replaced with Asserts that compare
     * test data objects(I.E. GoogleSearchResult) with expected results
     * @param testData DataProvider to pass multiple test values to 1 test.
     */
    @Test(groups = "search",
            description = "Perform multiple searches and extract results for multiple pages.",
            dataProvider = "searchTerms",
            dataProviderClass = DataSupplier.class)
    public void searchReturnsMultiplePages(String... testData) {
        GoogleActions ga = new GoogleActions();
        ga.search(testData[0]);
        List<GoogleSearchResult> results = ga.getList_GoogleSearchResults();
        for (int i = 0; i < 3; i++) {
            ga.click_NextPage();
            results.addAll(ga.getList_GoogleSearchResults());
        }
        results.forEach(e -> System.out.println(e.toString()));
    }

    /**
     * Same as searchReturnsMultiplePages(String... testData) but with data source which returns maps for test data
     * @param testData DataProvider to pass multiple test values to 1 test.
     */
    @Test(groups = "search",
            description = "Perform multiple searches and extract results for multiple pages.",
            dataProvider = "searchTermsExternalSource",
            dataProviderClass = DataSupplier.class)
    public void searchReturnsMultiplePagesWithExternalTestData(String... testData) {
        GoogleActions ga = new GoogleActions();
        ga.search(testData[0]);
        List<GoogleSearchResult> results = ga.getList_GoogleSearchResults();
        for (int i = 0; i < 3; i++) {
            ga.click_NextPage();
            results.addAll(ga.getList_GoogleSearchResults());
        }
        results.forEach(e -> System.out.println(e.toString()));
    }
}
