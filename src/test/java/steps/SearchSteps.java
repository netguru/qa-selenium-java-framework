package steps;

import base.TestingBase;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class SearchSteps extends TestingBase {

    @When("User searches a profile in {string}")
    public void tutorSearchesAJobIn(String location) {
        context.pages.getDashboardPage().setLocation(location);
        context.pages.getDashboardPage().clickSearchButton();
    }

    @When("Opens first profile")
    public void openFirstProfile() {
        context.pages.getSearchResultsPage().openFirstSearchResult();
    }

    @Then("Tutor profile is displayed")
    public void tutorProfileIsDisplayed() {
        context.pages.getTutorProfilePage().isInitialized();
    }

    @Then("Student profile is displayed")
    public void studentProfileIsDisplayed() {
        context.pages.getStudentProfilePage().isInitialized();
    }

    @When("User sets the distance of (1|5|10|20|50|100) km")
    public void userSetsTheDistanceOfKm(String distance) {
        context.pages.getDashboardPage().setDistance(distance);
    }

    @Then("Search results match distance of (1|5|10|20|50|100) km")
    public void searchResultsMatchDistanceOfKm(String expectedDistance) {
        Assert.assertEquals(context.pages.getSearchResultsPage().getMaxDistance(), expectedDistance + " km");
    }
}
