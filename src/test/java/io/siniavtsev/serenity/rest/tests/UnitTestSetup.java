package io.siniavtsev.serenity.rest.tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.siniavtsev.serenity.rest.config.WireMockEnvConfig;
import io.siniavtsev.serenity.rest.steps.RestSteps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class UnitTestSetup {

    protected static WireMockServer wireMockServer = new WireMockServer();
    protected RestSteps restSteps = new RestSteps();
    WireMockEnvConfig envConfig = new WireMockEnvConfig();

    @RegisterExtension
    protected static WireMockExtension wireMockConfig = WireMockExtension.newInstance()
            .options(wireMockConfig().port(3000))
            .build();

    @BeforeAll
    static void globalSetup() {
        wireMockServer.start();
    }

    @AfterAll
    static void globalShutdown() {
        wireMockServer.stop();
    }

    @BeforeEach
    void restTestSetup() {
        restSteps.setDefaultsFromEnvironmentConfig(envConfig);
    }

    @AfterEach
    void restTestCleanup() {
        wireMockServer.resetAll();
    }
}
