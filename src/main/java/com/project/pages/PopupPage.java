package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopupPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String parentHandle;

    private final By popupBtn = By.id("popupBtn");
    private final By parentHeading = By.id("pageHeading");
    private final By popupHeading = By.id("popupHeading");
    private final By popupCloseBtn = By.id("popupCloseBtn");

    public PopupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.parentHandle = driver.getWindowHandle();
    }

    public void openPopup() {
        driver.findElement(popupBtn).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public void switchToPopup() {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                return;
            }
        }
        throw new IllegalStateException("Popup window handle not found");
    }

    public void switchToParent() {
        driver.switchTo().window(parentHandle);
    }

    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }

    public String getPopupTitle() {
        return driver.getTitle();
    }

    public String getPopupHeading() {
        return driver.findElement(popupHeading).getText();
    }

    public void closePopup() {
        driver.findElement(popupCloseBtn).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(1));
    }

    public String getParentHeading() {
        return driver.findElement(parentHeading).getText();
    }
}