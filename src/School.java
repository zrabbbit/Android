import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student
{
    private int id;
    private String name;
    private String college;
    Student(){}
    Student(int id,String name,String college)
    {
        this.id=id;
        this.name=name;
        this.college=college;
    }
    public String toString()
    {
        return ("学生自我介绍：\n"+"姓名："+name+"\n学号："+id+"\n学院："+college);
    }
}
abstract class Course
{
    protected String name;
    protected String teacher;
    protected String site;
    protected List<Student> students;
    Course(){}
    Course(String name,String teacher,String site,List<Student> students)
    {
        this.name=name;
        this.teacher=teacher;
        this.site=site;
        this.students=students;
    }
    public abstract String toString();
    public String getName()
    {
        return name;
    }
}
class java_class extends Course
{
    java_class(){}
    java_class(String name,String teacher,String site,List<Student> students)
    {
        super(name,teacher,site,students);
    }
    public String toString()
    {
        System.out.println("课程名称："+name);
        System.out.println("任课老师："+teacher);
        System.out.println("上课地点："+site);
        System.out.println("参与课程学生：");
        for (Student i:students)
        {
            System.out.println(i.toString());
        }
        return null;
    }
}
class data_class extends Course
{
    data_class(){}
    data_class(String name,String teacher,String site,List<Student> students)
    {
        super(name,teacher,site,students);
    }
    public String toString()
    {
        System.out.println("课程名称："+name);
        System.out.println("任课老师："+teacher);
        System.out.println("上课地点："+site);
        System.out.println("参与课程学生：");
        for (Student i:students)
        {
            System.out.println(i.toString());
        }
        return null;
    }
}
class Teacher
{
    private int id;
    private String name;
    private List<Course> courses;
    Teacher(){}
    Teacher(int id,String name,List<Course>courses)
    {
        this.id=id;
        this.name=name;
        this.courses=courses;
    }
    public String toString()
    {
        System.out.println("教师介绍：");
        System.out.println("姓名："+name+"\n工号："+id);
        System.out.println("教授课程：");
        for (Course i:courses)
        {
            i.toString();
        }
        return null;
    }
    public void teaching()
    {
        System.out.println(name+"老师正在上课");
    }
    public String getName()
    {
        return name;
    }

}
interface Manage
{
    void managing();
}
class Counselor extends Teacher implements Manage
{
    private List<Student> s;
    Counselor(){}
    Counselor(int id,String name,List<Course>courses)
    {
        super(id,name,courses);
        s = new ArrayList<>();
    }
    public void managing()
    {
        Scanner scanner = new Scanner(System.in);
        int t=s.size();
        while(scanner.hasNextInt())
        {
            int s_id = scanner.nextInt();
            String s_name = scanner.next();
            String s_college = scanner.next();
            Student student = new Student(s_id,s_name,s_college);
            s.add(student);
        }
        System.out.println("辅导员："+this.getName()+"所管理的学生个数为："+s.size()+"人"+"\n新增学生个数："+(s.size()-t));
    }
}
class Absence extends Exception
{
    private int sum;
    Absence(int sum)
    {
        this.sum=sum;
    }
    public int getSum()
    {
        return sum;
    }
}
class Learning
{
    private Course course;
    private List<Student> students;
    Learning(Course course,List<Student> students)
    {
        this.course = course;
        this.students = students;
    }
    public void Roll_call() throws Absence
    {
        if(course.students.size()>students.size())
        {
            int shorting = course.students.size()-students.size();
            throw new Absence(shorting);
        }
    }
}
public class School {
    public static void main(String[] args)
    {
        Student student_1 = new Student(1,"A","Math");
        System.out.println(student_1.toString());
        List<Student> students = new ArrayList<>();
        List<Student> students1 = new ArrayList<>();
        students.add(student_1);
        Scanner scanner1 = new Scanner(System.in);
        while(scanner1.hasNextInt()) //学生列表 end退出
        {
            int id = scanner1.nextInt();
            String name = scanner1.next();
            String college = scanner1.next();
            Student ss = new Student(id,name,college);
            students.add(ss);
            students1.add(ss);
        }
        for (Student i:students)
        {
            System.out.println(i.toString());
        }
        Scanner scanner2 = new Scanner(System.in);
        String c_name = scanner2.next();
        String t_name = scanner2.next();
        String site = scanner2.next();
        java_class course1 = new java_class(c_name,t_name,site,students);
        course1.toString();
        data_class course2 = new data_class("data",t_name,site,students);
        course2.toString();
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        /*int id = scanner2.nextInt();
        Teacher teacher = new Teacher(id,t_name,courses);
        teacher.toString();
        teacher.teaching();
        Counselor counselor = new Counselor(id,t_name,courses);
        counselor.managing();*/
        Learning class1 = new Learning(course1,students1);
        try
        {
            class1.Roll_call();
        }catch (Absence e)
        {
            System.out.println("该课程旷课人数为："+e.getSum());
        }

    }
}
