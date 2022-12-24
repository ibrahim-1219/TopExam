
import java.util.Scanner;

public class Main {


    public static void main(String[] args)
    {
        Student student;
        Scanner input = new Scanner(System.in);
        do {

            System.out.println("Select 1 to Register :\nSelect 2 to Login :");
            String op = input.next();
            if (op.equals("1")) {
                Register register = new Register();
                student=register.register();
                break;
            }
            if (op.equals("2")) {
                Login log = new Login();
                student=log.login();
                break;
            }
           } while (true);
        if(student.isIs_admin())
        {
            AdminFlow adm_flow = new AdminFlow();
        }
        else
        {
            StudentFlow std_flow =new StudentFlow();
        }















        //System.out.println(student.getName());
        //System.out.println(student.getEmail());
        //System.out.println(student.getPassword());
        //System.out.println(student.getClas());
        //System.out.println(student.isIs_admin());
        /*DatabaseConnection c = new DatabaseConnection();
        try{
            Connection conn =c.connect(); ;
            Statement s = conn.createStatement();
            String query="SELECT * FROM questions;";
            s.execute(query);
            System.out.println("excuted");


        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }*/
    }
}