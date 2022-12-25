import java.util.Scanner;

public class Register {


    public Student register()
    {
        Scanner input = new Scanner(System.in);
        boolean b =true;
        String name,clas,email,password;
        do {
            System.out.println("Enter Your Name :");
            name = input.next();
            System.out.println("Enter Your class :");
            clas =input.next();
            System.out.println("Enter Your email :");
            email = input.next();
            System.out.println("Enter Your password :");
            password = input.next();
            b = DatabaseManagement.DB.checkNewUser(email);
        } while(!b);
        Student student = DatabaseManagement.DB.addStudent(name,clas,email,password);
        return  student;
    }
}