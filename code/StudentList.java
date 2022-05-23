// class to collect and manage a list of students

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentList implements Serializable {

  private ArrayList<Student> student_list = new ArrayList<>();
  private transient Scanner input = new Scanner(System.in);

  public StudentList() {}

  public StudentList(StudentList student_list) {
    this.student_list = student_list.get_student_list();
  }

  public ArrayList<Student> get_student_list() {
    return student_list;
  }

  // method to selection search through student pool

  public Student search(int student_id_search) {
    for (int i = 0; i < student_list.size(); i++) {
      if (student_list.get(i).get_student_id() == student_id_search) {
        return student_list.get(i);
      }
    }

    return null;
  }

  // method to view the courses to which a particular student within the pool is registered

  public void view_registration(int student_id_search) {
    if (search(student_id_search) != null) {
      Student selected_student = search(student_id_search);

      System.out.println(
        "\nThe following are courses to which " +
        selected_student.get_first_name() +
        " " +
        selected_student.get_last_name() +
        " is registered:\n"
      );

      for (int i = 0; i < selected_student.registered_courses.size(); i++) {
        System.out.println(
          "Course title: " +
          selected_student.registered_courses.get(i).get_course_name()
        );
        System.out.println(
          "Course ID: " +
          selected_student.registered_courses.get(i).get_course_id()
        );
        System.out.println(
          "Maximum registration: " +
          selected_student.registered_courses.get(i).get_max_registered()
        );
        System.out.println(
          "Registered # of students: " +
          selected_student.registered_courses.get(i).get_registered()
        );
        System.out.println("--------------------------------------------");
      }
    } else {
      System.out.println("Student not found.");
    }
  }

  // method to create a new student and add to pool

  public void add_student() {
    System.out.println("\nPlease provide the following information.\n");
    System.out.print("First name: ");
    String first_name = input.next();
    System.out.print("Last name: ");
    String last_name = input.next();
    System.out.print("ID #: ");
    int student_id = Integer.parseInt(input.next());
    input.nextLine();

    for (int i = 0; i < student_list.size(); i++) {
      while (student_list.get(i).get_student_id() == student_id) {
        System.out.print("ID already taken. Enter a new ID #: ");
        student_id = Integer.parseInt(input.next());
        input.nextLine();
      }
    }

    System.out.print("Username: ");
    String student_username = input.next();
    System.out.print("Password: ");
    String student_password = input.next();

    Student new_student = new Student(
      student_username,
      student_password,
      first_name,
      last_name,
      student_id
    );
    student_list.add(new_student);

    System.out.println(
      search(student_id).get_first_name() +
      " " +
      search(student_id).get_last_name() +
      " is registered."
    );
  }
}
