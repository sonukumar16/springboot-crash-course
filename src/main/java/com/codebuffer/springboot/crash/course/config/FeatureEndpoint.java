/*
This class is needed to define and tell which features are enable for which environment
so that we can easily get information about the features without any deep diving.
 */

package com.codebuffer.springboot.crash.course.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
    private final Map<String, Feature> featureMap =
            new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featureMap.put("Department", new Feature(true));
        featureMap.put("User", new Feature(false));
        featureMap.put("Authentication", new Feature(false));
    }

    @ReadOperation
    public Feature feature(@Selector String featureName) {
        return featureMap.get(featureName);
    }

    @ReadOperation
    public Map<String, Feature> features() {
        return featureMap;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature {
       private boolean isEnabled;
    }
}
