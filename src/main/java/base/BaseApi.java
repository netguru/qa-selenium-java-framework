package base;

import base.api.RequestBuilder;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeSuite;

public class BaseApi extends BaseTest{

    @BeforeSuite(alwaysRun = true)
    public void baseApiSetup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.BODY);
        RestAssured.config = RequestBuilder.getBasicRequestConfig();
    }
}
