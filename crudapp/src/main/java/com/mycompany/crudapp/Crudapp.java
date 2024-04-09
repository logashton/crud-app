package com.mycompany.crudapp;

import java.util.Scanner;
import com.mycompany.crudapp.EmployeeService;
import com.mycompany.crudapp.Employee;

public class Crudapp {
    private static final EmployeeService employeeService = new EmployeeService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Employee Database");
            System.out.println("1. View all records");
            System.out.println("2. Update a record");
            System.out.println("3. Create a record");
            System.out.println("4. Delete all records");
            System.out.println("5. View a record in more detail");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllRecords();
                    break;
                case 2:
                    updateRecord();
                    break;
                case 3:
                    createRecord();
                    break;
                case 4:
                    deleteAllRecords();
                    break;
                case 5:
                    viewRecord();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void viewAllRecords() {
        employeeService.getAllEmployees().forEach(System.out::println);
    }

    private static void updateRecord() {
        System.out.print("Enter employee ID to update: ");
        String employeeId = scanner.nextLine();
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new salary: ");
            double salary = scanner.nextDouble();
            employee.setName(name);
            employee.setSalary(salary);
            employeeService.updateEmployee(employee);
            System.out.println("Record updated successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void createRecord() {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setName(name);
        employee.setSalary(salary);

        employeeService.createEmployee(employee);
        System.out.println("Record created successfully!");
    }

    private static void deleteAllRecords() {
        employeeService.deleteAllEmployees();
        System.out.println("All records deleted successfully!");
    }

    private static void viewRecord() {
        System.out.print("Enter employee ID to view details: ");
        String employeeId = scanner.nextLine();
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("Employee not found!");
        }
    }
}

