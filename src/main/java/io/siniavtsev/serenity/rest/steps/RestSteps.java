package io.siniavtsev.serenity.rest.steps;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.siniavtsev.serenity.rest.config.EnvironmentConfig;
import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;
import java.util.Map;

public class RestSteps {

    @Getter
    protected RequestSpecification request;

    protected String endpoint = "";

    @Setter
    protected String defaultBaseUri = "";

    @Setter
    protected String defaultBasePath = "";

    public RestSteps() {
        applyDefaults();
    }

    @Step
    public void addHeader(String name, String value) {
        request.header(new Header(name, value));
    }

    @Step
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Step
    public void setEndpoint(Object endpoint) {
        this.endpoint = String.valueOf(endpoint);
    }

    @Step
    public void setQueryParameters(Map<String, ?> parameters) {
        request.queryParams(parameters);
    }

    @Step("Set Query Parameters")
    public void setQueryParametersDecorated(Map<String, ?> parameters) {
        request.queryParams(parameters);
    }

    @Step
    public void setRequestBody(Object requestBody) {
        request.body(requestBody);
    }

    @Step("Set Request Body")
    public void setRequestBodyDecorated(Object requestBody) {
        request.body(requestBody);
    }

    @Step("GET request")
    public Response getResponse() {
        return request.get(endpoint);
    }

    @Step("POST request")
    public Response getPostResponse() {
        return request.post(endpoint);
    }

    @Step("PUT request")
    public Response getPutResponse() {
        return request.put(endpoint);
    }

    @Step("PATCH request")
    public Response getPatchResponse() {
        return request.patch(endpoint);
    }

    @Step("DELETE request")
    public Response getDeleteResponse() {
        return request.delete(endpoint);
    }

    @Step("Response from GET request as object: {0}")
    public <T> T getResponseObject(Class<T> targetType) {
        return getResponse().as(targetType);
    }

    @Step("Response from POST request as object: {0}")
    public <T> T getPostResponseObject(Class<T> targetType) {
        return getPostResponse().as(targetType);
    }

    @Step("Response from PUT request as object: {0}")
    public <T> T getPutResponseObject(Class<T> targetType) {
        return getPutResponse().as(targetType);
    }
    @Step("Response from PATCH request as object: {0}")
    public <T> T getPatchResponseObject(Class<T> targetType) {
        return getPatchResponse().as(targetType);
    }

    @Step("Response from DELETE request as object: {0}")
    public <T> T getDeleteResponseObject(Class<T> targetType) {
        return getDeleteResponse().as(targetType);
    }

    @Step("Response from GET request as list of objects: {0}")
    public <T> List<T> getResponseObjectsList(Class<T> targetType) {
        return getResponse().jsonPath().getList(".", targetType);
    }

    @Step("Response from POST request as list of objects: {0}")
    public <T> List<T> getPostResponseObjectsList(Class<T> targetType) {
        return getPostResponse().jsonPath().getList(".", targetType);
    }

    @Step("Response from POST request as list of objects: {0}")
    public <T> List<T> getPutResponseObjectsList(Class<T> targetType) {
        return getPutResponse().jsonPath().getList(".", targetType);
    }

    @Step("Response from PATCH request as list of objects: {0}")
    public <T> List<T> getPatchResponseObjectsList(Class<T> targetType) {
        return getPatchResponse().jsonPath().getList(".", targetType);
    }

    @Step("Response from DELETE request as list of objects: {0}")
    public <T> List<T> getDeleteResponseObjectsList(Class<T> targetType) {
        return getDeleteResponse().jsonPath().getList(".", targetType);
    }

    @Step
    public void setBaseUri(String baseUri) {
        request.baseUri(baseUri);
    }

    @Step
    public void setBasePath(String basePath) {
        request.baseUri(basePath);
    }

    @Step
    public void setContentType(String contentType) {
        request.contentType(contentType);
    }

    @Step
    public void setContentType(ContentType contentType) {
        request.contentType(contentType);
    }

    public void setDefaultsFromEnvironmentConfig(EnvironmentConfig environmentConfig) {
        defaultBaseUri = environmentConfig.getBaseUri();
        defaultBasePath = environmentConfig.getBasePath();

        applyDefaults();
    }

    public void applyDefaults() {
        request = SerenityRest.given()
                .contentType(ContentType.JSON)
                .baseUri(defaultBaseUri)
                .basePath(defaultBasePath);
    }
}
