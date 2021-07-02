package service;

import dataBaseConnectors.PersonDAO;
import dataBaseConnectors.UserDAO;

import java.util.Scanner;

public class UserService {
    private UserDAO userDAO;
    private PersonDAO personDAO;

    public UserService() {
        userDAO = new UserDAO ();
        personDAO = new PersonDAO ();
    }

    public void showAllUsers(){
        userDAO.getAllUser ().forEach (System.out::println);
    }

    public void showUserByLogin(){
        Scanner scanner = new Scanner (System.in);
        boolean rightInput;
        String login = "";


    }


}
