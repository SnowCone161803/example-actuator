package com.example.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MyCustomActuatorInfo implements InfoContributor {

    /**
     * Adds an object to the /actuator/info endpoint
     * @param builder the info builder.
     */
    @Override
    public void contribute(Info.Builder builder) {
        final var items = Map.of(
            "alpha", 100,
            "beta", 200
        );
        builder.withDetail(
            MyCustomActuatorInfo.class.getSimpleName(),
            items
        );
    }
}
