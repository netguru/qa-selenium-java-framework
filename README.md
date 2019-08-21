# qa-java-framework

Fork created as an alternative to BDD version of [qa-java-framework](https://github.com/netguru/qa-selenium-java-framework)

Kudos for all the [contributors of the original repository](https://github.com/netguru/qa-selenium-java-framework/graphs/contributors) - thanks for your hard work

## Technology stack
* Selenium WebDriver
* Java SE 8
* Gradle
* TestNG

## How to run tests
1. Change `initConfig.properties` file
2. Run tasks `:cleanTest :test`
3. Pass the arguments `--tests *` to run all the tests

If you don't want to install Gradle on your local machine, you can use gradle wrapper to run tests and execute others commands. 
To do it:
1. open terminal
2. go to project folder (`pwd command should displayed qa-selenium-java-framework as current folder`)
3. use 
`./gradlew` instead `gradle`, eg. to compile project and run all tests:
```bash
./gradlew clean
```

## To run OWASP Zap Daemon
```docker run -u zap -p 8899:8899 -i owasp/zap2docker-stable zap.sh -daemon -host 0.0.0.0 -port 8899 -config api.key=SECRETKEY -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true```

## Git Branching Model
* Create new branch per feature/test
* Commit changes often and try to describe them well
* After finishing feature or test create a Pull Request
* Share it with team members for review
* If changes are needed, the author of PR will apply them
* Merge the PR to master branch

## License

See the [LICENSE](LICENSE) file for license rights and limitations (MIT).
