package com.switchfully.digibooky.domain.users;

import com.google.common.collect.Lists;

import java.util.List;

import static com.switchfully.digibooky.domain.users.Feature.LOGIN;


public enum Role {
    MEMBER(Lists.newArrayList(LOGIN)),
    ADMIN(Lists.newArrayList(LOGIN)),
    LIBRARIAN(Lists.newArrayList(LOGIN));

    private final List<Feature> features;

    Role(List<Feature> features) {
        this.features = features;
    }

    public boolean containsFeature(Feature feature) {
        return features.contains(feature);
    }
}
