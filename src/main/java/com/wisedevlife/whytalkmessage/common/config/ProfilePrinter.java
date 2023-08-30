package com.wisedevlife.whytalkmessage.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class ProfilePrinter {
    private final Environment environment;

    public void printActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("Active Profiles: " + String.join(", ", activeProfiles));
    }
}
