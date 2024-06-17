package com.example.logging_filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

@ConfigurationProperties
public class LoggingDebugAllowedConfiguration {

    public static List<String> CORRELATION_IDS;
    public static String ROOT_PACKAGE;

    @Value("${logging.debug.allowed.root-package}")
    public void setRootPackage(String rootPackage) {
        ROOT_PACKAGE = rootPackage;
    }

    @Value("${logging.debug.allowed.correlation-ids}")
    public void setCorrelationIds(List<String> correlationIds) {
        CORRELATION_IDS = correlationIds;
    }

}
