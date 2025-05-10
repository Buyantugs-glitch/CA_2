/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca2_2024578;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Buyantugs
 */
// This class handles search functionality in the employee list
public class Searching {
    public static Employee binarySearch(List<Employee> list, String name) {
        int left = 0, right = list.size() - 1;
        name = name.trim().toLowerCase();   // Clean and normalize the input name

        while (left <= right) {
            int mid = (left + right) / 2;
            String midName = list.get(mid).getName().toLowerCase(); // Normalize the mid-point name

            int comparison = midName.compareTo(name);   // Compare mid name with target

            if (comparison == 0)
                // Exact match found
                return list.get(mid);
            else if (comparison < 0)
                // Target is after mid point
                left = mid + 1;
            else
                // Target is before mid point
                right = mid - 1;
        }
        // If no match found
        return null;
    }
    
    public static List<Employee> partialSearch(List<Employee> list, String keyword) {
        keyword = keyword.trim().toLowerCase(); // Clean and normalize input
        List<Employee> results = new ArrayList<>();

        // Loop through all employees and check if name contains keyword
        for (Employee e : list) {
            if (e.getName().toLowerCase().contains(keyword)) {
                results.add(e); // Add to results if match is found
            }
        }
        return results; // Return all matching employees
    }
}