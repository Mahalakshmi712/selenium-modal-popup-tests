package com.project.tests;

import com.project.pages.AlertPage;
import com.project.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertTest extends BaseTest {

    private AlertPage alertPage;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        alertPage = new AlertPage(driver);
    }

    @Test(description = "TC06: Simple alert - read text and accept")
    public void tc06_simpleAlertAccept() {
        alertPage.clickSimpleAlert();
        Assert.assertEquals(alertPage.getAlertText(), "This is a simple alert", "Alert text mismatch");
        alertPage.acceptAlert();
        Assert.assertEquals(alertPage.getAlertResult(), "Alert was shown", "Result text mismatch after accepting alert");
    }

    @Test(description = "TC07: Confirm box - accept")
    public void tc07_confirmAccept() {
        alertPage.clickConfirm();
        Assert.assertEquals(alertPage.getAlertText(), "Do you want to continue?", "Confirm text mismatch");
        alertPage.acceptAlert();
        Assert.assertEquals(alertPage.getConfirmResult(), "You pressed OK", "Result mismatch after accepting confirm");
    }

    @Test(description = "TC08: Confirm box - dismiss")
    public void tc08_confirmDismiss() {
        alertPage.clickConfirm();
        alertPage.dismissAlert();
        Assert.assertEquals(alertPage.getConfirmResult(), "You pressed Cancel", "Result mismatch after dismissing confirm");
    }

    @Test(description = "TC09: Prompt box - enter text and accept")
    public void tc09_promptAccept() {
        alertPage.clickPrompt();
        alertPage.typeInPrompt("London");
        alertPage.acceptAlert();
        Assert.assertEquals(alertPage.getPromptResult(), "City: London", "Result mismatch after prompt input");
    }

    @Test(description = "TC10: Prompt box - dismiss")
    public void tc10_promptDismiss() {
        alertPage.clickPrompt();
        alertPage.dismissAlert();
        Assert.assertEquals(alertPage.getPromptResult(), "Prompt cancelled", "Result mismatch after dismissing prompt");
    }
}