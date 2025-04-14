package com.example.autowired.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.componentscan.annotation");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee employee = context.getBean("employee", Employee.class); // the second parameter is to replace casting
        Manager manager = context.getBean("manager", Manager.class); // the second parameter is to replace casting
        System.out.println(employee.toString());

        System.out.println(manager.toString());
    }
}
