# Lemonfrog

Automation framework designed as a proof of concept for web project - Lemonfrog. It's being actively developed by the members of QA-Selenium workgroup.

## Technology stack
* Selenium WebDriver
* Java SE 8
* Maven
* TestNG
* Cucumber

## How to run tests
1.Open up terminal and create project folder
```bash
mkdir NAME_OF_FOLDER
cd NAME_OF_FOLDER
```
2.Clone our git repo and navigate to project
```bash
git clone REPO_URL_FROM_GITHUB
cd qa-selenium
```
3.Copy `initConfig.properties` file from 1pass lemonfrog vault and paste to `qa-selenium` project folder

4a.Compile project files and run tests
```bash
mvn clean install test -Dcucumber.options="--tags @{name of tag, with upper/lowercase letters}"
```
4b.To run tests from previous build type
```bash
mvn test -Dcucumber.options="--tags @{name of tag, with upper/lowercase letters}"
```

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
    ├── src
    │   ├── main
    │   │   └── java
    │   │       ├── base
    │   │       │   ├── PageBase.java
    │   │       │   ├── SectionBase.java
    │   │       │   ├── TestBase.java
    │   │       │   └── UserType.java
    │   │       └── pages
    │   └── test
    │       ├── java
    │       │   ├── runners
    │       │   └── steps
    │       └── resources
    │           ├── drivers
    │           └── features   
    ├── pom.xml
    ├── testng.xml
    ├── README.md
    └── .gitignore
```
