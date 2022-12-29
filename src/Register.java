import java.util.Scanner;

public class Register {


    public Student register()
    {
        Scanner input = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        boolean b;
        String name,email,password;
        int clas;
        do {
            System.out.println("Enter Your Name :");
            name = in.nextLine();
            System.out.println("Enter Your class :");
            clas =input.nextInt();
            System.out.println("Enter Your email :");
            email = input.next();
            System.out.println("Enter Your password :");
            password = input.next();
            b = DatabaseManagement.DB.checkNewUser(email);
        } while(!b);
         return DatabaseManagement.DB.addStudent(name,clas,email,password);
    }
}