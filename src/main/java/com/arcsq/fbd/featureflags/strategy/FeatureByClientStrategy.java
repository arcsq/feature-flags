package com.arcsq.fbd.featureflags.strategy;

import no.finn.unleash.UnleashContext;
import no.finn.unleash.strategy.Strategy;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Map;

public class FeatureByClientStrategy implements Strategy {


    @Override
    public String getName() {
        return "featureByClient";
    }

    @Override
    public boolean isEnabled(Map<String, String> map) {
        return false;
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters, UnleashContext unleashContext) {
        return parameters.get("clientIds").contains(unleashContext.getProperties().get("clientId"));
    }
}
