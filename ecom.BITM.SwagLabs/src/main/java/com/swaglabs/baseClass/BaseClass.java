package com.swaglabs.baseClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

    protected WebDriver driver;

    // Pause after every click/typing, in milliseconds (3000 = 3 seconds).
    // Set to 0 to run at full speed again.
    private static final int STEP_DELAY_MS = 3000;

    // Pause before the browser closes so you can see the final page
    private static final int END_DELAY_MS = 3000;

    // true  = screenshot for PASSED tests too (negative tests pass, so they need this)
    // false = screenshot only for FAILED tests
    private static final boolean SCREENSHOT_ON_PASS = true;

    // Slows down every click and typing action
    private static class SlowListener implements WebDriverListener {

        @Override
        public void beforeClick(WebElement element) {
            pause(STEP_DELAY_MS);
        }

        @Override
        public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
            pause(STEP_DELAY_MS);
        }
    }

    private static void pause(int millis) {
        if (millis <= 0) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {

        WebDriver rawDriver;

        if (browser.equalsIgnoreCase("chrome")) {
        	final Map<String, Object> chromePrefs = new HashMap<>();
        	chromePrefs.put("credentials_enable_service", false);
        	chromePrefs.put("profile.password_manager_enabled", false);
        	chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

        	final ChromeOptions chromeOptions = new ChromeOptions();
        	chromeOptions.setExperimentalOption("prefs", chromePrefs);
        	
            rawDriver = new ChromeDriver(chromeOptions);

        } else if (browser.equalsIgnoreCase("firefox")) {
            rawDriver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            rawDriver = new EdgeDriver();

        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        // Wrap the driver so every click/typing has a delay
        driver = new EventFiringDecorator<WebDriver>(new SlowListener()).decorate(rawDriver);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (driver != null) {

            boolean failed = result.getStatus() == ITestResult.FAILURE;

            // Screenshot for failed tests, and for passed tests when SCREENSHOT_ON_PASS is true
            if (failed || SCREENSHOT_ON_PASS) {
                takeScreenshot(result, failed ? "FAIL" : "PASS");
            }

            pause(END_DELAY_MS);
            driver.quit();
        }
    }

    private void takeScreenshot(ITestResult result, String status) {
        try {
            String time = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            // Example: PASS_com.swaglabs.login.TS2_verifyInvalidLogin_20261008_113015.png
            String className = result.getTestClass().getName();
            String fileName = status + "_" + className + "_"
                    + result.getMethod().getMethodName() + "_" + time + ".png";

            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Path destination = Paths.get("screenshot", fileName);

            Files.createDirectories(destination.getParent());
            Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved: " + destination.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Could not save screenshot: " + e.getMessage());
        }
    }
}