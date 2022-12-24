import java.util.Scanner;

public class Login {
    public Student login()
    {
        Student student;
        Scanner input = new Scanner(System.in);
        String email,password;
        do {
            System.out.println("Enter Your email :");
            email = input.next();
            System.out.println("Enter Your password :");
            password = input.next();
            student =DatabaseManagement.DB.checkExistingUser(email,password);
            if(student == null)
            {
                System.out.println("This Email doesnt exists");
                continue;
            }
            else
            {
                System.out.println("Welcom "+student.getName()+" to our Site");
                break;

            }
        } while(true);
        return  student;
    }
    }

