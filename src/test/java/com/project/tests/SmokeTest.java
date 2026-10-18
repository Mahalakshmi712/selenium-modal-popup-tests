package com.project.tests;

import com.project.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void testPageLoads() {
        Assert.assertEquals(driver.getTitle(), "Modal and Popup Test App");
    }
}