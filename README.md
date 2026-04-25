# Automation Assessment — Amazon.eg

Selenium + TestNG + Allure automation framework for Amazon.eg covering login, cart, and access restriction scenarios.

---

## Prerequisites

Make sure the following are installed before running the tests:

| Tool | Version | Download |
|---|---|---|
| Java JDK | 11 or higher | https://adoptium.net |
| Maven | 3.8 or higher | https://maven.apache.org/download.cgi |
| Git | Any | https://git-scm.com/download/win |
| Microsoft Edge | Latest | https://www.microsoft.com/en-us/edge |
| Allure CLI | 2.x | https://docs.qameta.io/allure/#_installing_a_commandline |

Verify your setup by running:

```bash
java -version
mvn -version
allure --version
```

---

## Project Structure

```
src/
├── main/java/org/example/
│   ├── drivers/
│   │   ├── BrowserFactory.java       # Browser configuration (Chrome, Firefox, Edge)
│   │   └── DriverManager.java        # ThreadLocal WebDriver management
│   ├── listeners/
│   │   └── BaseListeners.java        # TestNG listeners — screenshots, logs, Allure
│   ├── pages/
│   │   ├── HomePage.java
│   │   ├── LoginPage.java
│   │   ├── TodaysDealsPage.java
│   │   ├── ProductPage.java
│   │   ├── ProductCheckPage.java
│   │   └── CartPage.java
│   └── utils/
│       ├── AllureUtils.java          # Allure attachment helpers
│       ├── BrowserActions.java       # Navigation, refresh, quit
│       ├── CustomSoftAssertion.java  # Soft assertion wrapper
│       ├── ElementsActions.java      # Click, sendKeys, getText, hover
│       ├── FileUtils.java            # File and directory cleanup
│       ├── LogsUtil.java             # Log4j2 logger wrapper
│       ├── ScreenshotsUtils.java     # Screenshot capture
│       ├── Scrolling.java            # JavaScript scroll helpers
│       ├── Validations.java          # Assert wrappers
│       └── Waits.java                # Explicit wait helpers
└── test/java/tests/
    ├── BaseTest.java                 # @BeforeClass/@AfterClass — driver setup/teardown
    ├── Scenario1.java                # Login with unregistered email
    ├── Scenario2.java                # Add product to cart and validate
    ├── Scenario3.java                # Access restriction without login
    └── FailedScenario.java           # Login with registered email (negative test)
```

---

## Clone the Repository

```bash
git clone https://github.com/marwanayman318/AssetAutomationAssessment.git
cd AssetAutomationAssessment
```

---

## Running the Tests

### Run all tests

```bash
mvn clean test
```

### Run a specific test class

```bash
mvn clean test -Dtest=Scenario1
mvn clean test -Dtest=Scenario2
mvn clean test -Dtest=Scenario3
```

### Run a specific test method

```bash
mvn clean test -Dtest=Scenario2#verifyCartDetails
```

### Run with a specific browser

The default browser is Edge. To switch browsers pass the `browser` system property:

```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Run using the TestNG XML suite

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## Test Scenarios

### Scenario 1 — Login with unregistered email
Verifies that a user who is not registered sees the correct message after entering their email.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Navigate to Amazon.eg | Home page loads |
| 2 | Click Sign In | Login page opens |
| 3 | Enter unregistered email | — |
| 4 | Click Continue | "Looks like you're new to Amazon" message appears |

### Scenario 2 — Add product to cart
Verifies that a product added from Today's Deals appears correctly in the cart.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Navigate to Today's Deals | Deals page loads |
| 2 | Click second category | Category filtered |
| 3 | Click first product | Product page loads |
| 4 | Validate name and price on product page | Name and price match |
| 5 | Select quantity = 2, add to cart | "Added to Cart" confirmation shown |
| 6 | Go to basket | Cart page loads |
| 7 | Validate name, price, quantity, subtotal in cart | All values match |

### Scenario 3 — Access restriction without login
Verifies that protected pages redirect to Sign In, while Your Lists remains accessible.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Hover Account & Lists → Your Orders | Redirected to Sign In page |
| 2 | Navigate back | Home page |
| 3 | Hover Account & Lists → Your Addresses | Redirected to Sign In page |
| 4 | Navigate back | Home page |
| 5 | Hover Account & Lists → Your Lists | Redirected to Lists page (accessible without login) |

---

## Generating the Allure Report

Allure results are written to `test-outputs/allure-results/` automatically after each run.

### Step 1 — Run the tests

```bash
mvn clean test
```

### Step 2 — Generate and open the report

```bash
allure serve test-outputs/allure-results
```

This generates the report and opens it in your default browser automatically.

### Alternative — Generate a static report to a folder

```bash
allure generate test-outputs/allure-results --clean -o test-outputs/allure-report
allure open test-outputs/allure-report
```

---

## Screenshots

Screenshots are captured automatically after every test regardless of result:

| Result | File name pattern | Location |
|---|---|---|
| Passed | `passed-{testName}.png` | `test-outputs/screenshots/` |
| Failed | `failed-{testName}.png` | `test-outputs/screenshots/` |
| Skipped | `skipped-{testName}.png` | `test-outputs/screenshots/` |

Screenshots are also attached directly to the Allure report under each test step.

---

## Logs

Log4j2 logs are written per test run to:

```
test-outputs/Logs/
```

Logs are also attached to each test entry in the Allure report for easy debugging.

---

## Output Directory Structure

After a test run the following directories are populated:

```
test-outputs/
├── allure-results/     # Raw Allure result files (input to allure serve)
├── allure-report/      # Generated HTML report (only if you run allure generate)
├── screenshots/        # PNG screenshots per test
└── Logs/               # Log4j2 log files
```

All output directories are cleaned automatically at the start of each new test run.

---

## Troubleshooting

**`git` is not recognized**
Git is not on your PATH. Open Git Bash instead of PowerShell, or add `C:\Program Files\Git\bin` to your system PATH.

**`allure` is not recognized**
Allure CLI is not installed or not on your PATH. Download it from https://docs.qameta.io/allure and add its `bin/` folder to your PATH.

**`NoSuchElementException` on hover menus**
Amazon's dropdown menus render asynchronously after hover. This is handled by explicit `waitUntilVisible` calls after each hover action. If it still occurs, increase the wait timeout in `Waits.java` from 10 to 15 seconds.

**Tests run on the wrong browser**
Check Windows Credential Manager for cached GitHub credentials and remove any stale entries. For the browser, pass `-Dbrowser=edge` explicitly to the Maven command.

**Wrong Git account on commits**
Set your identity locally for this project:
```bash
git config user.name "Your Name"
git config user.email "your@email.com"
```
