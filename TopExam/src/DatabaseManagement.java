import java.sql.*;
import java.util.Random;
import java.util.Scanner;


public class DatabaseManagement {
    private final String url="jdbc:mysql://localhost/topexam";
    private final String user="root";
    private final String password="";
    private Connection connect_database;
    private Statement statement ;

    public static DatabaseManagement DB =new DatabaseManagement();

    public DatabaseManagement(){
        try {
            connect_database=DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean checkNewUser(String email)
    {
        boolean b = true;
        try {
            statement = connect_database.createStatement();
            ResultSet result;
            String query = "SELECT * FROM `students` WHERE email ='"+ email +"';";
            result = statement.executeQuery(query);
            result.next();
            String db_email = result.getString(3);
            if (email.equals(db_email)) {
                System.out.println("This Email is Already Exists");
                return false;
            }

        }

        catch (SQLException ex)
        {
            //System.out.println(ex.getMessage());
        }
        return b;


    }

    public Student addStudent(String name,String clas ,String email ,String password)
    {
        Student student = new Student();
        try
        {
            statement = connect_database.createStatement();
            String query = "INSERT INTO `students`(`name`, `email`, `password`, `class`) " +
                    "VALUES ('" + name + "','" + email + "','" + password + "','" + clas + "')";
            statement.execute(query);
            System.out.println("Welcom " + name + " to our Site");

            student.setName(name);
            student.setEmail(email);
            student.setClas(clas);
            student.setPassword(password);
            //result=statement.executeQuery("SELECT * FROM student WHERE email ='"+email+"';" );
            //result.next();
            //student.se
        }
        catch (SQLException ex )
        {
            System.out.println(ex.getMessage());
        }
        return student;
    }

    public Student checkExistingUser(String email, String password)
    {
        Student student=null;
        try {
            statement = connect_database.createStatement();
            ResultSet result;
            String query = "SELECT * FROM `students` WHERE email ='"+ email +"' AND password ='"+ password +"';";
            result = statement.executeQuery(query);
            result.next();
            String db_email = result.getString(3);
            String db_password = result.getString(4);
            if (email.equals(db_email) && password.equals(db_password)) {
                student=studentinfo(email);
            }

        }

        catch (SQLException ex)
        {
            //System.out.println(ex.getMessage());
        }
        return student;

    }

    public Student studentinfo(String email) {
        Student student = new Student();
        try
        {
            statement = connect_database.createStatement();
            ResultSet result ;
            String query = "SELECT * FROM `students` WHERE email = '"+ email +"';";
            result=statement.executeQuery(query);
            result.next();
            student.setId(result.getInt(1));
            student.setName(result.getString(2));
            student.setEmail(result.getString(3));
            student.setPassword(result.getString(4));
            student.setClas(result.getString(6));
            student.setIs_admin(result.getBoolean(5));
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return student;

    }

    public void viewAllQuestions() {
        ResultSet result ;
        try
        {
            statement = connect_database.createStatement();
            result=statement.executeQuery("SELECT `question_id`, `question`, `answer` FROM `questions`");
            System.out.println("id  question                                                                      answer");
            while (result.next())
            {
                System.out.print(result.getString(1)+"   ");
                System.out.print(result.getString(2));
                System.out.printf("%50s",result.getBoolean(3));
                System.out.println();

            }

        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void addQuestion() {
        Scanner input = new Scanner(System.in);
        try
        {
            System.out.println("Enter the question :");
            String question = input.nextLine();
            int answer;
            do {
                System.out.println("Enter the Answer (1 For True or 0 For False) :");
                answer = input.nextInt();
            }while (answer !=1 & answer!=0);
            statement=connect_database.createStatement();
            String query = "INSERT INTO `questions`(`question`, `answer`) VALUES ('"+ question +"','"+ answer +"')";
            statement.execute(query);
            System.out.println("Question Added");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void updateQuestion() {
        Scanner input = new Scanner(System.in);
        Scanner n = new Scanner(System.in);
        try
        {      int q_id;
            do {
                System.out.println("Enter The Questin id :");
                q_id = input.nextInt();
            } while (!searchId(q_id));
            System.out.println("Enter the new Question :");
            String question = n.nextLine();
            int answer;
            do {
                System.out.println("Enter The Answer (1 For True or 0 For False) :");
                answer = input.nextInt();
            }while (answer !=1 & answer!=0);
            statement = connect_database.createStatement();
            String query = "UPDATE `questions` SET `question_id`='"+q_id+"',`question`='"+ question +"',`answer`='"+ answer +"' WHERE `question_id`='"+ q_id +"';";
            statement.execute(query);
            System.out.println("Question Updated");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }



    public void deleteQuestion() {
        Scanner input = new Scanner(System.in);
        try
        {   int q_id;
            do {
                System.out.println("Enter The Questin id :");
                q_id = input.nextInt();
            }while (searchId(q_id));
            statement = connect_database.createStatement();
            String query = "DELETE FROM `questions` WHERE question_id = "+ q_id +";";
            statement.execute(query);
            System.out.println("Question Deleted");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private boolean searchId(int id ) {
        boolean b =true;
        try
        {   ResultSet result;
            statement = connect_database.createStatement();
            String query= "SELECT * FROM `questions` WHERE question_id = "+id+";";
            result=statement.executeQuery(query);
            result.next();
            String w =result.getString(2);
        }
        catch (SQLException ex)
        {
            b=false;
            //System.out.println("This question_id doesnt exists!");
        }
        return b;
    }
    public void logout() {
        try{
            connect_database.close();
            statement.close();
            System.exit(0);
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void viewMyMarks(Student student) {
        ResultSet result;
        double sum =0;
        int counter=0;
        double res=0;
        try
        {
            statement =connect_database.createStatement();
            String query ="SELECT `id`,`name`,`exam mark` FROM `students` INNER JOIN examsmarks WHERE students.id = examsmarks.student_id And id =" + student.getId() + ";";
            result=statement.executeQuery(query);
            if(result.next()){
                   System.out.println("id   name               exam mark");
                }
            do {
                System.out.print(result.getString(1)+"   ");
                System.out.print(result.getString(2)+"          ");
                System.out.println(result.getInt(3));
                sum+=result.getInt(3);
                counter++;
            }while(result.next());
            res=sum/counter;
            if(sum!=0){
                System.out.print("YOur Average Marks is ");
                System.out.printf("%.2f",res);
                System.out.println();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("You didnt Enter Any of Our Exams");
        }

    }

    public void examflow(Student student) {
            Scanner input = new Scanner(System.in);
            ResultSet result;
            int counter =0;
            int correctAnswer=0,wrongAnswer=0,answer;
            while (counter<5)
            {
                Random rand = new Random();
                int upperbound = getMaxId();
                int int_random = rand.nextInt(upperbound);
                if(searchId(int_random))
                {
                    counter++;
                }
                else {
                    continue;
                }
                try
                 {
                   statement =connect_database.createStatement();
                   String query="SELECT * FROM `questions` WHERE question_id="+ int_random +";";
                   result =statement.executeQuery(query);
                   result.next();
                   int db_answer= result.getInt(3);
                   System.out.println("question NO "+ int_random +": "+ result.getString(2));
                     do {
                         System.out.println("Enter The Answer (1 For True or 0 For False) :");
                         answer = input.nextInt();
                     }while (answer !=1 & answer!=0);
                     if(answer==db_answer)
                     {
                         correctAnswer++;
                     }
                     else
                     {
                         wrongAnswer++;
                     }


                 }
                catch (SQLException ex)
                 {
                     System.out.println(ex.getMessage());
                 }
            }
            try {
                 statement=connect_database.createStatement();
                 String query="INSERT INTO `examsmarks`(`student_id`, `exam mark`) VALUES ('"+ student.getId() +"','"+ (correctAnswer*20)+"')";
                 statement.execute(query);
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        System.out.println("Correct Answers is "+correctAnswer);
        System.out.println("Wrong Answers is "+wrongAnswer);
        System.out.println("Your Exam Mark is "+(correctAnswer*20));
    }

    private int getMaxId() {
        ResultSet result;
        int max_id=0;
        try{
            statement = connect_database.createStatement();
            result = statement.executeQuery("SELECT MAX(question_id) as max_items FROM questions;");
            result.next();
            max_id = result.getInt(1);
        }
        catch (SQLException exc)
        {
            System.out.println(exc.getMessage());
        }
        return max_id;
    }
}
