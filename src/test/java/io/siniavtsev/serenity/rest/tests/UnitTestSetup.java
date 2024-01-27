package io.siniavtsev.serenity.rest.tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.siniavtsev.serenity.rest.config.WireMockEnvConfig;
import io.siniavtsev.serenity.rest.steps.RestSteps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class UnitTestSetup {

    static WireMockServer wireMockServer = new WireMockServer();
    protected RestSteps restSteps = new RestSteps();
    WireMockEnvConfig envConfig = new WireMockEnvConfig();

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
