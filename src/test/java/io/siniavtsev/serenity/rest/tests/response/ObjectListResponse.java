package io.siniavtsev.serenity.rest.tests.response;

import io.siniavtsev.serenity.rest.model.User;
import io.siniavtsev.serenity.rest.model.UsersList;
import io.siniavtsev.serenity.rest.tests.UnitTestSetup;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ObjectListResponse extends UnitTestSetup {

    static final String ENDPOINT = "/object-list-resource";
    static final UsersList<User> RESPONSE_BODY = new UsersList<>() {{
        add(User.builder().id(76).userName("Sigourney Weaver").build());
        add(User.builder().id(77).userName("Tom Hanks").build());
    }};
    static final UsersList<User> REQUEST_BODY = new UsersList<>() {{
        add(User.builder().userName("Sigourney Weaver").build());
        add(User.builder().userName("Tom Hanks").build());
    }};

    @Test
    void getResponse() {

        wireMockExtension.stubFor(get(urlEqualTo(ENDPOINT))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        var result = restSteps.getResponseObjectsList(User.class);

        assertThat(result).hasSameElementsAs(RESPONSE_BODY);
    }

    @Test
    void postResponse() {

        wireMockExtension.stubFor(post(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY.toJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPostResponseObjectsList(User.class);

        assertThat(result).hasSameElementsAs(RESPONSE_BODY);
    }

    @Test
    void putResponse() {

        wireMockExtension.stubFor(put(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY.toJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPutResponseObjectsList(User.class);

        assertThat(result).hasSameElementsAs(RESPONSE_BODY);
    }

    @Test
    void patchResponse() {

        wireMockExtension.stubFor(patch(urlEqualTo(ENDPOINT))
                .withRequestBody(equalToJson(REQUEST_BODY.toJson()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        restSteps.setRequestBody(REQUEST_BODY);
        var result = restSteps.getPatchResponseObjectsList(User.class);

        assertThat(result).hasSameElementsAs(RESPONSE_BODY);
    }

    @Test
    void deleteResponse() {

        wireMockExtension.stubFor(delete(urlEqualTo(ENDPOINT))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        var result = restSteps.getDeleteResponseObjectsList(User.class);

        assertThat(result).hasSameElementsAs(RESPONSE_BODY);
    }
}
