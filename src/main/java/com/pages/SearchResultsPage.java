package com.pages;

import com.data.Freelancer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class SearchResultsPage {
    private WebDriver driver;
    // We use this ArrayList for getting quantity of freelancers on the search page
    private ArrayList<WebElement> freelancersInfo;
    // We use this ArrayList to store freelancer objects with info about each freelancer
    public ArrayList<Freelancer> freelancers;
    // It's a constructor for creating a new SearchResultsPage
    public SearchResultsPage(WebDriver driver) {
        this.driver=driver;
    }
    // This method for adding a filter IndependentFreelancers on a SearchResultsPage
    public void selectIndependentFreelancers() throws InterruptedException {
// Waiting for appearing filters button
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement filtersButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.filters-button")));
        filtersButton.click();
        WebElement independentFreelancersOption = driver.findElement(By.xpath("//form/div[2]/div[3]/div/facet-input-radio-list/div/div[2]/label"));
        independentFreelancersOption.click();
        filtersButton.click();
        System.out.println("Test Log: Apply filter IndependentFreelancers to search results");
    }
    // This method for getting freelancers info and store it in freelancers object
    public void getFreelancerInfo(String keyword) throws InterruptedException {
// Waiting for appearing article element (the freelancers info is placed in this element)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement filtersButton = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("article")));
// getting quantity of freelancers on the search page
        freelancersInfo = (ArrayList<WebElement>) driver.findElements(By.tagName("article"));
        System.out.println("Test Log: Freelancers quantity "+freelancersInfo.size());
        freelancers = new ArrayList<Freelancer>();
// in this loop we getting freelancers info and store it in freelancer object
        for (int i=1;i<freelancersInfo.size()+1;i++) {
// getting a freelancer name
            WebElement freelancerTileName = driver.findElement(By.xpath("//section//section["+i+"]//article//a[@class='freelancer-tile-name']"));
            String tileName = freelancerTileName.getText();
            System.out.println("Test Log: "+i+"Freelancer name is " + tileName);
// getting a freelancer title
            WebElement freelancerTileTitle = driver.findElement(By.xpath("//section//section["+i+"]//article//h4[@data-qa='tile_title']"));
            String tileTitle = freelancerTileTitle.getText();
//checking if title contains keyword
            if (tileTitle.contains(keyword)) {
                System.out.println("Test Log: The title contains " + keyword + " in " + tileTitle);
            } else {
                System.out.println("Test Log: The title doesn't contain " + keyword + " in " + tileTitle);
            }
// getting a freelancer rate
            WebElement freelancerRate = driver.findElement(By.xpath("//section//section["+i+"]//article//div[@data-freelancer-rate]"));
            String rate = freelancerRate.getText();
            String[] rates = rate.split("\\r?\\n");
            rate = rates[0];
            System.out.println("Test Log: "+i+"Freelancer rate is " + rate);
// getting a freelancer earnings
            WebElement freelancerEarnings = driver.findElement(By.xpath("//section//section["+i+"]//article//div[@data-freelancer-earnings]"));
            String earnings = freelancerEarnings.getText();
            String[] earning = earnings.split(" ");
            earnings = earning[0];
            System.out.println("Test Log: "+i+"Freelancer earnings is " + earnings);
// getting a freelancer score if it's present in freelancer info
            String score="";
            if (isElementPresent(By.xpath("//section//section["+i+"]//article//div[@data-freelancer-job-success-score]"))) {
                WebElement freelancerScore = driver.findElement(By.xpath("//section//section["+i+"]//article//div[@data-freelancer-job-success-score]"));
                score = freelancerScore.getText();
                String[] scores = score.split(" ");
                score = scores[0];
                System.out.println("Test Log: "+i+"Freelancer score is " + score);
            }
// getting a freelancer location
            WebElement freelancerLocation = driver.findElement(By.xpath("//section//section["+i+"]//article//div[@data-freelancer-location]"));
            String location = freelancerLocation.getText();
            System.out.println("Test Log: "+i+"Freelancer location is " + location);
// getting a freelancer description
            WebElement freelancerTileDescription = driver.findElement(By.xpath("//section//section["+i+"]//article//p"));
            String tileDescription = freelancerTileDescription.getText();
//checking if description contains keyword
            if (tileDescription.contains(keyword)) {
                System.out.println("Test Log: The description contains " + keyword + " in " + tileDescription);
            } else {
                System.out.println("Test Log: The description doesn't contain " + keyword + " in " + tileDescription);
            }
// getting a freelancer specialization if it's present in freelancer info
            String tileSpec="";
            if (isElementPresent(By.xpath("//section//section["+i+"]//article//div[@data-freelancer-tile-specialization-line]/div"))) {
                WebElement freelancerSpec = driver.findElement(By.xpath("//section//section["+i+"]//article//div[@data-freelancer-tile-specialization-line]/div"));
                tileSpec = freelancerSpec.getText();
//checking if specialization contains keyword
                if (tileSpec.contains(keyword)) {
                    System.out.println("Test Log: The specialization contains " + keyword + " in " + tileSpec);
                } else {
                    System.out.println("Test Log: The specialization doesn't contain " + keyword + " in " + tileSpec);
                }
            }
// getting freelancer skills
            ArrayList<WebElement> skills = (ArrayList<WebElement>) driver.findElements(By.xpath("//section//section["+i+"]//article//div[@data-ng-if='skills.length > 0']"));
            String tileSkills = "";
// adding all freelancer skills to the string
            for (int l = 0; l < skills.size(); l++) {
                WebElement freelancerSkills = skills.get(l);
                tileSkills = tileSkills + freelancerSkills.getText() + " ";

            }
//checking if skills contains keyword
            if (tileSkills.contains(keyword)) {
                System.out.println("Test Log: The skills contains " + keyword + " in " + tileSkills);
            } else {
                System.out.println("Test Log: The skills doesn't contain " + keyword + " in " + tileSkills);
            }
// creating a new freelancer object and store freelancer info in it
            Freelancer freelancer = new Freelancer(tileName, tileTitle, rate, earnings, score, location, tileDescription, tileSpec, tileSkills);
// adding a freelancer object to ArrayList freelancers
            freelancers.add(freelancer);
        }

    }
    // This method for checking if an element is present on the page
    public Boolean isElementPresent(By locator){
        Boolean isPresent = driver.findElements(locator).size() > 0;
        return isPresent;
    }
    // This method for clicking on random freelancer and checking a freelancer info
    public void cickRandomFreelancerAndCheckInfo(String keyword) throws InterruptedException {
// Click on random freelancer
        freelancersInfo = (ArrayList<WebElement>) driver.findElements(By.tagName("article"));
        int i=(int) (Math.random()*freelancersInfo.size());
        freelancersInfo.get(i).click();
        System.out.println("Test Log: Random "+i+"Freelancer is clicked");
// getting a freelancer object with the random index i
        Freelancer freelancer = freelancers.get(i);

// Waiting for appearing freelancers info on the page and getting freelancers name
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement freelancerName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2/span[@itemprop='name']")));
        String name = freelancerName.getText();
// checking if freelancers name is the same as we stored
        if(name.equals(freelancer.getName())) {
            System.out.println("Test Log: Freelancers name " + name+" is the same as we stored "+freelancer.getName());
        }else {
            System.out.println("Test Log: Freelancers name " + name+" is not the same as we stored "+freelancer.getName());
        }

// Waiting for appearing freelancers title on the page and getting freelancers tittle
        wait = new WebDriverWait(driver, 10);
        WebElement freelancerTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3//span[@data-ng-bind-html]")));
        String title = freelancerTitle.getText();
// checking if freelancers title is the same as we stored
        if(title.equals(freelancer.getTitle())) {
            System.out.println("Test Log: Freelancers title " + title+" is the same as we stored "+freelancer.getTitle());
        }else{
            System.out.println("Test Log: Freelancers title " + title+" is not the same as we stored "+freelancer.getTitle());
        }
//checking if title contains keyword
        if (title.contains(keyword)) {
            System.out.println("Test Log: Freelancers title contains " + keyword + " in " + title);
        } else {
            System.out.println("Test Log: Freelancers title doesn't contain " + keyword + " in " + title);
        }

// Waiting for appearing freelancers rate on the page and getting freelancers rate
        wait = new WebDriverWait(driver, 10);
        WebElement freelancerRate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3//span/span[@itemprop='pricerange']")));
        String rate = freelancerRate.getText();
// checking if freelancers rate is the same as we stored
        if(rate.equals(freelancer.getRate())) {
            System.out.println("Test Log: Freelancers rate " + rate+" is the same as we stored "+freelancer.getRate());
        }else{
            System.out.println("Test Log: Freelancers rate " + rate+" is not the same as we stored "+freelancer.getRate());
        }
// Waiting for appearing freelancers earnings on the page and getting freelancers earnings
        wait = new WebDriverWait(driver, 10);
        WebElement freelancerEarnings = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3/span[@itemprop='pricerange']")));
        String earnings = freelancerEarnings.getText();
// checking if freelancers earnings is the same as we stored
        if(earnings.equals(freelancer.getEarnings())) {
            System.out.println("Test Log: Freelancers earnings " + earnings+" is the same as we stored "+freelancer.getEarnings());
        }else{
            System.out.println("Test Log: Freelancers earnings " + earnings+" is not the same as we stored "+freelancer.getEarnings());
        }

// getting a freelancer score if it's present on the page
        String score="";
        if(isElementPresent(By.xpath("//cfe-job-success//div[@data-ng-if]//h3"))) {
            WebElement freelancerScore = driver.findElement(By.xpath("//html/body//fe-profile/div[2]/div[2]/div[1]/div[1]/fe-profile-header/div/div[2]/div[1]/div[2]/cfe-job-success/div[1]/div/h3"));
            score = freelancerScore.getText();
        }
// checking if freelancers score is the same as we stored
        if(score.equals(freelancer.getScore())){
            System.out.println("Test Log: Freelancer score " + score+" is the same as we stored "+freelancer.getScore());
        }else{
            System.out.println("Test Log: Freelancer score " + score+" is not the same as we stored "+freelancer.getScore());
        }
// getting freelancers location
        WebElement freelancerLocation = driver.findElement(By.xpath("//fe-profile-location-label//span[@class='w-700']//span[@itemprop='country-name']"));
        String location = freelancerLocation.getText();
// checking if freelancers location is the same as we stored
        if(location.equals(freelancer.getLocation())) {
            System.out.println("Test Log: Freelancer location " + location+" is the same as we stored "+freelancer.getLocation());
        }else{
            System.out.println("Test Log: Freelancer location " + location+" is not the same as we stored "+freelancer.getLocation());
        }
// getting freelancers description
        WebElement freelancerTileDescription = driver.findElement(By.xpath("//o-profile-overview//p"));
        String tileDescription = freelancerTileDescription.getText();
// checking if freelancers description is the same as we stored
        if(tileDescription.equals(freelancer.getDescription())) {
            System.out.println("Test Log: Freelancers description " + tileDescription+" is the same as we stored "+freelancer.getDescription());
        }else{
            System.out.println("Test Log: Freelancers description " + tileDescription+" is not the same as we stored "+freelancer.getDescription());
        }
//checking if description contains keyword
        if (tileDescription.contains(keyword)) {
            System.out.println("Test Log: Freelancers description contains " + keyword + " in " + tileDescription);
        } else {
            System.out.println("Test Log: Freelancers description doesn't contain " + keyword + " in " + tileDescription);
        }
// getting a freelancer specialization if it's present on the page
        if(isElementPresent(By.xpath("//cfe-profile-selector"))) {
            WebElement freelancerSpec = driver.findElement(By.xpath("//cfe-profile-selector//button[2]"));
            String tileSpec = freelancerSpec.getText();
// checking if freelancers specialization is the same as we stored
            if(tileSpec.equals(freelancer.getSpecialization())) {
                System.out.println("Test Log: Freelancers tileSpec " + tileSpec+" is the same as we stored "+freelancer.getSpecialization());
            }else{
                System.out.println("Test Log: Freelancers tileSpec " + tileSpec+" is the same as we stored "+freelancer.getSpecialization());
            }
//checking if specialization contains keyword
            if (tileSpec.contains(keyword)) {
                System.out.println("Test Log: Freelancers specialization contains " + keyword + " in " + tileSpec);
            } else {
                System.out.println("Test Log: Freelancers specialization doesn't contain " + keyword + " in " + tileSpec);
            }
        }


    }
}
