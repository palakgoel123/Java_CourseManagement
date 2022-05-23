// class to represent a single student

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable, SInterface {

  private int student_id;
  private String first_name;
  private String last_name;
  protected ArrayList<Course> registered_courses = new ArrayList<>();

  public Student() {}

  public Student(
    String username,
    String password,
    String first_name,
    String last_name,
    int student_id
  ) {
    this.username = username;
    this.password = password;
    this.first_name = first_name;
    this.last_name = last_name;
    this.student_id = student_id;
  }

  public int get_student_id() {
    return student_id;
  }

  public String get_first_name() {
    return first_name;
  }

  public String get_last_name() {
    return last_name;
  }

  public void set_student_id(int student_id) {
    this.student_id = student_id;
  }

  public ArrayList<Course> get_registered_courses() {
    return registered_courses;
  }

  // method to selection search through array-list of courses

  public int search(Course course) {
    for (int i = 0; i < registered_courses.size(); i++) {
      if (
        (course.get_course_id() == registered_courses.get(i).get_course_id()) &&
        (course.get_section() == registered_courses.get(i).get_section())
      ) {
        return i;
      }
    }

    return -1;
  }

  // method used by register method in CourseList to add a course to the array-list

  public void add_course(Course course) {
    boolean exists = false;

    for (int i = 0; i < registered_courses.size(); i++) {
      if (registered_courses.get(i).equals(course)) {
        exists = true;
      }
    }

    if (!exists) {
      registered_courses.add(course);
      
    }
  }

  // method used by withdraw method in CourseList to remove a course from the array-list

  public void remove_course(Course course) {
    for (int i = 0; i < registered_courses.size(); i++) {
      if (registered_courses.get(i).equals(course)) {
        registered_courses.remove(search(course));
      }
    }
  }
}
