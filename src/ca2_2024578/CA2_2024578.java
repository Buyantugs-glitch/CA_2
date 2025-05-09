/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2_2024578;

import static ca2_2024578.MenuOption.SEARCH;
import static ca2_2024578.MenuOption.SORT;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author buyantugs
 */
public class CA2_2024578 {

    /**
     * @param args the command line arguments
     */
    
    static List<Employee> employees = new ArrayList<>();
    
    public static void main(String[] args) {
       String filename = "C:\\Users\\buyan\\Desktop\\CA2_2024578\\CA2_2024578\\src\\ca2_2024578\\Applicants_Form.txt";
        try {
            readFromFile(filename);
            System.out.println("File read successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }   
        Scanner sc = new Scanner(System.in); 

        // Show the menu until the user chooses to exit
        MenuOption option;
        do {
            showMenu();
            option = MenuOption.fromInt(sc.nextInt());
            sc.nextLine(); // consume newline
            switch (option) {
                case SORT:
                    System.out.println("\nFirst 20 employees in alphabetical sort: ");
                    Sorting.recursiveInsertionSort(employees, employees.size());
                    employees.stream().limit(20).forEach(System.out::println);
                    break;
                case SEARCH:
                    Sorting.recursiveInsertionSort(employees, employees.size());

                    System.out.print("Enter name to search: ");
                    String name = sc.nextLine();

                    System.out.println("Do you want exact match or partial match? (e/p): ");
                    String type = sc.nextLine().trim().toLowerCase();

                    if (type.equals("e")) {
                        // Exact match using Binary Search
                        Employee found = Searching.binarySearch(employees, name);
                        System.out.println(found != null ? found : "Not found.");
                    } else {
                        // Partial match using linear scan
                        List<Employee> results = Searching.partialSearch(employees, name);
                        if (results.isEmpty()) {
                            System.out.println("No matching names found.");
                        } else {
                            System.out.println("Matching results:");
                            results.forEach(System.out::println);
                        }
                    }
