package io.siniavtsev.serenity.rest.tests.response;

import io.siniavtsev.serenity.rest.model.User;
import io.siniavtsev.serenity.rest.tests.UnitTestSetup;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ObjectResponse extends UnitTestSetup {

    static final String ENDPOINT = "/object-resource";
    static final User RESPONSE_BODY = User.builder().id(13).userName("Matthew McConaughey").build();
    static final User REQUEST_BODY = User.builder().userName("Matthew McConaughey").build();

    @Test
    void getResponse() {

        wireMockConfig.stubFor(get(urlEqualTo(ENDPOINT))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        var result = restSteps.getResponseObject(User.class);

        assertThat(result).usingRecursiveComparison().isEqualTo(RESPONSE_BODY);
    }

    @Test
    void postResponse() {

        wireMockConfig.stubFor(post(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY.toJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPostResponseObject(User.class);

        assertThat(result).isEqualTo(RESPONSE_BODY);
    }

    @Test
    void putResponse() {

        wireMockConfig.stubFor(put(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY.toJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPutResponseObject(User.class);

        assertThat(result).isEqualTo(RESPONSE_BODY);
    }

    @Test
    void patchResponse() {

        wireMockConfig.stubFor(patch(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY.toJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPatchResponseObject(User.class);

        assertThat(result).isEqualTo(RESPONSE_BODY);
    }

    @Test
    void deleteResponse() {

        wireMockConfig.stubFor(delete(urlEqualTo(ENDPOINT))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        var result = restSteps.getDeleteResponseObject(User.class);

        assertThat(result).isEqualTo(RESPONSE_BODY);
    }
}
