package com.epam.ta.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.Objects;

public class WebDriverFactory {

    @Value("${headless:false}")
    private Boolean headless;

    @Value("${width:1920}")
    private int width;

    @Value("${height:1080}")
    private int height;

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
        driver.manage().window().setSize(new Dimension(width, height));
        return driver;
    }

    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }

}
