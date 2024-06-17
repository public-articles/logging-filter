package com.example.logging_filter;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

public class MDCTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable task) {
        Map<String, String> webThreadContext = MDC.getCopyOfContextMap();
        return () -> {
            try {
                MDC.setContextMap(webThreadContext);
                task.run();
            } finally {
                MDC.clear();
            }
        };
    }

}
