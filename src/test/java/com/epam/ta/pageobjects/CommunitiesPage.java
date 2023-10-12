package com.epam.ta.pageobjects;

import com.epam.ta.factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CommunitiesPage {

    @FindBy(css = ".evnt-search-filter .form-control.evnt-search")
    private static WebElement searchInput;

    private WebDriver webDriver;

    private WebDriverFactory webDriverFactory;
    public CommunitiesPage(WebDriverFactory webDriverFactory) {
        var webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void searchForInput(String input) {
        searchInput.sendKeys(input);
    }
}
