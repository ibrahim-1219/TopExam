public class Student {
    private int id ;
    private String name;
    private String email;
    private String password;
    private String clas;
    private boolean is_admin;

    public Student(String name, String email, String password,String clas,boolean is_admin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.clas=clas;
    }
    public  Student()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }
}
