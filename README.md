# Lemonfrog

Automation framework designed as a proof of concept for web project - Lemonfrog. It's being actively developed by the members of QA-Selenium workgroup.

## Technology stack
* Selenium WebDriver
* Java SE 8
* Maven
* TestNG
* Cucumber

## Git Branching Model
* Create new branch per feature/test - describe it with JIRA ticket + description with dashes (eg. QA-188-initial-project-setup)
* Commit changes often and try to describe them well
* After finishing feature or test create a Pull Request
* Share it with team members for review
* If changes are needed, the author of PR will apply them
* Merge the PR to master

## Initial project structure

```bash
└── lemonfrog
    └── src
    │   ├── main
    │   │   └── java
    │   │       ├── pages
    │   │       └── TestBase.class
    │   └── test
    │       ├── java
    │       │   └── steps
    │       └── resources
    │           └── features
    ├── pom.xml
    ├── testng.xml
    ├── initConfig.properties
    ├── README.md
    └── .gitignore
```
