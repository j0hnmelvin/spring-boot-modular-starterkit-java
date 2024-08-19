package com.jms.designpatterns;

import com.jms.designpatterns.behavioral.observer.Group;
import com.jms.designpatterns.behavioral.observer.User;
import com.jms.designpatterns.creational.abstractfactory.AbstractFactory;
import com.jms.designpatterns.creational.abstractfactory.Factory;
import com.jms.designpatterns.creational.abstractfactory.OperatingSystem;
import com.jms.designpatterns.creational.abstractfactory.button.Button;
import com.jms.designpatterns.creational.abstractfactory.textbox.TextBox;
import com.jms.designpatterns.creational.factory.CakeFactory;
import com.jms.designpatterns.creational.factory.CakeType;
import com.jms.designpatterns.creational.singleton.NonSingleton;
import com.jms.designpatterns.creational.singleton.ThreadSafeDoubleCheckedLocking;
import com.jms.designpatterns.creational.singleton.ThreadSafeEagerlyInitialized;
import com.jms.designpatterns.creational.singleton.ThreadSafeLazyInitialized;
import com.jms.designpatterns.structural.adapter.*;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    enum PATTERN {
        BEHAVIORAL_OBSERVER,
        CREATIONAL_ABSTRACT_FACTORY,
        CREATIONAL_FACTORY,
        CREATIONAL_SINGLETON,
        STRUCTURAL_ADAPTER
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display available patterns
            System.out.println("Available Patterns To Test:");
            for (PATTERN pattern : PATTERN.values()) {
                System.out.println(pattern.ordinal() + 1 + " - " + pattern);
            }

            // Get user input as a number
            System.out.print("Enter Pattern Number: ");

            int patternNumber;
            PATTERN selectedPattern;
            try {
                patternNumber = Integer.valueOf(scanner.next());
                selectedPattern = PATTERN.values()[patternNumber - 1];
                runSelectedPattern(selectedPattern);
            } catch (Exception e) {
                System.out.println("Invalid Pattern Number...");
            }

            System.out.print("Try Again? (y/n): ");
            String answer = scanner.next();
            if (!answer.equalsIgnoreCase("y")) {
                break;
            }
        }
        scanner.close();
    }

    private static void runSelectedPattern(PATTERN selectedPattern) throws InterruptedException {
        switch (selectedPattern) {
            case BEHAVIORAL_OBSERVER: {
                var group = new Group();
                var user1 = new User(1);
                var user2 = new User(2);
                var user3 = new User(3);
                group.addObserver(user1);
                group.addObserver(user2);
                group.addObserver(user3);
                group.notifyObservers("Hello from JMS!");
                group.removeObserver(user2);
                group.notifyObservers("It is a sunny day!");
                break;
            }
            case CREATIONAL_ABSTRACT_FACTORY: {
                // Display available patterns
                System.out.println("Available Operating Systems:");
                for (OperatingSystem operatingSystem : OperatingSystem.values()) {
                    System.out.println(operatingSystem.ordinal() + 1 + " - " + operatingSystem);
                }

                // Get user input as a number
                System.out.print("Enter Operating System Number: ");

                try {
                    Scanner scanner = new Scanner(System.in);
                    int enteredNumber = Integer.valueOf(scanner.next());
                    OperatingSystem selectedOperatingSystem = OperatingSystem.values()[enteredNumber - 1];

                    Factory factory = AbstractFactory.createFactory(selectedOperatingSystem);
                    Button button = factory.createButton();
                    button.displayButton();
                    TextBox textBox = factory.createTextBox();
                    textBox.displayTextBox();
                } catch (Exception e) {
                    System.out.println("Invalid Operating System Number...");
                }
                break;
            }
            case CREATIONAL_FACTORY: {
                log.info("The baker begins his work.");
                var chocolateCake = CakeFactory.getCake(CakeType.CHOCOLATE);
                var strawberryCake = CakeFactory.getCake(CakeType.STRAWBERRY);
                var vanillaCake = CakeFactory.getCake(CakeType.VANILLA);
                log.info(chocolateCake.getDescription());
                log.info(strawberryCake.getDescription());
                log.info(vanillaCake.getDescription());
                break;
            }
            case CREATIONAL_SINGLETON: {
                // Non-Singleton
                var nonSingleton1 = new NonSingleton();
                log.info("nonSingleton1={}", nonSingleton1);
                var nonSingleton2 = new NonSingleton();
                log.info("nonSingleton2={}", nonSingleton2);

                // Create multiple threads
                Thread thread1 = new Thread(() -> {
                    var ei1 = ThreadSafeEagerlyInitialized.getInstance();
                    log.info("Thread 1: {}", ei1);

                    var li1 = ThreadSafeLazyInitialized.getInstance();
                    log.info("Thread 1: {}", li1);

                    var dcl1 = ThreadSafeDoubleCheckedLocking.getInstance();
                    log.info("Thread 1: {}", dcl1);
                });

                Thread thread2 = new Thread(() -> {
                    var ei2 = ThreadSafeEagerlyInitialized.getInstance();
                    log.info("Thread 2: {}", ei2);

                    var li2 = ThreadSafeLazyInitialized.getInstance();
                    log.info("Thread 2: {}", li2);

                    var dcl2 = ThreadSafeDoubleCheckedLocking.getInstance();
                    log.info("Thread 2: {}", dcl2);
                });

                // Start & join the threads
                thread1.start();
                thread2.start();
                thread1.join();
                thread2.join();

                break;
            }
            case STRUCTURAL_ADAPTER: {
                Client client = new Client();
                client.processData(new JSONDataAnalyticsToolImpl("{\"name\": \"John Doe\"}"));
                XMLData xmlData = new XMLData("<root><name>John Doe</name></root>");
                client.processData(new XMLAdapter(xmlData));
                break;
            }
        }
    }

}
