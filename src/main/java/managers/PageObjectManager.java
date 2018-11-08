package managers;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageObjectManager {
    private WebDriver driver;

    private HomePage homePage;
    private LoginPage loginPage;
    private ResetPasswordPage resetPasswordPage;
    private RegisterPage registerPage;

    private HeaderSection headerSection;

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

    public HeaderSection getHeaderSection() {
        return (headerSection == null) ? headerSection = new HeaderSection(driver) : headerSection;
    }
}
