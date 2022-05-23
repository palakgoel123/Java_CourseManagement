// class which represents a single course

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

  private String course_name;
  private String instructor;
  private int course_id;
  private int section;
  private String location;
  private int max_registered;
  protected ArrayList<Student> registered_students = new ArrayList<>();
  private int registered = 0;

  public Course() {}

  public Course(
    String course_name,
    String instructor,
    int course_id,
    int section,
    String location,
    int max_registered
  ) {
    this.course_name = course_name;
    this.instructor = instructor;
    this.course_id = course_id;
    this.section = section;
    this.location = location;
    this.max_registered = max_registered;
  }

  public String get_course_name() {
    return course_name;
  }

  public void set_course_name(String course_name) {
    this.course_name = course_name;
  }

  public String get_instructor() {
    return instructor;
  }

  public void set_instructor(String instructor) {
    this.instructor = instructor;
  }

  public int get_course_id() {
    return course_id;
  }

  public void set_course_id(int course_id) {
    this.course_id = course_id;
  }

  public int get_section() {
    return section;
  }

  public void set_section(int section) {
    this.section = section;
  }

  public int get_registered() {
    return registered;
  }

  public void set_registered(int registered) {
    this.registered = registered;
  }

  public int get_max_registered() {
    return max_registered;
  }

  public void set_max_registered(int max_registered) {
    this.max_registered = max_registered;
  }

  public ArrayList<Student> get_registered_students() {
    return registered_students;
  }

  public String get_location() {
    return location;
  }

  public void set_location(String location) {
    this.location = location;
  }

  // method to selection search through array-list of students

  public int search(Student student) {
    for (int i = 0; i < registered_students.size(); i++) {
      if (
        student.get_student_id() == registered_students.get(i).get_student_id()
      ) {
        return i;
      }
    }

    return -1;
  }

  // method used by register method in CourseList to add a student to the array-list

  public void add_student(Student student) {
    boolean exists = false;

    for (int i = 0; i < registered_students.size(); i++) {
      if (registered_students.get(i).equals(student)) {
        exists = true;
      }
    }

    if (!exists) {
      registered_students.add(student);
      registered += 1;
    }
  }

  // method used by withdraw method in CourseList to remove a student from the array-list

  public void remove_student(Student student) {
    for (int i = 0; i < registered_students.size(); i++) {
      if (registered_students.get(i).equals(student)) {
        registered_students.remove(search(student));
        registered -= 1;
      }
    }
  }
}
