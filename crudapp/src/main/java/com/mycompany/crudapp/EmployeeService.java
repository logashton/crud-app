package com.mycompany.crudapp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private static final String JSON_FILE = "src/main/resources/employees.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public void createEmployee(Employee employee) {
        List<Employee> employees = getAllEmployees();
        employees.add(employee);
        saveEmployees(employees);
    }

    public List<Employee> getAllEmployees() {
        try {
            return mapper.readValue(new File(JSON_FILE), new TypeReference<List<Employee>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Read a specific employee record
    public Employee getEmployeeById(String employeeId) {
        return getAllEmployees().stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public void updateEmployee(Employee updatedEmployee) {
        List<Employee> employees = getAllEmployees();
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(updatedEmployee.getEmployeeId())) {
                employee.setName(updatedEmployee.getName());
                employee.setSalary(updatedEmployee.getSalary());
                break;
            }
        }
        saveEmployees(employees);
    }

    public void deleteAllEmployees() {
        saveEmployees(new ArrayList<>());
    }

    public void deleteEmployee(String employeeId) {
        List<Employee> employees = getAllEmployees();
        employees.removeIf(employee -> employee.getEmployeeId().equals(employeeId));
        saveEmployees(employees);
    }

    private void saveEmployees(List<Employee> employees) {
        try {
            mapper.writeValue(new File(JSON_FILE), employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
