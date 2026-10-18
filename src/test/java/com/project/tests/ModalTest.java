package com.project.tests;

import com.project.pages.ModalPage;
import com.project.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModalTest extends BaseTest {

    private ModalPage modalPage;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        modalPage = new ModalPage(driver);
    }

    @Test(description = "TC01: Modal opens on button click")
    public void tc01_modalOpensOnClick() {
        modalPage.openModal();
        Assert.assertTrue(modalPage.isModalDisplayed(), "Modal should be visible after clicking Open Modal");
        Assert.assertEquals(modalPage.getModalTitle(), "Enter Your Name", "Modal title mismatch");
    }

    @Test(description = "TC02: Submit modal with a valid name")
    public void tc02_submitValidName() {
        modalPage.openModal();
        modalPage.enterName("Alice");
        modalPage.clickSubmit();
        modalPage.waitForModalToClose();
        Assert.assertEquals(modalPage.getResultText(), "Hello, Alice", "Result text mismatch after valid submit");
    }

    @Test(description = "TC03: Submit modal with an empty name")
    public void tc03_submitEmptyName() {
        modalPage.openModal();
        modalPage.enterName("");
        modalPage.clickSubmit();
        Assert.assertEquals(modalPage.getErrorMessage(), "Name is required", "Validation message mismatch");
        Assert.assertTrue(modalPage.isModalDisplayed(), "Modal should stay open after invalid submit");
    }

    @Test(description = "TC04: Close modal using Cancel")
    public void tc04_closeWithCancel() {
        modalPage.openModal();
        modalPage.clickCancel();
        Assert.assertFalse(modalPage.isModalDisplayed(), "Modal should be closed after Cancel");
        Assert.assertEquals(modalPage.getResultText(), "", "No result text expected after Cancel");
    }

    @Test(description = "TC05: Close modal using the X button")
    public void tc05_closeWithX() {
        modalPage.openModal();
        modalPage.clickClose();
        Assert.assertFalse(modalPage.isModalDisplayed(), "Modal should be closed after clicking X");
        Assert.assertEquals(modalPage.getResultText(), "", "No result text expected after clicking X");
    }
}