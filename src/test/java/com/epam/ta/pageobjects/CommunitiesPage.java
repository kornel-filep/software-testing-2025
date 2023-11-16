package com.epam.ta.pageobjects;

import com.epam.ta.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommunitiesPage {

    @FindBy(css = ".evnt-search-filter .form-control.evnt-search")
    private WebElement searchInput;

    @FindBy(id = "filter_location")
    private WebElement locationFilter;

    @FindBy(css = ".evnt-filter-menu.show .evnt-search")
    private WebElement locationFilterInput;

    @FindBy(css = ".highlight-text")
    private WebElement locationFilterHighlightedItem;

    @FindBy(css = ".evnt-community-card")
    private List<WebElement> communityCards;

    @FindBy(css = ".evnt-name-wrapper")
    private List<WebElement> communityCardTitles;

    private final WebDriver webDriver;

    public CommunitiesPage(WebDriverFactory webDriverFactory) {
        this.webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void searchForInput(String input) {
        searchInput.sendKeys(input);
    }

    public List<WebElement> getCommunityCards() {
        return communityCards;
    }

    public List<String> getCommunityCardTitles() {
        return communityCardTitles.stream()
                .map(WebElement::getText)
                .toList();
    }

    public WebElement getLocationFilter() {
        return locationFilter;
    }

    public WebElement getLocationFilterInput() {
        return locationFilterInput;
    }

    public WebElement getLocationFilterHighlightedItem() {
        return locationFilterHighlightedItem;
    }
}
