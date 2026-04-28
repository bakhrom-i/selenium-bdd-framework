# Selenium BDD Framework

> Production-ready **Selenium-Java** framework with **Cucumber BDD**, **Page Object Model**, parallel execution on **Selenium Grid**, and **Jenkins** CI pipeline.

[![Selenium BDD Tests](https://github.com/YOUR_GH_USER/selenium-bdd-framework/actions/workflows/tests.yml/badge.svg)](https://github.com/YOUR_GH_USER/selenium-bdd-framework/actions/workflows/tests.yml)
![Java](https://img.shields.io/badge/java-17-orange)
![Selenium](https://img.shields.io/badge/selenium-4.20-43B02A)
![Cucumber](https://img.shields.io/badge/cucumber-7-23D96C)
![License](https://img.shields.io/badge/license-MIT-blue)

**📊 Live test report:** https://YOUR_GH_USER.github.io/selenium-bdd-framework/

> _Replace `YOUR_GH_USER` with your GitHub username after pushing._

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
4. Report appears at `https://YOUR_GH_USER.github.io/selenium-bdd-framework/`

## License

MIT
