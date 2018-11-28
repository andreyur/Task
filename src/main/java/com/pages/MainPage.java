package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;

    // It's a constructor for creating a new MainPage

    public MainPage(WebDriver driver) {
        this.driver=driver;
    }

// This method for opening the MainPage

    public MainPage open(){
        driver.get("https://www.upwork.com/");
        driver.manage().window().maximize();
        return this;
    }

// This method for searching freelancers with keyword and return a new SearchResultsPage

    public SearchResultsPage search(String text) throws InterruptedException {
        WebElement inputSearchField = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/nav/div/div[2]/div[1]/form/div[3]/input[2]"));
        String option = inputSearchField.getAttribute("placeholder");
// Checking, if placeholder text of the input equals "Find Job" - switch the option for finding freelancers
        if(option.equals("Find Jobs")){
            WebElement optionButton = driver.findElement(By.xpath("//html/body/div[1]/div[2]/div/nav/div/div[2]/div[1]/form/div[3]/div/button[2]/span[1]"));
            optionButton.click();
// Waiting for appearing freelancers option in drop down
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement optionFreelancer = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Freelancers")));
            optionFreelancer.click();
        }
        inputSearchField.sendKeys(text);
        inputSearchField.submit();
        System.out.println("Test Log: Search freelancers with keyword "+text);
        return new SearchResultsPage(driver);
    }
}
