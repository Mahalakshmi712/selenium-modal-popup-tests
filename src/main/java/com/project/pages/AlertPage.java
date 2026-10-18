package com.project.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By alertBtn = By.id("alertBtn");
    private final By confirmBtn = By.id("confirmBtn");
    private final By promptBtn = By.id("promptBtn");
    private final By alertResult = By.id("alertResult");
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click methods only click: the page is blocked while an alert is open.
    public void clickSimpleAlert() {
        driver.findElement(alertBtn).click();
    }

    public void clickConfirm() {
        driver.findElement(confirmBtn).click();
    }

    public void clickPrompt() {
        driver.findElement(promptBtn).click();
    }

    private Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText() {
        return waitForAlert().getText();
    }

    public void acceptAlert() {
        waitForAlert().accept();
    }

    public void dismissAlert() {
        waitForAlert().dismiss();
    }

    public void typeInPrompt(String text) {
        waitForAlert().sendKeys(text);
    }

    public String getAlertResult() {
        return driver.findElement(alertResult).getText();
    }

    public String getConfirmResult() {
        return driver.findElement(confirmResult).getText();
    }

    public String getPromptResult() {
        return driver.findElement(promptResult).getText();
    }
}