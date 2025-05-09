/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca2_2024578;

/**
 *
 * @author buyantugs
 */
public enum MenuOption {
    SORT, SEARCH, ADD, GENERATE, EXIT;

    public static MenuOption fromInt(int choice) {
        return switch (choice) {
            case 1 -> SORT;
            case 2 -> SEARCH;
            case 3 -> ADD;
            case 4 -> GENERATE;
            default -> EXIT;
        };
    }
}
