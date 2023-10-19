package com.epam.ta.stepdefinition;

import com.epam.ta.factory.WebDriverFactory;
import com.epam.ta.pageobjects.CommunitiesPage;
import com.epam.ta.pageobjects.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import static com.epam.ta.helpers.Addresses.COMMUNITIES_PAGE;
import static com.epam.ta.helpers.Addresses.MAIN_PAGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class StepDefinitions {

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
    public void iSearchFor(String input) {
        communitiesPage.searchForInput(input);
    }

    @Then("I see {int} community card")
    public void iSeeCommunityCard(int expectedCardCount) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            var actualCardCount = communitiesPage.getCommunityCards().size();
            if (actualCardCount == expectedCardCount) {
                return;
            }
            Thread.sleep(1000);
        }
        Assert.fail("Expected card count did not match actual card count");
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
