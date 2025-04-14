package com.example.componentscan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component("employee")

@Component("employee1")
public class Employee {
    @Value("1")
    private int EmployeeId;
    @Value("Rajeev")
    private String firstName;
    @Value("Ranjan")
    private String lastName;
    @Value("#{4*7*100*1200}")
    private double salary;
    @Value("${java.home}")
    private String javaPathHome;


    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeId=" + EmployeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", javaPathHome='" + javaPathHome + '\'' +
                '}';
    }
}
