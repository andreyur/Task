package com.upworktest;

import com.pages.MainPage;
import com.pages.SearchResultsPage;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchResultsTest {
    WebDriver driver;
    private String keyword = "Java";
    @BeforeTest
    public void setUp() {
        // Initialization WebDriver and deleting cookies
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void testSearchResults() throws InterruptedException {
        System.out.println("Test Log: The test is started");
        // opening the MainPage
        MainPage mp = new MainPage(driver).open();
        System.out.println("Test Log: the https://www.upwork.com is opened");
        // searching freelancers with keyword and return a new SearchResultsPage
        SearchResultsPage sp = mp.search(keyword);
        // Apply filter IndependentFreelancers to search results
        sp.selectIndependentFreelancers();
        // getting freelancers info and store it in freelancers object
        sp.getFreelancerInfo(keyword);
        //clicking on random freelancer and checking a freelancer info
        sp.cickRandomFreelancerAndCheckInfo(keyword);
        System.out.println("Test Log: The test is finished");
    }
    @AfterTest
    public void tearDown(){
        //Close the browser
        driver.quit();
    }
}
