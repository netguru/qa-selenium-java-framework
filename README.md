# qa-java-framework

## Technology stack
* Selenium WebDriver
* Java SE 8
* Maven
* JUnit
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
cd qa-java-framework
```
3.Create `initConfig.properties` file and store it only in project's 1pass vault

4a.Compile project files and run tests
```bash
mvn clean test -Dcucumber.options="--tags @{name of tag, with upper/lowercase letters}"
```
4b.To run tests from previous build type
```bash
mvn test -Dcucumber.options="--tags @{name of tag, with upper/lowercase letters}"
```

5.To run tests with html reporting tool use 
 5a. after running `test` phase run: 
```bash
mvn verify -DskipTests
```
5b. or using syntax from step 4 run:
```bash
mvn clean verify -Dcucumber.options="--tags @{name of tag, with upper/lowercase letters}"
```

## Git Branching Model
* Create new branch per feature/test - describe it with JIRA ticket + description with dashes (eg. QA-188-initial-project-setup)
* Commit changes often and try to describe them well
* After finishing feature or test create a Pull Request
* Share it with team members for review
* If changes are needed, the author of PR will apply them
* Merge the PR to master

## License

See the [LICENSE](LICENSE) file for license rights and limitations (MIT).
