package com.jms.designpatterns.creational.factory;

public class VanillaCake implements Cake {

    static final String DESCRIPTION = "This is a vanilla cake.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
