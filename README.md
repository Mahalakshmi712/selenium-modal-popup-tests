# Selenium Tests for Modal Dialogs and Pop-up Windows

Automated Selenium WebDriver tests for the three kinds of "pop-up" behaviour found in web applications, built with Maven and TestNG and run automatically by a Jenkins job that pulls the code from GitHub.

## Problem Statement

Modal dialogs, JavaScript alerts and pop-up windows interrupt the normal flow of a page, and each one needs a different Selenium technique to handle correctly:

| Mechanism | What it is | How Selenium handles it |
|-----------|------------|-------------------------|
| Modal dialog | In-page overlay built with HTML, CSS and JavaScript | Normal locators plus explicit waits for visibility |
| JavaScript alert, confirm, prompt | Browser-level dialog, not part of the DOM | `driver.switchTo().alert()` |
| Pop-up window | A second browser window | Window handles with `driver.switchTo().window(...)` |

This project tests all three against a local test page and runs the tests in a CI pipeline.

## Tools

Java 17, Maven 3.9, Selenium WebDriver 4.25, TestNG 7.10, Google Chrome, Git and GitHub, Jenkins 2.580.

## Project Structure

```
selenium-modal-popup-tests/
├── pom.xml                      Maven build: dependencies and Surefire configuration
├── testng.xml                   TestNG suite (which test classes run)
├── src/main/java/com/project/pages/
│   ├── ModalPage.java           Page object: modal dialog
│   ├── AlertPage.java           Page object: alert, confirm, prompt
│   └── PopupPage.java           Page object: pop-up window and window handles
├── src/test/java/com/project/
│   ├── tests/                   SmokeTest, ModalTest, AlertTest, PopupTest
│   └── utils/                   BaseTest (browser lifecycle), ScreenshotListener
├── src/test/resources/testapp.html   Local test application
├── docs/test-cases.md           Test cases and test data (TC01 to TC12)
├── reports/                     Saved test reports
└── screenshots/                 Failure screenshots
```

The tests follow the Page Object Model: locators and actions live in the page classes, and the tests only describe behaviour.

## Test Cases

Twelve test cases plus one smoke test (13 tests in total). The full table with steps, test data and expected results is in [docs/test-cases.md](docs/test-cases.md).

- **TC01 to TC05:** modal opens, valid submit, empty submit, Cancel, close with X
- **TC06 to TC10:** simple alert, confirm accept and dismiss, prompt accept and dismiss
- **TC11 to TC12:** pop-up opens and its content is verified, pop-up closes and focus returns to the parent window

A TestNG listener saves a screenshot to `screenshots/` automatically whenever a test fails.

## How to Run Locally

Requirements: JDK 17, Maven 3.8 or higher, Google Chrome. Selenium Manager downloads the matching ChromeDriver automatically.

```bat
git clone https://github.com/Mahalakshmi712/selenium-modal-popup-tests.git
cd selenium-modal-popup-tests

mvn clean test
```

Run without opening a browser window (used by Jenkins):

```bat
mvn clean test -Dheadless=true
```

Reports are written to `target/surefire-reports/`. Open `emailable-report.html` for a readable summary.

## Jenkins Pipeline

A Freestyle job named `selenium-modal-popup-tests` does the following on every build:

1. Clones this repository (branch `main`) from GitHub.
2. Runs `mvn clean test -Dheadless=true` with JDK 17 and Maven 3.9.
3. Publishes the JUnit XML results from `target/surefire-reports/*.xml` (test result page and trend graph).
4. Archives `target/surefire-reports/**` and `screenshots/*.png` with the build.

Jenkins tools configured: JDK 17 (`jdk17`) and Maven 3.9.16 (`maven-3.9.16`).

## Results

All 13 tests pass locally and in Jenkins (builds #4 and #5: 13 passed, 0 failed, 0 skipped). A deliberate failure of TC02 was used to verify that failures are reported and that a screenshot is captured; the evidence is in `reports/failure-demo-emailable-report.html` and `screenshots/`.