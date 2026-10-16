# Test Cases and Test Data

**Application under test:** `src/test/resources/testapp.html` (local page with a modal dialog, JavaScript alerts and a pop-up window)
**Tools:** Selenium WebDriver 4, TestNG, Maven, Jenkins, GitHub
**Browser:** Google Chrome

**Precondition for all tests:** the test page is open in a fresh browser session.

## A. Modal Dialog

| ID | Test Case | Steps | Test Data | Expected Result |
|----|-----------|-------|-----------|-----------------|
| TC01 | Modal opens on button click | 1. Click "Open Modal" | None | Modal is visible and its title is "Enter Your Name" |
| TC02 | Submit modal with a valid name | 1. Open modal 2. Enter a name 3. Click "Submit" | Name = `Alice` | Modal closes and the page shows "Hello, Alice" |
| TC03 | Submit modal with an empty name | 1. Open modal 2. Leave name empty 3. Click "Submit" | Name = (empty) | Error "Name is required" is shown and the modal stays open |
| TC04 | Close modal using Cancel | 1. Open modal 2. Click "Cancel" | None | Modal closes and no result text is shown |
| TC05 | Close modal using the X button | 1. Open modal 2. Click "X" | None | Modal closes and no result text is shown |

## B. JavaScript Alerts

| ID | Test Case | Steps | Test Data | Expected Result |
|----|-----------|-------|-----------|-----------------|
| TC06 | Simple alert: read text and accept | 1. Click "Simple Alert" 2. Read alert text 3. Accept | None | Alert text is "This is a simple alert"; after accepting, the page shows "Alert was shown" |
| TC07 | Confirm box: accept | 1. Click "Confirm Box" 2. Accept | None | Alert text is "Do you want to continue?"; page shows "You pressed OK" |
| TC08 | Confirm box: dismiss | 1. Click "Confirm Box" 2. Dismiss | None | Page shows "You pressed Cancel" |
| TC09 | Prompt box: enter text and accept | 1. Click "Prompt Box" 2. Type text 3. Accept | Input = `London` | Page shows "City: London" |
| TC10 | Prompt box: dismiss | 1. Click "Prompt Box" 2. Dismiss | None | Page shows "Prompt cancelled" |

## C. Pop-up Window

| ID | Test Case | Steps | Test Data | Expected Result |
|----|-----------|-------|-----------|-----------------|
| TC11 | Pop-up opens and its content is verified | 1. Click "Open Popup Window" 2. Switch to the new window | None | Two windows are open; popup title is "Popup Window"; heading is "Welcome to the Popup" |
| TC12 | Close pop-up and return to the parent window | 1. Open popup 2. Switch to it 3. Click "Close Popup" 4. Switch back to the parent | None | One window remains open and the parent heading "Modal and Popup Test App" is visible |