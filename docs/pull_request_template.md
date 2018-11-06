### Summary

*[Short description related to the code. More details about test scenarios should be kept 
in the JIRA ticket related to the pull request.]*

### Reference
JIRA Ticket:  *[Insert here a link to the JIRA ticket.]*

### Checklist
- [ ] Do your branch and PR name start with issue key from JirQA?
- [ ] Do your changes require to update the README.md file? Have you already done that?
- [ ] If you have changed the `initConfig.properties` file remember to update it in 
  * 1password (vault #QA), 
  * `mvn clean test...` command in `.circleci/config.yml` file, 
  * environment variables in CircleCI project.
- [ ] Add the “Ready for review” label.
- [ ] Move the JIRA ticket to "Waiting for feedback".
- [ ] Remember to delete your branch after merging it.