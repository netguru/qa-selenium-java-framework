# qa-java-framework

## Technology stack
* Selenium WebDriver
* Java SE 8
* Gradle
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

4a.Compile project files and run all tests
```bash
gradle clean cucumber
```
4b.To run tests for specific tags
```bash
gradle clean cucumber -PmyTags="@tag1,@tag2,..."
```

5.To run tests with html reporting tool use 
5a. after running `test` phase run:
```bash
gradle generateCucumberReports
```
5b. or using syntax from step 4 run:
```bash
gradle clean cucumber generateCucumberReports
```
6.If you don't want to install Gradle on your local machine, you can use gradle wrapper to run tests and execute others commands. 
To do it:
1. open terminal
2. go to project folder (`pwd command should displayed qa-selenium-java-framework as current folder`)
3. use 
`./gradlew` instead `gradle`, eg. to compile project and run all tests:
```bash
./gradlew clean cucumber
```

7.If you want to skip some tests use `@wip` tag to mark selected features.

## To run OWASP Zap Daemon
```docker run -u zap -p 8899:8899 -i owasp/zap2docker-stable zap.sh -daemon -host 0.0.0.0 -port 8899 -config api.key=SECRETKEY -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true```

## Git Branching Model
* Create new branch per feature/test - describe it with JIRA ticket + description with dashes (eg. QA-188-initial-project-setup)
* Commit changes often and try to describe them well
* After finishing feature or test create a Pull Request
* Share it with team members for review
* If changes are needed, the author of PR will apply them
* Merge the PR to master

## License

See the [LICENSE](LICENSE) file for license rights and limitations (MIT).
