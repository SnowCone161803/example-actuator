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

     public void updateMetrics(long value) {
         incrementCounter(value);
     }

     private void incrementCounter(long value) {
         meterRegistry.counter(
             // this is the metrics/<endpoint> value
             MyCustomMetrics.class.getSimpleName(),
             // tags are optional
             "typeOfTag:" + (value % 4), "tagValue:" + (value % 8)
         ).increment();
     }
}
