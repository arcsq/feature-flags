package com.arcsq.fbd.featureflags;

import com.arcsq.fbd.featureflags.strategy.FeatureByClientStrategy;
import no.finn.unleash.DefaultUnleash;
import no.finn.unleash.Unleash;
import no.finn.unleash.util.UnleashConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FeatureFlagsApplication {

    @Bean
    public Unleash initUnleash() {
        UnleashConfig config = UnleashConfig.builder().appName("ssn-processing")
                .unleashAPI("http://localhost:4242/api").build();
        Unleash unleash = new DefaultUnleash(config, new FeatureByClientStrategy());
        return unleash;
    }

    public static void main(String[] args) {
        SpringApplication.run(FeatureFlagsApplication.class, args);
    }

}
