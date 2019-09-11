package base.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.FailureConfig;
import io.restassured.config.HeaderConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.response.Response;

public class RequestBuilder {
    public static RequestSpecBuilder getBasicRequestSpecBuilder() {
        RequestSpecBuilder basicRequestSpecBuilder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .addFilter((req, response, ctx) -> {
                    Response rs = ctx.next(req, response);
                    String endpoint = req.getDerivedPath();
                    String rqBody = req.getBody() != null ? req.getBody().toString() : " ";
                    String rsBody = rs.getBody().asString().equals("") ? " " : rs.getBody().prettyPrint();

//                    Allure.addAttachment(String.format("Request Body - %s", endpoint), rqBody);
//                    Allure.addAttachment(String.format("Request Headers - %s", endpoint), req.getHeaders().toString());
//                    Allure.addAttachment(String.format("Request URI - %s", endpoint), req.getURI());
//                    Allure.addAttachment(String.format("Response Body - %s", endpoint), rsBody);
//                    Allure.addAttachment(String.format("Response Status Code - %s", endpoint),
//                            Integer.toString(rs.getStatusCode()));

                    return rs;
                });

        return basicRequestSpecBuilder;
    }

    public static RestAssuredConfig getBasicRequestConfig() {
        return (RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (type, s) -> {
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
                            return objectMapper;
                        }
                )));
    }
}
