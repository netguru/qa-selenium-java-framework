package steps;

import base.TestingBase;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.DashboardPage;
import pages.SearchResultsPage;
import pages.StudentProfilePage;
import pages.TutorProfilePage;

public class SearchSteps extends TestingBase {
    private DashboardPage dashboardPage = new DashboardPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private TutorProfilePage tutorProfilePage = new TutorProfilePage();
    private StudentProfilePage studentProfilePage = new StudentProfilePage();

    @When("User searches a profile in {string}")
    public void tutorSearchesAJobIn(String location) {
        dashboardPage.setLocation(location);
        dashboardPage.clickSearchButton();
    }

    @When("Opens first profile")
    public void openFirstProfile() {
        searchResultsPage.openFirstSearchResult();
    }

    @Then("Tutor profile is displayed")
    public void tutorProfileIsDisplayed() {
        tutorProfilePage.isInitialized();
    }

    @Then("Student profile is displayed")
    public void studentProfileIsDisplayed() {
        studentProfilePage.isInitialized();
    }

    @When("User sets the distance of (1|5|10|20|50|100) km")
    public void userSetsTheDistanceOfKm(String distance) {
        dashboardPage.setDistance(distance);
    }

    @Then("Search results match distance of (1|5|10|20|50|100) km")
    public void searchResultsMatchDistanceOfKm(String expectedDistance) {
        Assert.assertEquals(searchResultsPage.getMaxDistance(), expectedDistance + " km");
    }
}
