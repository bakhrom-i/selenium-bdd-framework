# Selenium BDD Framework

> Production-ready **Selenium-Java** framework with **Cucumber BDD**, **Page Object Model**, parallel execution on **Selenium Grid**, and **Jenkins** CI pipeline.

[![Selenium BDD Tests](https://github.com/bakhrom-i/selenium-bdd-framework/actions/workflows/tests.yml/badge.svg)](https://github.com/bakhrom-i/selenium-bdd-framework/actions/workflows/tests.yml)
![Java](https://img.shields.io/badge/java-17-orange)
![Selenium](https://img.shields.io/badge/selenium-4.20-43B02A)
![Cucumber](https://img.shields.io/badge/cucumber-7-23D96C)
![License](https://img.shields.io/badge/license-MIT-blue)

**📊 Live test report:** https://bakhrom-i.github.io/selenium-bdd-framework/

> _Replace `bakhrom-i` with your GitHub username after pushing._

## Tech Stack

- **Language:** Java 17
- **Test Runner:** Cucumber 7 + JUnit 5
- **Browser Automation:** Selenium WebDriver 4
- **Build:** Maven
- **Reporting:** Cucumber HTML, Allure (published to GitHub Pages)
- **Grid:** Selenium Grid 4 (Hub + Nodes via Docker)
- **CI:** GitHub Actions (nightly + on push) and Jenkins (declarative pipeline)

## Features

- Gherkin feature files for readable specs
- Page Object Model for maintainable locators
- Cross-browser config (Chrome, Firefox, Edge) via WebDriverManager
- Parallel scenarios via JUnit 5 (`cucumber.execution.parallel.enabled=true`)
- Selenium Grid support — point `grid.url` at your hub
- Hooks for screenshots on failure
- **Nightly CI run** publishes Allure report to GitHub Pages

## What the tests do

By default the suite drives [the-internet.herokuapp.com/login](https://the-internet.herokuapp.com/login) — a public sandbox built specifically for automation testing. It exercises a real browser session against a real HTTP form: positive login with `tomsmith` / `SuperSecretPassword!`, plus three negative paths that assert an error flash banner appears.

Override the target with `-Dbase.url=https://your-app/login` to run the same scenarios against your own application.

## Project Structure

```
selenium-bdd-framework/
├── src/
│   ├── main/java/com/portfolio/bdd/
│   │   ├── pages/         # Page Object classes
│   │   ├── driver/        # WebDriver factory (local + Grid)
│   │   ├── utils/         # Helpers, waits, data
│   │   └── config/        # Config loading
│   └── test/
│       ├── java/com/portfolio/bdd/
│       │   ├── stepdefs/  # Step definitions
│       │   ├── hooks/     # @Before / @After hooks
│       │   └── runners/   # Cucumber runners
│       └── resources/features/
├── .github/workflows/tests.yml   # GitHub Actions
├── .jenkins/Jenkinsfile          # Jenkins pipeline
├── docker-grid.yml               # Selenium Grid hub + nodes
└── pom.xml
```

## Running Locally

```bash
# Default: local Chrome
mvn clean test

# Against Selenium Grid (local Docker)
docker-compose -f docker-grid.yml up -d
mvn clean test -Dgrid.url=http://localhost:4444 -Dbrowser=firefox

# Tag filtering
mvn clean test -Dcucumber.filter.tags="@smoke and not @wip"
```

## Enabling the live report (one-time GitHub setup)

After pushing the repo:

1. Go to your repo on GitHub → **Settings** → **Pages**
2. Under **Build and deployment → Source**, select **GitHub Actions**
3. Push to `main` (or wait for nightly cron) — the `publish-report` job will deploy
4. Report appears at `https://bakhrom-i.github.io/selenium-bdd-framework/`

## License

MIT
