package io.siniavtsev.serenity.rest.config;

public class WireMockEnvConfig implements EnvironmentConfig {
    static String port;

    @Override
    public String getBasePath() {
        return "";
    }

    @Override
    public String getBaseUri() {
        return "http://localhost:" + port;
    }

    public void setPort(String port) {
        WireMockEnvConfig.port = port;
    }
}
