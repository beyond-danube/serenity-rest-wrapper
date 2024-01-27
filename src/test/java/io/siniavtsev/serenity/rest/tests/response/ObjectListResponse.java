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

        stubFor(get(urlEqualTo(ENDPOINT))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_BODY.toJson())));

        restSteps.setEndpoint(ENDPOINT);
        var result = restSteps.getResponseObjectsList(User.class);

        assertThat(result).hasSameElementsAs(RESPONSE_BODY);
    }
}
