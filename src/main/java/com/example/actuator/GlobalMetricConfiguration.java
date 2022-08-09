package com.example.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalMetricConfiguration {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        // this tag gets added to all metric endpoints
        // currently, I don't see why this is useful
        // need to see how tags are being used in monitoring applications to understand what is going on here
        //
        // possibly good for:
        // - microservice type
        // - microservice instance ID
        // it might make these things filterable in 3rd party tools
        return registry -> registry.config().commonTags("globalTagType", "globalTagValue");
    }
}
