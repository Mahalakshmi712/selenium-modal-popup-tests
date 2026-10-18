package com.project.tests;

import com.project.pages.PopupPage;
import com.project.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PopupTest extends BaseTest {

    private PopupPage popupPage;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        popupPage = new PopupPage(driver);
    }

    @Test(description = "TC11: Pop-up opens and its content is verified")
    public void tc11_popupOpensAndContentVerified() {
        popupPage.openPopup();
        Assert.assertEquals(popupPage.getWindowCount(), 2, "Two windows should be open after clicking the popup button");
        popupPage.switchToPopup();
        Assert.assertEquals(popupPage.getPopupTitle(), "Popup Window", "Popup title mismatch");
        Assert.assertEquals(popupPage.getPopupHeading(), "Welcome to the Popup", "Popup heading mismatch");
    }

    @Test(description = "TC12: Close pop-up and return to the parent window")
    public void tc12_closePopupAndReturnToParent() {
        popupPage.openPopup();
        popupPage.switchToPopup();
        popupPage.closePopup();
        popupPage.switchToParent();
        Assert.assertEquals(popupPage.getWindowCount(), 1, "Only the parent window should remain open");
        Assert.assertEquals(popupPage.getParentHeading(), "Modal and Popup Test App", "Parent heading mismatch after returning");
    }
}