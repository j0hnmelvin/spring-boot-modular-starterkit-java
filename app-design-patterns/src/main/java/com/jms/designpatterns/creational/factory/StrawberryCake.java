package com.jms.designpatterns.creational.factory;

public class StrawberryCake implements Cake {

    static final String DESCRIPTION = "This is a strawberry cake.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
