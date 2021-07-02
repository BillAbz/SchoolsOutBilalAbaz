import service.CourseService;
import service.ExamService;
import service.ModuleService;
import service.PersonService;

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
                System.out.println("What do you want to look at? \n1: Person \n2: Course\n3: Module\n4: Exam\n0: End");
                choiceOne = scanner.nextInt();
                if(choiceOne==0)break;
                if(choiceOne<1||choiceOne>4){
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

            if (choiceTwo!=0)
                if (choiceOne==1){

                    switch (choiceTwo){
                        case 1:personService.showAllPerson ();break;//see All Person
                        case 2:personService.showPersonById ();break;//see One Person By Id
                        case 3:personService.addPerson ();break;//add One new Country
                        case 4:personService.updatePerson ();break;//edit One Country
                        case 5:personService.deleteAPerson ();break;//delete One Country
                    }
                    System.out.println("We did a Person thing!");


                } else if (choiceOne==2) {
                    switch (choiceTwo) {
                        case 1:
                            courseService.showAllCourses ();
                            break;//TODO see All Continents
                        case 2:
                            courseService.showCourseById ();
                            break;//TODO see One Continent By Id
                        case 3:
                            courseService.addCourse ();
                            break;//TODO add One new Continent
                        case 4:
                            courseService.updateCourse ();
                            break;//TODO edit One Continent
                        case 5:
                            courseService.deleteACourse ();
                            break;//TODO delete One Continent
                    }
                    System.out.println ("We did a course thing!");

                }else if (choiceOne==3){
                    switch (choiceTwo) {
                        case 1:moduleService.showAllModules ();
                            break;//TODO see All City
                        case 2:moduleService.showModuleById ();
                            break;//TODO see One City By Id
                        case 3:moduleService.addModule ();
                            break;//TODO add One new City
                        case 4:moduleService.updateModule ();
                            break;//TODO edit One City
                        case 5:moduleService.deleteAModule ();
                            break;//TODO delete One City
                    }
                    System.out.println("We did a module thing!");

                }else if (choiceOne==4){
                    switch (choiceTwo) {
                        case 1:examService.showAllExams ();
                            break;//TODO see All City
                        case 2:examService.showExamById ();
                            break;//TODO see One City By Id
                        case 3:examService.addExam ();
                            break;//TODO add One new City
                        case 4:examService.updateExam ();
                            break;//TODO edit One City
                        case 5:examService.deleteAExam ();
                            break;//TODO delete One City
                    }
                    System.out.println("We did a module thing!");

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

