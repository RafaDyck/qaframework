package com.qarepo;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class TestNGRunner {
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suites = new ArrayList<String>();
		suites.add("config-test.xml");
		testng.setTestSuites(suites);
		testng.run();
	}
}
