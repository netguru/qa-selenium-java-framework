package steps;

import base.TestingBase;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class SearchSteps extends TestingBase {

    @When("User searches a profile in \"([^\"]*)\"")
    public void tutorSearchesAJobIn(String location) {
        pages.getDashboardPage().setLocation(location);
        pages.getDashboardPage().clickSearchButton();
    }

    @When("Opens first profile")
    public void openFirstProfile() {
        pages.getSearchResultsPage().openFirstSearchResult();
    }

    @Then("Tutor profile is displayed")
    public void tutorProfileIsDisplayed() {
        pages.getTutorProfilePage().isInitialized();
    }

    @Then("Student profile is displayed")
    public void studentProfileIsDisplayed() {
        pages.getStudentProfilePage().isInitialized();
    }

    @When("User sets the distance of (1|5|10|20|50|100) km")
    public void userSetsTheDistanceOfKm(String distance) {
        pages.getDashboardPage().setDistance(distance);
    }

    @Then("Search results match distance of (1|5|10|20|50|100) km")
    public void searchResultsMatchDistanceOfKm(String expectedDistance) {
        Assert.assertEquals(expectedDistance + " km", pages.getSearchResultsPage().getMaxDistance());
    }
}
