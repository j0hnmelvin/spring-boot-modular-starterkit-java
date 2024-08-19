package com.jms.designpatterns.creational.abstractfactory;

import com.jms.designpatterns.creational.abstractfactory.button.Button;
import com.jms.designpatterns.creational.abstractfactory.textbox.TextBox;

public interface Factory {
    Button createButton();
    TextBox createTextBox();
}
