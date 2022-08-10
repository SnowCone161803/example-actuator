package com.example.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class MyCustomMetrics {

    private final MeterRegistry meterRegistry;
    private final Flux<Long> everySecond = Flux.interval(Duration.ofSeconds(1));

    @PostConstruct
    public void init() {
        // increment counter every second
        everySecond.subscribe(this::updateMetrics);
    }

    /**
     * creates and updates a counter on /actuator/metrics/MyCustomMetrics
     */
    public void updateMetrics(long value) {
        incrementCounter(value);
        updateSummary(value);
    }

    /**
     * creates and updates a counter on /actuator/metrics/MyCustomMetrics-set-value.
     * <br>
     * This also displays the total and max values for the record
     * <br>
     * Interestingly, this doesn't show the value in the endpoint; unless I'm missing something.
     */
    private void updateSummary(long value) {
        meterRegistry.summary(MyCustomMetrics.class.getSimpleName() + "-set-value")
            // statistic: "MAX" will be 109 when you look at the endpoint
            .record(100 + value % 10);
    }

    /**
     * Increment a counter each second
     * @param value used to determine the tag to add along with the counter increasing.
     */
    private void incrementCounter(long value) {
        meterRegistry.counter(
            // this is the metrics/<endpoint> value
            MyCustomMetrics.class.getSimpleName(),
            // tags are optional
            "typeOfTag:" + (value % 4), "tagValue:" + (value % 8)
        ).increment();
    }
}
