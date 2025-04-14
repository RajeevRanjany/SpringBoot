package com.example.componentscan.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.componentscan.annotation");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee employee = context.getBean("employee1", Employee.class); // the second parameter is to replace casting
        System.out.println(employee.toString());
    }
}
