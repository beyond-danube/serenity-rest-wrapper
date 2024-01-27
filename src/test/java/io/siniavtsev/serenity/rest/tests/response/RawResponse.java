package io.siniavtsev.serenity.rest.tests.response;

import io.siniavtsev.serenity.rest.tests.UnitTestSetup;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RawResponse extends UnitTestSetup {

    static final String ENDPOINT = "/resource";
    static final String RESPONSE_BODY = "{\"userId\":12, \"userName:\":\"Nicolas Cage\"}";
    static final String REQUEST_BODY = "{\"userName:\":\"Nicolas Cage\"}";

    @Test
    void getResponse() {

        wireMockInstance.stubFor(get(urlEqualTo(ENDPOINT))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(RESPONSE_BODY)));

        restSteps.setEndpoint(ENDPOINT);
        var result = restSteps.getResponse();

        assertThat(result.statusCode()).isEqualTo(200);
        assertThat(result.body().asString()).isEqualTo(RESPONSE_BODY);
    }

    @Test
    void postResponse() {

        wireMockInstance.stubFor(post(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(RESPONSE_BODY)));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPostResponse();

        assertThat(result.statusCode()).isEqualTo(200);
        assertThat(result.body().asString()).isEqualTo(RESPONSE_BODY);
    }

    @Test
    void putResponse() {

        wireMockInstance.stubFor(put(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(RESPONSE_BODY)));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPutResponse();

        assertThat(result.statusCode()).isEqualTo(200);
        assertThat(result.body().asString()).isEqualTo(RESPONSE_BODY);
    }

    @Test
    void patchResponse() {

        wireMockInstance.stubFor(patch(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(RESPONSE_BODY)));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPatchResponse();

        assertThat(result.statusCode()).isEqualTo(200);
        assertThat(result.body().asString()).isEqualTo(RESPONSE_BODY);
    }

    @Test
    void deleteResponse() {

        wireMockInstance.stubFor(delete(urlEqualTo(ENDPOINT))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(RESPONSE_BODY)));

        restSteps.setEndpoint(ENDPOINT);
        var result = restSteps.getDeleteResponse();

        assertThat(result.statusCode()).isEqualTo(200);
        assertThat(result.body().asString()).isEqualTo(RESPONSE_BODY);
    }
}
