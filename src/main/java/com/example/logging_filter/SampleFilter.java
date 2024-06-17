package com.example.logging_filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SampleFilter extends Filter<ILoggingEvent> {

    private final String ALLOW_ALL_CORRELATION_IDS_WILDCARD = "all";
    private final String DEFAULT_CORRELATION_ID = "none";

    @Override
    public FilterReply decide(ILoggingEvent event) {

        var requestCorrelationID = Optional.ofNullable(event.getMDCPropertyMap().get(FilterConfig.CORRELATION_ID)).orElse(DEFAULT_CORRELATION_ID);
        if (event.getLevel()==Level.DEBUG
                && event.getLoggerName().startsWith(LoggingDebugAllowedConfiguration.ROOT_PACKAGE)) {

            if (LoggingDebugAllowedConfiguration.CORRELATION_IDS.contains(requestCorrelationID)
                    || LoggingDebugAllowedConfiguration.CORRELATION_IDS.contains(ALLOW_ALL_CORRELATION_IDS_WILDCARD)) {
                return FilterReply.ACCEPT;
            } else {
                return FilterReply.DENY;
            }

        } else {
            return FilterReply.NEUTRAL;
        }

    }

}