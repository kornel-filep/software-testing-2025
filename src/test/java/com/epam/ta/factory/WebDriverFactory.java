package com.epam.ta.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.Objects;

public class WebDriverFactory {

    @Value("${headless:false}")
    private Boolean headless;

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
            new ChromeOptions().setHeadless(headless).addArguments("--remote-allow-origins=*")
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }

}
