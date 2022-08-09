package com.example.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class MyCustomHealthIndicator implements HealthIndicator {

    private int counter = 0;

    @Override
    public Health health() {
        final Health result;
        switch (counter++) {
            case 0:
                result = Health.unknown().build();
                break;
            case 1:
                result = Health.down().build();
                break;
            case 2:
                result = Health.outOfService().build();
                break;
            default:
                result = Health.up().build();
                counter = 0;
        }
        return result;
    }
}