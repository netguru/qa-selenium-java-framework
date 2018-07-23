package managers;

import pages.*;

public class PageObjectManager {
    private DashboardPage dashboardPage;
    private ForgotPasswordPage forgotPasswordPage;
    private HomePage homePage;
    private LogInPage logInPage;
    private SearchResultsPage searchResultsPage;
    private SignUpPage signUpPage;
    private StudentProfilePage studentProfilePage;
    private TutorProfilePage tutorProfilePage;

    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage() : dashboardPage;
    }

    public ForgotPasswordPage getForgotPasswordPage() {
        return (forgotPasswordPage == null) ? forgotPasswordPage = new ForgotPasswordPage() : forgotPasswordPage;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage() : homePage;
    }

    public LogInPage getLogInPage() {
        return (logInPage == null) ? logInPage = new LogInPage() : logInPage;
    }

    public SearchResultsPage getSearchResultsPage() {
        return (searchResultsPage == null) ? searchResultsPage = new SearchResultsPage() : searchResultsPage;
    }

    public SignUpPage getSignUpPage() {
        return (signUpPage == null) ? signUpPage = new SignUpPage() : signUpPage;
    }

    public StudentProfilePage getStudentProfilePage() {
        return (studentProfilePage == null) ? studentProfilePage = new StudentProfilePage() : studentProfilePage;
    }

    public TutorProfilePage getTutorProfilePage() {
        return (tutorProfilePage == null) ? tutorProfilePage = new TutorProfilePage() : tutorProfilePage;
    }
}
