package io.siniavtsev.serenity.rest.tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.siniavtsev.serenity.rest.config.WireMockEnvConfig;
import io.siniavtsev.serenity.rest.steps.RestSteps;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@ExtendWith(SerenityJUnit5Extension.class)
public class UnitTestSetup {

    protected static WireMockServer wireMockServer = new WireMockServer();

    @RegisterExtension
    protected static WireMockExtension wireMockInstance = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
            .build();
    static WireMockEnvConfig envConfig = new WireMockEnvConfig();
    @Steps
    protected RestSteps restSteps;

    @BeforeAll
    static void globalSetup() {
        wireMockServer.start();
        envConfig.setPort(String.valueOf(wireMockInstance.getPort()));
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
        wireMockInstance.resetAll();
    }
}
