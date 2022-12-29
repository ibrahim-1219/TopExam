import java.util.Scanner;

public class AdminFlow {

    public AdminFlow()
    {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("******************************************************");
            System.out.println("Select 1 to View All Questions :\nSelect 2 to View Students Exam Marks :\nSelect 3 to Add a question :\nSelect 4 to Update a question :\nSelect 5 to delete a question :\nSelect 6 to Log out ");
            String op = input.next();
            if (op.equals("1")) {
                DatabaseManagement.DB.viewAllQuestions();
                continue;
            }
            if (op.equals("2")) {
                DatabaseManagement.DB.viewStudentsMarks();
                continue;
            }
            if (op.equals("3")) {
                DatabaseManagement.DB.addQuestion();
                continue;
            }
            if (op.equals("4")) {
                DatabaseManagement.DB.updateQuestion();
                continue;
            }
            if (op.equals("5")) {
                DatabaseManagement.DB.deleteQuestion();
                continue;
            }
            if (op.equals("6")) {
                DatabaseManagement.DB.logout();

            } else {
                System.out.println("Please Enter a valid Number :");
            }
        }while (true);
    }
}