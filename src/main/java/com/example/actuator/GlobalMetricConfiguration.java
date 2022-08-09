package com.example.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalMetricConfiguration {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        // this tag gets added to all metric endpoints; why is this useful?!
        return registry -> registry.config().commonTags("globalTagType", "globalTagValue");
    }
}
