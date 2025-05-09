/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca2_2024578;

/**
 *
 * @author buyan
 */
public class Employee { 
    private String name;
    private ManagerType managerType;
    private Department department;
    private EmployeeRole role;

    // Constructor
    public Employee(String name, ManagerType managerType, Department department, EmployeeRole role) {   
        this.name = name;
        this.managerType = managerType;
        this.department = department;
        this.role = role;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public Department getDepartment() {
        return department;
    }

    public EmployeeRole getRole() {
        return role;
    }

    // Display employee details
    @Override
    public String toString() {
        return name + " | " + managerType + " | " + department + " | " + role;
    }
}