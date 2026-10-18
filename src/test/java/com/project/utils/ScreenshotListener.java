package com.project.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        if (!(instance instanceof BaseTest)) {
            return;
        }
        WebDriver driver = ((BaseTest) instance).driver;
        if (driver == null) {
            return;
        }

        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String fileName = result.getMethod().getMethodName() + "_" + timestamp + ".png";

            Path folder = Paths.get("screenshots");
            Files.createDirectories(folder);

            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path target = folder.resolve(fileName);
            Files.copy(source.toPath(), target, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved: " + target.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("Could not capture screenshot: " + e.getMessage());
        }
    }
}