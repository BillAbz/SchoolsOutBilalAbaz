package service;

import dataBaseConnectors.PersonDAO;
import dataBaseConnectors.UserDAO;
import model.User;

import java.util.Scanner;

public class UserService {
    private UserDAO userDAO;
    private PersonDAO personDAO;
    private Scanner scanner = new Scanner(System.in);

    public UserService() {
        userDAO = new UserDAO ();
        personDAO = new PersonDAO ();
    }


    public void showAllUsers(){
        userDAO.getAllUser ().forEach (System.out::println);
    }

    public void getUserByLogin(){
        System.out.println("What is your login?");
        String login = scanner.next();
        User user = userDAO.getUserById (login);
        System.out.println(user);
    }


}
