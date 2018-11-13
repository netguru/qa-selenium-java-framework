package managers;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageObjectManager {
    private WebDriver driver;

    private HomePage homePage;
    private LoginPage loginPage;
    private ResetPasswordPage resetPasswordPage;
    private RegisterPage registerPage;
    private SearchPage searchPage;
    private EditProfileBasicInformationPage editProfileBasicInformationPage;
    private EditProfileBecomeADetectivePage editProfileBecomeADetectivePage;
    private AddRestaurantStep1Page addRestaurantStep1Page;

    private HeaderSection headerSection;
    private FooterSection footerSection;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(this.driver) : homePage;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public ResetPasswordPage getResetPasswordPage() {
        return (resetPasswordPage == null) ? resetPasswordPage = new ResetPasswordPage(driver) : resetPasswordPage;
    }

    public RegisterPage getRegisterPage() {
        return (registerPage == null) ? registerPage = new RegisterPage(driver) : registerPage;
    }

    public SearchPage getSearchPage() {
        return (searchPage == null) ? searchPage = new SearchPage(driver) : searchPage;
    }

    public EditProfileBasicInformationPage getEditProfileBasicInformationPage() {
        return (editProfileBasicInformationPage == null) ?
                editProfileBasicInformationPage = new EditProfileBasicInformationPage(driver) :
                editProfileBasicInformationPage;
    }

    public EditProfileBecomeADetectivePage getEditProfileBecomeADetectivePage() {
        return (editProfileBecomeADetectivePage == null) ?
                editProfileBecomeADetectivePage = new EditProfileBecomeADetectivePage(driver) :
                editProfileBecomeADetectivePage;
    }

    public AddRestaurantStep1Page getAddRestaurantStep1Page() {
        return (addRestaurantStep1Page == null) ?
                addRestaurantStep1Page = new AddRestaurantStep1Page(driver) :
                addRestaurantStep1Page;
    }

    public HeaderSection getHeaderSection() {
        return (headerSection == null) ? headerSection = new HeaderSection(driver) : headerSection;
    }

    public FooterSection getFooterSection() {
        return (footerSection == null) ? footerSection = new FooterSection(driver) : footerSection;
    }
}
