package com.jms.designpatterns.creational.abstractfactory;

import com.jms.designpatterns.creational.abstractfactory.button.Button;
import com.jms.designpatterns.creational.abstractfactory.button.MacButton;
import com.jms.designpatterns.creational.abstractfactory.textbox.MacTextBox;
import com.jms.designpatterns.creational.abstractfactory.textbox.TextBox;

public class MacFactory implements Factory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public TextBox createTextBox() {
        return new MacTextBox();
    }
}
