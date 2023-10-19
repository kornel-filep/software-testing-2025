package com.epam.ta.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

public class WebDriverFactory {

    private WebDriver driver;

    public WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            driver = setUpWebDriver();
        }
        return driver;
    }

    private WebDriver setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(
            new ChromeOptions().addArguments("--remote-allow-origins=*")
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public void tearDown() {
        if(Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }

}
