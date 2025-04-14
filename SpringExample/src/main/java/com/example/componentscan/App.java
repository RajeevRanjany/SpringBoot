package com.example.componentscan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("componentScanDemo.xml");

//        Employee employee = context.getBean("employee", Employee.class); // the second parameter is to replace casting
        Employee employee = context.getBean("employee1", Employee.class); // the second parameter is to replace casting
        System.out.println(employee.toString());
    }
}
