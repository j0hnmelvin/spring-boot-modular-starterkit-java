package com.jms.designpatterns.creational.abstractfactory.textbox;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WindowsTextBox implements TextBox {
    @Override
    public void displayTextBox() {
        log.info("Displaying Windows TextBox");
    }
}
