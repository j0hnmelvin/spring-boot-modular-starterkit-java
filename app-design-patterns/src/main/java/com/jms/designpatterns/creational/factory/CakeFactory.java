package com.jms.designpatterns.creational.factory;

public class CakeFactory {

    public static Cake getCake(CakeType cakeType) {
        if (cakeType == CakeType.CHOCOLATE) {
            return new ChocolateCake();
        } else if (cakeType == CakeType.STRAWBERRY) {
            return new StrawberryCake();
        }  else if (cakeType == CakeType.VANILLA) {
            return new VanillaCake();
        } else {
            throw new RuntimeException("Cake type not defined");
        }
    }
}
