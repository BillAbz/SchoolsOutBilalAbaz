package service;

import dataBaseConnectors.ExamDAO;
import dataBaseConnectors.ModuleDAO;
import model.Exam;
import model.Module;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class ExamService {
    private ExamDAO examDAO;
    private ModuleDAO moduleDAO;

    public ExamService() {
        examDAO = new ExamDAO ();
        moduleDAO = new ModuleDAO ();
    }

    public void showAllExams(){
        examDAO.getAllExam ().forEach (System.out::println);
    }

    public void showExamById(){
        Scanner scanner = new Scanner(System.in);
        boolean rightInput;
        int id= 0;
        do {
            System.out.println("Insert Id");
            try {
                id = Integer.parseInt(scanner.next());
                scanner.nextLine();
                rightInput= true;
            } catch (NumberFormatException e) {
                rightInput =false;
                System.out.println("Id is not correct");
            }
        }while (!rightInput);

        Exam exam = examDAO.getExamById ((long) id);
        if (exam!= null) System.out.println (exam);
        else System.out.println("This Id does not have a module");
    }

    public void addExam(){

        Scanner scanner = new Scanner(System.in);

        moduleDAO.getAllModules ().forEach (System.out::println);
        System.out.println("Which one of these module do you want to use? Type in a number.");
        boolean exist = false;
        int currentId = 0;
        while (!exist){
            currentId = makeACorrectId();
            if (moduleDAO.getModuleById ((long) currentId)!= null) exist = true;
            else System.out.println("Module doesn't exist");
        }
        System.out.println("Insert name");
        String name = scanner.next();
        System.out.println("Insert description");
        String description = scanner.next();


        System.out.println ("Insert day for exam date");
        int day = scanner.nextInt ();
        System.out.println ("Insert month for exam date");
        int month = scanner.nextInt ();
        System.out.println ("Insert year for exam date");
        int year = scanner.nextInt ();
        LocalDate dateTime = LocalDate.of (year,month,day);


        System.out.println ("Insert weight");
        int weight = scanner.nextInt ();

        System.out.println ("Insert total");
        int total = scanner.nextInt ();

        Exam exam = new Exam (name,description,dateTime,weight,total, moduleDAO.getModuleById ((long) currentId));
        examDAO.addExam (exam);
    }

    public void updateExam(){
        examDAO.getAllExam ().forEach (System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        boolean exist =false;
        long currentId = 0;
        while (!exist){
            currentId = makeACorrectId ();
            if (examDAO.getExamById ((long) Math.toIntExact (currentId))!= null) exist=true;
            else System.out.println ("Exam doesn't exist");
        }
        Scanner scanner = new Scanner (System.in);
        Exam exam = examDAO.getExamById (currentId);
        System.out.println("Do you want to change the name? NA for nothing");
        String answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String name = scanner.next ();
            exam.setName (name);
        }

        System.out.println("Do you want to change the description? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String description = scanner.next ();
            exam.setDescription (description);
        }

        System.out.println("Do you want to change the date? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("Insert day for exam date");
            int day = scanner.nextInt ();
            System.out.println ("Insert month for exam date");
            int month = scanner.nextInt ();
            System.out.println ("Insert year for exam date");
            int year = scanner.nextInt ();
            LocalDate date = LocalDate.of (year,month,day);

            exam.setDate (date);
        }

        System.out.println("Do you want to change the weight? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            int weight = scanner.nextInt ();
            exam.setWeight (weight);
        }
        System.out.println("Do you want to change the total? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            int total = scanner.nextInt ();
            exam.setTotal (total);
        }

        System.out.println("Do you want to change the module? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            moduleDAO.getAllModules ().forEach (System.out::println);
            System.out.println("Which one of these course do you want to use? Type in a number.");
            boolean existCourse = false;
            int currentCourseId = 0;
            while (!existCourse){
                currentCourseId = makeACorrectId();
                if (moduleDAO.getModuleById ((long) currentCourseId)!= null) existCourse = true;
                else System.out.println("Course doesn't exist");
            }
            exam.setModule (moduleDAO.getModuleById ((long) currentCourseId));
        }


        examDAO.updateExam (exam);
        System.out.println ("Exam updated!");
    }

    public void deleteAExam() throws SQLException {
        Scanner scanner = new Scanner (System.in);
        examDAO.getAllExam ().forEach (System.out::println);
        System.out.println ("Give id of module you want to delete:");
        int personId = giveExistingPersonId ();
        System.out.println("Are you sure you want to delete this city:");
        System.out.println(examDAO.getExamById ((long) personId));
        System.out.println("Y/N");
        String answer = scanner.next ();
        if (answer.toUpperCase(Locale.ROOT).equals ("Y")){
            examDAO.deleteExam (examDAO.getExamById ((long) personId));
            System.out.println ("Exam has been deleted");
        }else System.out.println ("Exam has not been deleted");
    }





    private int giveExistingPersonId() {
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (examDAO.getExamById ((long) currentId)!= null) exist = true;
            else System.out.println("Person doesn't exist");
        }
        return currentId;
    }

    private int makeACorrectId(){
        Scanner scanner = new Scanner(System.in);
        boolean rightInput;
        int id= 0;
        do {
            System.out.println("Insert Id");
            try {
                id = Integer.parseInt(scanner.next());
                scanner.nextLine();
                rightInput= true;
            } catch (NumberFormatException e) {
                rightInput =false;
                System.out.println("Id is not correct");
            }
        }while (!rightInput);
        return id;
    }
}
