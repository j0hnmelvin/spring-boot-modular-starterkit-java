package com.jms.designpatterns.creational.abstractfactory;

public class AbstractFactory {
    public static Factory createFactory(OperatingSystem operatingSystem) {
        switch (operatingSystem) {
            case MACOS -> {
                return new MacFactory();
            }
            case WINDOWS -> {
                return new WindowsFactory();
            }
        }
        return new MacFactory();
    }
}
