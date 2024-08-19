package com.jms.designpatterns.creational.abstractfactory;

import com.jms.designpatterns.creational.abstractfactory.button.Button;
import com.jms.designpatterns.creational.abstractfactory.button.WindowsButton;
import com.jms.designpatterns.creational.abstractfactory.textbox.TextBox;
import com.jms.designpatterns.creational.abstractfactory.textbox.WindowsTextBox;

public class WindowsFactory implements Factory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public TextBox createTextBox() {
        return new WindowsTextBox();
    }
}
