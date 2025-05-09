/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2_2024578;

import static ca2_2024578.MenuOption.ADD;
import static ca2_2024578.MenuOption.EXIT;
import static ca2_2024578.MenuOption.GENERATE;
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
       String filename = "C:\\Users\\buyan\\Documents\\NetBeansProjects\\CA2_2024578\\src\\ca2_2024578\\Application_Form.txt";
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
                    break;
                case ADD:
                    addEmployee(sc);
                    break;
                case GENERATE:
                    System.out.print("How many random employees to generate? ");
                    int count = sc.nextInt();
                    sc.nextLine(); // consume newline
                    generateRandomEmployeesFromFile("C:\\Users\\buyan\\Documents\\NetBeansProjects\\CA2_2024578\\src\\ca2_2024578\\Application_Form.txt", count);
                    break;
                case EXIT:
                    System.out.println("Exiting...");
                    break;
            }
        } while (option != MenuOption.EXIT);
    }

    // Displays the menu options
    static void showMenu() {
        System.out.println("\nSelect an option:");
        for (MenuOption mo : MenuOption.values())
            System.out.println(mo.ordinal() + 1 + ". " + mo);
        System.out.println("Select an option: ");   // Ask the user for a choice.
    }

    // Reads employees from a text file
    static void readFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        Random random = new Random();
        while ((line = br.readLine()) != null) {
            String name = line.trim();
            
            // Randomly assign Manager, Department, and Role
            ManagerType mt = ManagerType.values()[random.nextInt(ManagerType.values().length)];
            Department dept = Department.values()[random.nextInt(Department.values().length)];
            EmployeeRole role = EmployeeRole.values()[random.nextInt(EmployeeRole.values().length)];
            employees.add(new Employee(name, mt, dept, role));
        }
        br.close();
    }

    // Adds a new employee with user input
    static void addEmployee(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.println("Select Manager Type:");
        for (int i = 0; i < ManagerType.values().length; i++)
            System.out.println((i + 1) + ". " + ManagerType.values()[i]);
        ManagerType mt = ManagerType.values()[sc.nextInt() - 1];

        System.out.println("Select Department:");
        for (int i = 0; i < Department.values().length; i++)
            System.out.println((i + 1) + ". " + Department.values()[i]);
        Department dept = Department.values()[sc.nextInt() - 1];

        System.out.println("Select Role:");
        for (int i = 0; i < EmployeeRole.values().length; i++)
            System.out.println((i + 1) + ". " + EmployeeRole.values()[i]);
        EmployeeRole role = EmployeeRole.values()[sc.nextInt() - 1];
        sc.nextLine(); // consume newline

        employees.add(new Employee(name, mt, dept, role));
        System.out.println(name + " added successfully!");
    }   
    
    // Generates 'count' random employees using names from file and prints only the newly generated ones
    static void generateRandomEmployeesFromFile(String filename, int count) {
        List<String> namesFromFile = new ArrayList<>();
        List<Employee> newEmployees = new ArrayList<>();
        Random random = new Random();

        // Read names from file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String name = line.trim();
                if (!name.isEmpty()) {
                    namesFromFile.add(name);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return;
        }

        if (namesFromFile.isEmpty()) {
            System.out.println("No names found in file.");
            return;
        }

        // Generate 'count' random employees using names from file
        for (int i = 0; i < count; i++) {
            String name = namesFromFile.get(random.nextInt(namesFromFile.size()));

            ManagerType mt = ManagerType.values()[random.nextInt(ManagerType.values().length)];
            Department dept = Department.values()[random.nextInt(Department.values().length)];
            EmployeeRole role = EmployeeRole.values()[random.nextInt(EmployeeRole.values().length)];

            Employee emp = new Employee(name, mt, dept, role);
            employees.add(emp);         // Add to main list
            newEmployees.add(emp);      // Also store in separate list for display
        }

        // Only display the new employees just generated
        System.out.println("\n" + count + " random employees generated from file:");
        newEmployees.forEach(System.out::println);
    }
}
