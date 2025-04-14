package com.example.ChallengeOne;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCExample {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


//        UserDataProvider databaseProvider = new UserDatabaseProvider();
//        UserManager userManagerWithDB = new UserManager(databaseProvider);

        UserManager userManagerWithDB = (UserManager) context.getBean("userManagerWithuserDatabaseProvider");
        System.out.println(userManagerWithDB.getUserInfo());

//        UserDataProvider webServiceProvider = new WebServiceDataProvider();
//        UserManager userManagerWithWS = new UserManager(webServiceProvider);
        UserManager userManagerWithWS = (UserManager) context.getBean("userManagerWithwebServiceDataProvider");
        System.out.println(userManagerWithWS.getUserInfo());


//        UserDataProvider newDatabaseProvider = new NewDatabaseProvider();
//        UserManager userManagerWithNewDB = new UserManager(newDatabaseProvider);

        UserManager userManagerWithNewDB = (UserManager) context.getBean("userManagerWithnewDatabaseProvider");
        System.out.println(userManagerWithNewDB.getUserInfo());

    }
}
