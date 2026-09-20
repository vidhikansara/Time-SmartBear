package org.example;

import org.example.format.BritishTimeFormatter;
import org.example.model.ClockTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BritishTimeFormatter formatter = new BritishTimeFormatter();
        for (String arg : args) {
            try {
                System.out.println(arg + " -> " + formatter.format(ClockTime.parse(arg)));
            } catch (IllegalArgumentException e) {
                System.out.println(arg + " -> invalid input: " + e.getMessage());
            }
        }
    }
}