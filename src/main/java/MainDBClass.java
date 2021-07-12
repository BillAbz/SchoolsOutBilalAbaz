import service.*;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class MainDBClass {

        private static int choiceOne= 9;
        private static int choiceTwo= 9;
        private static boolean continueThis= true;

        public static void main(String[] args) {

            try {

                while (continueThis){
                    getChoice();
                    choices();
                }



            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public static void getChoice(){
            Scanner scanner = new Scanner(System.in);

            while (choiceOne==9) {
                System.out.println("What do you want to look at? \n1: Person \n2: Course\n3: Module\n4: Exam\n5: Grade\n6: User\n0: End");
                choiceOne = scanner.nextInt();
                if(choiceOne==0)break;
                if(choiceOne<1||choiceOne>6){
                    choiceOne = 9;
                    System.out.println("Invalid choice.");
                }else {
                    while (choiceTwo==9) {
                        System.out.println("What do you want to look at? \n1: See All \n2: See One \n3: Add One \n4: Edit One \n5: Delete One\n0: End");
                        choiceTwo = scanner.nextInt();
                        if (choiceTwo==0)break;
                        if(choiceTwo<1||choiceTwo>5){
                            choiceTwo = 9;
                            System.out.println("Invalid choice.");
                        }
                    }
                }
            }

        }


        private static void choices() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            PersonService personService = new PersonService ();
            CourseService courseService = new CourseService ();
            ModuleService moduleService = new ModuleService ();
            ExamService examService = new ExamService ();
            GradeService gradeService = new GradeService ();
            UserService userService = new UserService ();

            if (choiceTwo!=0)
                if (choiceOne==1){

                    switch (choiceTwo){
                        case 1:personService.showAllPerson ();break;
                        case 2:personService.showPersonById ();break;
                        case 3:personService.addPerson ();break;
                        case 4:personService.updatePerson ();break;
                        case 5:personService.deleteAPerson ();break;
                    }
                    System.out.println("We did a Person thing!");


                } else if (choiceOne==2) {
                    switch (choiceTwo) {
                        case 1:
                            courseService.showAllCourses ();
                            break;//TODO see All Course
                        case 2:
                            courseService.showCourseById ();
                            break;//TODO see One Course By Id
                        case 3:
                            courseService.addCourse ();
                            break;//TODO add One new Course
                        case 4:
                            courseService.updateCourse ();
                            break;//TODO edit One Course
                        case 5:
                            courseService.deleteACourse ();
                            break;//TODO delete One Course
                    }
                    System.out.println ("We did a course thing!");

                }else if (choiceOne==3){
                    switch (choiceTwo) {
                        case 1:moduleService.showAllModules ();
                            break;//TODO see All Module
                        case 2:moduleService.showModuleById ();
                            break;//TODO see One Module By Id
                        case 3:moduleService.addModule ();
                            break;//TODO add One new Module
                        case 4:moduleService.updateModule ();
                            break;//TODO edit One Module
                        case 5:moduleService.deleteAModule ();
                            break;//TODO delete One Module
                    }
                    System.out.println("We did a module thing!");

                }else if (choiceOne==4){
                    switch (choiceTwo) {
                        case 1:examService.showAllExams ();
                            break;//TODO see All Exam
                        case 2:examService.showExamById ();
                            break;//TODO see One Exam By Id
                        case 3:examService.addExam ();
                            break;//TODO add One new Exam
                        case 4:examService.updateExam ();
                            break;//TODO edit One Exam
                        case 5:examService.deleteAExam ();
                            break;//TODO delete One Exam
                    }
                    System.out.println("We did a Exam thing!");

                }else if (choiceOne==5) {
                    switch (choiceTwo) {
                        case 1:
                            gradeService.showAllGrades ();
                            break;//TODO see All Grade
                        case 2:
                            gradeService.showGradeById ();
                            break;//TODO see One Grade By Id
                        case 3:
                            gradeService.addGrade ();
                            break;//TODO add One new Grade
                        case 4:
                            gradeService.updateGrade ();
                            break;//TODO edit One Grade
                        case 5:
                            gradeService.deleteGrade ();
                            break;//TODO delete One Grade
                    }
                    System.out.println ("We did a Grade thing!");
                }else if (choiceOne==6) {
                    switch (choiceTwo) {
                        case 1:
                            userService.showAllUsers ();
                            break;//TODO see All users
                        case 2:
                            userService.getUserByLogin ();
                            break;//TODO see One user By login

                    }
                    System.out.println ("We did a user thing!");
                }
            choiceOne =9;
            choiceTwo =9;
            boolean goodAnswer;
            do {
                System.out.println("Do you want to Try again? Y/N");
                String answer = scanner.next();
                if (answer.toUpperCase(Locale.ROOT).equals("N")){
                    System.out.println("Bye!");
                    continueThis = false;
                    break;
                }
                if (!answer.toUpperCase(Locale.ROOT).equals("Y")) {
                    goodAnswer = false;
                    System.out.println(answer+ " is not a good answer.");
                }
                else goodAnswer = true;
            }while (!goodAnswer);



        }


}

