package io.siniavtsev.serenity.rest.tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.siniavtsev.serenity.rest.config.WireMockEnvConfig;
import io.siniavtsev.serenity.rest.steps.RestSteps;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@WireMockTest
public class UnitTestSetup {

    protected static WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    static WireMockEnvConfig envConfig = new WireMockEnvConfig();

    protected RestSteps restSteps = new RestSteps();

    @RegisterExtension
    protected static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    @BeforeAll
    static void globalSetup() {
        wireMockServer.start();
        envConfig.setPort(String.valueOf(wireMockExtension.getPort()));
    }

    @AfterAll
    static void globalShutdown() {
        wireMockServer.stop();
    }

    @BeforeEach
    void restTestSetup() {
        restSteps.setDefaultsFromEnvironmentConfig(envConfig);
        System.out.println(envConfig.getBaseUri());
    }

    @AfterEach
    void restTestCleanup() {
        wireMockServer.resetAll();
    }
}
