package com.jms.designpatterns.creational.abstractfactory.button;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WindowsButton implements Button {
    @Override
    public void displayButton() {
        log.info("Displaying Windows Button");
    }
}
