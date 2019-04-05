package com.arcsq.fbd.featureflags;

import no.finn.unleash.Unleash;
import no.finn.unleash.UnleashContext;
import no.finn.unleash.strategy.UserWithIdStrategy;
import no.finn.unleash.util.UnleashConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureBasedActions {

    @Autowired
    private Unleash unleash;

    @GetMapping(path = "/process-ssn/{ssn}")
    public String invokeProcessSSN(@PathVariable("ssn") String ssn) {
        if (unleash.isEnabled("show-last-4-social")) {
            return "Your Social Security Number " + ssn.substring(ssn.length() - 4, ssn.length()) +
                    " has been processed";
        }
        else {
            return "Your Social Security Number has been processed";
        }
    }

    @GetMapping(path = "/process-client-ssn/{clientId}/{ssn}")
    public String invokeProcessSSN(@PathVariable("clientId") String clientId, @PathVariable("ssn") String ssn) {
        UnleashContext context = UnleashContext.builder().addProperty("clientId", clientId).build();
        if (unleash.isEnabled("client-based-ssn-display", context)) {
            return "Your Social Security Number " + ssn.substring(ssn.length() - 4, ssn.length()) +
                    " has been processed";
        }
        else {
            return "Your Social Security Number has been processed";
        }
    }

}
