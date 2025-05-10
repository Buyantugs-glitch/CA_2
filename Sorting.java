/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca2_2024578;

import java.util.List;

/**
 *
 * @author Buyantugs
 */
// Recursive insertion sort implementation
public class Sorting {
    
    // Recursively sorts the list of employees based on their name (alphabetical order)
    public static void recursiveInsertionSort(List<Employee> list, int n) {
        if (n <= 1) return;

        // Sort the first n-1 elements
        recursiveInsertionSort(list, n - 1);

        Employee last = list.get(n - 1);
        int j = n - 2;

        // Move elements that are greater than 'last' one position ahead
        while (j >= 0 && list.get(j).getName().compareToIgnoreCase(last.getName()) > 0) {
            list.set(j + 1, list.get(j));
            j--;
        }
        list.set(j + 1, last);
    }
}
