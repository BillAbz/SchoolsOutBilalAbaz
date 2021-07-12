package service;

import dataBaseConnectors.ExamDAO;
import dataBaseConnectors.GradeDAO;
import dataBaseConnectors.PersonDAO;
import model.Exam;
import model.Grade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class GradeService {
    private GradeDAO gradeDAO;
    private ExamDAO examDAO;
    private PersonDAO personDAO;

    public GradeService() {
        gradeDAO = new GradeDAO ();
        examDAO = new ExamDAO ();
        personDAO = new PersonDAO ();
    }

    public void showAllGrades(){
        gradeDAO.getAllGrades ().forEach (System.out::println);
    }

    public void showGradeById(){
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

        Grade grade = gradeDAO.findGradeById ((long) id);
        if (grade!= null) System.out.println (grade);
        else System.out.println("This Id does not have a module");
    }

    public void addGrade(){
        Scanner scanner = new Scanner (System.in);

        examDAO.getAllExam ().forEach (System.out::println);
        System.out.println("Which one of these exam do you want to use? Type in a number.");
        boolean exist = false;
        int examId = 0;
        while (!exist){
            examId = makeACorrectId ();
            if (examDAO.getExamById ((long) examId)!= null) exist =true;
            else System.out.println ("Exam doesn't exist");
        }

        personDAO.getAllPersons ().forEach (System.out::println);
        System.out.println("Which one of these person do you want to use? Type in a number.");
        int personId = 0;
        while (!exist){
            personId = makeACorrectId ();
            if (personDAO.getPersonById (personId)!= null) exist = true;
            else System.out.println ("person doesn't exist");
        }

        System.out.println ("Insert grade (xx.xx)");
        BigDecimal grade = scanner.nextBigDecimal ();
        System.out.println ("Insert comment");
        String comment = scanner.next ();
        System.out.println ("Insert internal comment");
        String internalComment = scanner.next ();
        System.out.println ("Chose a absent (true/false)");
        boolean absent = scanner.nextBoolean ();
        System.out.println ("Chose a postponed (true/false)");
        boolean postponed = scanner.nextBoolean ();

        System.out.println ("Insert day for date");
        int day = scanner.nextInt ();
        System.out.println ("Insert month for date");
        int month = scanner.nextInt ();
        System.out.println ("Insert year for date");
        int year = scanner.nextInt ();
        LocalDate dateTime = LocalDate.of (year,month,day);


        Grade grade1 = new Grade (personDAO.getPersonById (personId), examDAO.getExamById ((long) examId),
                grade,comment,internalComment,absent,postponed,dateTime);
        gradeDAO.addGrade (grade1);

    }

    public void updateGrade(){
        gradeDAO.getAllGrades ().forEach (System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        boolean exist =false;
        long currentId = 0;
        while (!exist){
            currentId = makeACorrectId ();
            if (gradeDAO.findGradeById ((long) Math.toIntExact (currentId))!= null) exist=true;
            else System.out.println ("Grade doesn't exist");
        }
        Scanner scanner = new Scanner (System.in);
        Grade grade = gradeDAO.findGradeById (currentId);
        System.out.println("Do you want to change the person? NA for nothing");
        String answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            personDAO.getAllPersons ().forEach (System.out::println);
            System.out.println("Which one of these person do you want to use? Type in a number.");
            boolean existPerson = false;
            int currentPersonId = 0;
            while (!existPerson){
                currentPersonId = makeACorrectId();
                if (personDAO.getPersonById (currentPersonId)!= null) existPerson = true;
                else System.out.println("Course doesn't exist");
            }
            grade.setPerson (personDAO.getPersonById (currentPersonId));
        }

        System.out.println("Do you want to change the exam? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            examDAO.getAllExam ().forEach (System.out::println);
            System.out.println("Which one of these exam do you want to use? Type in a number.");
            boolean existExam = false;
            int currentExamId = 0;
            while (!existExam){
                currentExamId = makeACorrectId();
                if (examDAO.getExamById ((long) currentExamId)!= null) existExam = true;
                else System.out.println("Course doesn't exist");
            }
            grade.setExam (examDAO.getExamById ((long) currentExamId));
        }
        System.out.println("Do you want to change the comment? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String comment = scanner.next ();
            grade.setComment (comment);
        }

        System.out.println("Do you want to change the internal comment? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println ("What do you want to change it do?");
            String internalComment = scanner.next ();
            grade.setInternalComment (internalComment);
        }

        System.out.println("Do you want to change the absent? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println("What do you want to change it do? \n1: True \n2: False");
            boolean exists = false;
            int activeNumber = 0;
            boolean absent = false;

            while (!exists) {
                activeNumber = scanner.nextInt ();
                if (activeNumber == 1) {
                    absent = true; exists = true; }
                else if (activeNumber == 2) {
                    absent = false; exists = true; }
                else System.out.println("This absent status doesn't exist.");
            }

            grade.setAbsent (absent);
        }

        System.out.println("Do you want to change the postponed? NA for nothing");
        answer = scanner.next ();
        if (!answer.toUpperCase(Locale.ROOT).equals ("NA")){
            System.out.println("What do you want to change it do? \n1: True \n2: False");
            boolean exists = false;
            int activeNumber = 0;
            boolean postponed = false;

            while (!exists) {
                activeNumber = scanner.nextInt ();
                if (activeNumber == 1) {
                    postponed = true; exists = true; }
                else if (activeNumber == 2) {
                    postponed = false; exists = true; }
                else System.out.println("This active status doesn't exist.");
            }

            grade.setPostponed (postponed);
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

            grade.setDate (date);
        }


        gradeDAO.updateGrade (grade);
        System.out.println ("Grade updated!");
    }

    public void deleteGrade(){
        Scanner scanner = new Scanner (System.in);
        gradeDAO.getAllGrades ().forEach (System.out::println);
        System.out.println ("Give id of grade you want to delete:");
        int id = giveExistingPersonId ();
        System.out.println("Are you sure you want to delete this grade:");
        System.out.println(gradeDAO.findGradeById ((long) id));
        System.out.println("Y/N");
        String answer = scanner.next ();
        if (answer.toUpperCase(Locale.ROOT).equals ("Y")){
            gradeDAO.deleteGrade (gradeDAO.findGradeById ((long) id));
            System.out.println ("Grade has been deleted");
        }else System.out.println ("Grade has not been deleted");
    }



    private int giveExistingPersonId() {
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (gradeDAO.findGradeById ((long) currentId)!= null) exist = true;
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
