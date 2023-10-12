package com.epam.ta.stepdefinition;

import com.epam.ta.factory.WebDriverFactory;
import com.epam.ta.pageobjects.CommunitiesPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;

public class StepDefinitions {

    @Autowired
    CommunitiesPage communitiesPage;

    @Autowired
    WebDriverFactory webDriverFactory;

    @Given("the {string} site is opened")
    public void thePageIsOpened(String pageName) {
        WebDriver driver = webDriverFactory.getDriver();
        switch (pageName) {
            case "Communities" -> driver.get("https://wearecommunity.io/communities");
            case "Main" -> driver.get("https://wearecommunity.io");
        }
    }

    @When("I search for {string}")
    public void iSearchFor(String input) {
        communitiesPage.searchForInput(input);
    }

}
