package managers;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageObjectManager {
    private WebDriver driver;
    private DashboardPage dashboardPage;
    private ForgotPasswordPage forgotPasswordPage;
    private HomePage homePage;
    private LogInPage logInPage;
    private SearchResultsPage searchResultsPage;
    private SignUpPage signUpPage;
    private StudentProfilePage studentProfilePage;
    private TutorProfilePage tutorProfilePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage(driver) : dashboardPage;
    }

    public ForgotPasswordPage getForgotPasswordPage() {
        return (forgotPasswordPage == null) ? forgotPasswordPage = new ForgotPasswordPage(driver) : forgotPasswordPage;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    public LogInPage getLogInPage() {
        return (logInPage == null) ? logInPage = new LogInPage(driver) : logInPage;
    }

    public SearchResultsPage getSearchResultsPage() {
        return (searchResultsPage == null) ? searchResultsPage = new SearchResultsPage(driver) : searchResultsPage;
    }

    public SignUpPage getSignUpPage() {
        return (signUpPage == null) ? signUpPage = new SignUpPage(driver) : signUpPage;
    }

    public StudentProfilePage getStudentProfilePage() {
        return (studentProfilePage == null) ? studentProfilePage = new StudentProfilePage(driver) : studentProfilePage;
    }

    public TutorProfilePage getTutorProfilePage() {
        return (tutorProfilePage == null) ? tutorProfilePage = new TutorProfilePage(driver) : tutorProfilePage;
    }
}
