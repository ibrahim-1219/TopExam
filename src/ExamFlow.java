import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class ExamFlow extends  DatabaseManagement {

    public static void examFlow(Student student) {
        int counter =0;
        int[] arr ={0,0,0,0,0};
        int correctAnswer=0,wrongAnswer=0;
        while (counter<5)
        {
            Random rand = new Random();
            int upperbound = DatabaseManagement.DB.getMaxId();
            int int_random = rand.nextInt(upperbound);
            if(DatabaseManagement.DB.searchId(int_random) & ExamFlow.searchRepititon(int_random,arr))
            {
                counter++;
                arr[counter-1]=int_random;
            }
            else
            {
                continue;
            }
            if(generateQuestions(int_random,counter))
            {
                correctAnswer++;
            }
            else
            {
                wrongAnswer++;
            }

        }
        addExamMark(student,correctAnswer);
        System.out.println("Correct Answers is "+correctAnswer);
        System.out.println("Wrong Answers is "+wrongAnswer);
        System.out.println("Your Exam Mark is "+(correctAnswer*20));
    }


    public static boolean searchRepititon(int int_random, int[] arr) {
        for (int i=0 ;i<5 ;i++)
        {
            if(int_random == arr[i])
            {
                return false;
            }
        }
        return true;
    }





    public static boolean generateQuestions(int int_random,int counter)
    {
        Scanner input =new Scanner(System.in);
        ResultSet result;
        int answer;
        boolean b =false;
        try
        {
            statement =connect_database.createStatement();
            String query="SELECT * FROM `questions` WHERE question_id="+ int_random +";";
            result =statement.executeQuery(query);
            result.next();
            int db_answer= result.getInt(3);
            System.out.println("question NO "+ counter +": "+ result.getString(2));
            do {
                System.out.println("Enter The Answer (1 For True or 0 For False) :");
                answer = input.nextInt();
            }while (answer !=1 & answer!=0);
            if(answer==db_answer)
            {
                return true;
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return  b;
    }






    public static void addExamMark(Student student, int correctAnswer)
    {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            statement=connect_database.createStatement();
            String query="INSERT INTO `examsmarks`(`student_id`, `exam mark`, `Time of Exam`) VALUES ('"+student.getId()+"','"+(correctAnswer*20)+"','"+dtf.format(now)+"')";
            statement.execute(query);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }



}
