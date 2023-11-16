package com.epam.ta.stepdefinition;

import com.epam.ta.factory.WebDriverFactory;
import com.epam.ta.pageobjects.CommunitiesPage;
import com.epam.ta.pageobjects.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.netty.handler.timeout.TimeoutException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.NoSuchElementException;

import static com.epam.ta.helpers.Addresses.COMMUNITIES_PAGE;
import static com.epam.ta.helpers.Addresses.MAIN_PAGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class StepDefinitions {

    private static final Duration TIMEOUT_SECONDS = Duration.ofSeconds(3);
    private static final Duration POLLING_TIMEOUT_SECONDS = Duration.ofSeconds(1);

    @Autowired
    CommunitiesPage communitiesPage;

    @Autowired
    MainPage mainPage;

    @Autowired
    WebDriverFactory webDriverFactory;

    @Given("the {string} site is opened")
    public void thePageIsOpened(String pageName) {
        WebDriver driver = webDriverFactory.getDriver();
        switch (pageName) {
            case "Communities" -> driver.get(COMMUNITIES_PAGE);
            case "Main" -> driver.get(MAIN_PAGE);
        }
    }

    @When("I search for {string}")
    public void iSearchFor(String input) throws InterruptedException {
        Thread.sleep(500);
        communitiesPage.searchForInput(input);
    }

    @Then("I see {int} community card")
    public void iSeeCommunityCard(int expectedCardCount) throws InterruptedException {
        WebDriver driver = webDriverFactory.getDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(TIMEOUT_SECONDS)
            .pollingEvery(POLLING_TIMEOUT_SECONDS)
            .ignoring(NoSuchElementException.class);

        try {
            wait.until(
                (ExpectedCondition<Boolean>) driver1 -> communitiesPage.getCommunityCards().size() ==
                    expectedCardCount);
        } catch (TimeoutException e) {
            Assert.fail("Expected card count did not match actual card count.");
        }
    }

    @Given("the {string} button is clicked")
    public void theCommunitiesButtonIsClicked(String buttonName) {
        switch (buttonName) {
            case "Communities" -> mainPage.getCommunitiesButton().click();
        }
    }

    @And("All cards contain the {string} word")
    public void allCardsContainTheCardNameWord(String word) {
        for (String title : communitiesPage.getCommunityCardTitles()) {
            assertThat(title, containsString(word));
        }
    }

    @When("I narrow the location to {string}")
    public void iNarrowTheLocationToBudapest(String locationName) {
        communitiesPage.getLocationFilter().click();
        communitiesPage.getLocationFilterInput().sendKeys(locationName);
    }

    @And("I click the highlited checkbox")
    public void iClickTheBudapestCheckbox() {
        communitiesPage.getLocationFilterHighlightedItem().click();
    }
}
