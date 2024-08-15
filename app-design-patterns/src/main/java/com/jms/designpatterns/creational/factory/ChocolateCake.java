package com.jms.designpatterns.creational.factory;

public class ChocolateCake implements Cake {

    static final String DESCRIPTION = "This is a chocolate cake.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
