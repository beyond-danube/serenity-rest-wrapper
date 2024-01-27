package io.siniavtsev.serenity.rest.config;

public class WireMockEnvConfig implements EnvironmentConfig {
    @Override
    public String getBaseUri() {
        return "http://localhost:8080";
    }

    @Override
    public String getBasePath() {
        return "";
    }
}
