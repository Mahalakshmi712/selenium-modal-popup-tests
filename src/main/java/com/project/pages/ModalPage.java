package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By openModalBtn = By.id("openModalBtn");
    private final By modal = By.id("myModal");
    private final By modalTitle = By.id("modalTitle");
    private final By nameInput = By.id("modalName");
    private final By submitBtn = By.id("modalSubmitBtn");
    private final By cancelBtn = By.id("modalCloseBtn");
    private final By closeXBtn = By.id("modalCloseX");
    private final By errorMessage = By.id("modalError");
    private final By resultText = By.id("modalResult");

    public ModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openModal() {
        driver.findElement(openModalBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(modal));
    }

    public boolean isModalDisplayed() {
        return driver.findElement(modal).isDisplayed();
    }

    public String getModalTitle() {
        return driver.findElement(modalTitle).getText();
    }

    public void enterName(String name) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    public void clickCancel() {
        driver.findElement(cancelBtn).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modal));
    }

    public void clickClose() {
        driver.findElement(closeXBtn).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modal));
    }

    public void waitForModalToClose() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modal));
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public String getResultText() {
        return driver.findElement(resultText).getText();
    }
}