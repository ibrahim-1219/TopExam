import java.util.Scanner;

public class StudentFlow {

    public StudentFlow(Student student) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("******************************************************");
            System.out.println("Select 1 to View My Exams Marks :\nSelect 2 to Start the Exam :\nSelect 3 to Log out :");
            String op = input.next();
            if (op.equals("1")) {
                DatabaseManagement.DB.viewMyMarks(student);
                continue;
            }
            if (op.equals("2")) {
                ExamFlow.examFlow(student);
                continue;
            }
            if (op.equals("3")) {
                DatabaseManagement.DB.logout();

            } else {
                System.out.println("Please Enter a valid Number :");
            }
        }while (true);
    }
    }

