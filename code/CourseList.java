// class to collect and manage a list of courses

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseList implements Serializable {

  private ArrayList<Course> course_list = new ArrayList<>();
  private transient Scanner input = new Scanner(System.in);

  public CourseList() {}

  public CourseList(CourseList course_list) {
    this.course_list = course_list.get_course_list();
  }

  public ArrayList<Course> get_course_list() {
    return course_list;
  }

  // method to selection search through course database

  public Course search(int course_id_search, int course_section_search) {
    for (int i = 0; i < course_list.size(); i++) {
      if (
        course_list.get(i) != null &&
        (
          course_list.get(i).get_course_id() == course_id_search &&
          course_list.get(i).get_section() == course_section_search
        )
      ) {
        return course_list.get(i);
      }
    }

    return null;
  }

  // method to view details of a particular course

  public void view(int course_id_search, int course_section_search) {
    System.out.println(
      "\nCourse title: " +
      search(course_id_search, course_section_search).get_course_name()
    );
    System.out.println(
      "Instructor: " +
      search(course_id_search, course_section_search).get_instructor()
    );
    System.out.println(
      "Section: " +
      search(course_id_search, course_section_search).get_section()
    );
    System.out.println(
      "Location: " +
      search(course_id_search, course_section_search).get_location()
    );
    System.out.println(
      "Maximum # of students: " +
      search(course_id_search, course_section_search).get_max_registered()
    );
    System.out.println(
      "Registered # of students: " +
      search(course_id_search, course_section_search).get_registered()
    );
  }

  // method which views all courses, full or open

  public void view_all(boolean full) {
    for (int i = 0; i < course_list.size(); i++) {
      if (full) {
        if (
          course_list.get(i).get_registered() ==
          course_list.get(i).get_max_registered()
        ) {
          System.out.println(
            "\nCourse title: " + course_list.get(i).get_course_name()
          );
          System.out.println(
            "Course ID: " + course_list.get(i).get_course_id()
          );
          System.out.println(
            "Maximum registration: " + course_list.get(i).get_max_registered()
          );
          System.out.println(
            "Registered # of students: " + course_list.get(i).get_registered()
          );
          System.out.println("--------------------------------------------");
        }
      } else {
        System.out.println(
          "\nCourse title: " + course_list.get(i).get_course_name()
        );
        System.out.println("Course ID: " + course_list.get(i).get_course_id());
        System.out.println(
          "Maximum registration: " + course_list.get(i).get_max_registered()
        );
        System.out.println(
          "Registered # of students: " + course_list.get(i).get_registered()
        );
        System.out.println("--------------------------------------------");
      }
    }
  }

  // method to view the list of students registered to a course

  public void view_registry(int course_id_search, int course_section_search) {
    if (search(course_id_search, course_section_search) != null) {
      Course selected_course = search(course_id_search, course_section_search);

      System.out.println(
        "\nThe following are students registered to course '" +
        selected_course.get_course_name() +
        ":'\n"
      );

      for (int i = 0; i < selected_course.registered_students.size(); i++) {
        System.out.print(
          selected_course.registered_students.get(i).get_student_id() +
          " : " +
          selected_course.registered_students.get(i).get_first_name() +
          " " +
          selected_course.registered_students.get(i).get_last_name() +
          "\n"
        );
      }
    } else {
      System.out.println("Course not found.");
    }
  }

  // method to create a new course and add to database

  public void add_course() {
    System.out.println(
      "\nPlease provide the requested information to add a new course.\n"
    );
    System.out.print("Course title: ");
    String course_name = input.nextLine();
    System.out.print("Course instructor: ");
    String instructor = input.nextLine();
    System.out.print("Course ID (Integer Only): ");
    int course_id = Integer.parseInt(input.next());
    input.nextLine();
    for (int i = 0; i < course_list.size(); i++) {
      while (course_list.get(i).get_course_id() == course_id) {
        System.out.print("ID already taken. Enter a new ID #: ");
        course_id = Integer.parseInt(input.next());
        input.nextLine();
      }
    }
    System.out.print("Course section: ");
    int section = Integer.parseInt(input.next());
    input.nextLine();
    System.out.print("Course location: ");
    String location = input.nextLine();
    System.out.print("Course registration limit: ");
    int max_registered = Integer.parseInt(input.next());
    input.nextLine();

    Course new_course = new Course(
      course_name,
      instructor,
      course_id,
      section,
      location,
      max_registered
    );

    course_list.add(new_course);
  }

  // method to remove a course from database

  public void delete(int course_id_search, int course_section_search) {
    if (search(course_id_search, course_section_search) != null) {
      System.out.println(
        "Course '" +
        search(course_id_search, course_section_search).get_course_name() +
        "' has been removed."
      );
      course_list.remove(search(course_id_search, course_section_search));
    } else {
      System.out.println("Course not found.");
    }
  }

  // method to edit a course previously-existing within database

  public void edit(int course_id_search, int course_section_search) {
    if (search(course_id_search, course_section_search) != null) {
      System.out.println(
        "Give updated information for course '" +
        search(course_id_search, course_section_search).get_course_name() +
        ".' Enter to skip a field.\n"
      );
      System.out.print("Course title: ");
      String course_name = input.nextLine();
      System.out.print("Course instructor: ");
      String instructor = input.nextLine();
      System.out.print("Course section: ");
      String str_section = input.nextLine();
      int section = 0;
      System.out.print("Course location: ");
      String location = input.nextLine();
      System.out.print("Course registration limit: ");
      String str_max_registered = input.nextLine();
      int max_registered = 0;

      if (course_name.equals("")) {
        course_name =
          search(course_id_search, course_section_search).get_course_name();
      }

      if (instructor.equals("")) {
        instructor =
          search(course_id_search, course_section_search).get_instructor();
      }

      if (str_section.equals("")) {
        section = search(course_id_search, course_section_search).get_section();
      } else {
        section = Integer.parseInt(str_section);
      }

      if (location.equals("")) {
        location =
          search(course_id_search, course_section_search).get_location();
      }

      if (str_max_registered.equals("")) {
        max_registered =
          search(course_id_search, course_section_search).get_max_registered();
      } else {
        max_registered = Integer.parseInt(str_section);
      }

      Course updated_course = new Course(
        course_name,
        instructor,
        course_id_search,
        section,
        location,
        max_registered
      );
      course_list.set(
        course_list.indexOf(search(course_id_search, course_section_search)),
        updated_course
      );
    } else {
      System.out.println("Course not found.");
    }
  }

  // method to add a new student to a particular course

  public synchronized void register(
    int course_id_search,
    int course_section_search,
    Student student
  ) {
    if (search(course_id_search, course_section_search) != null) {
      if (
        search(course_id_search, course_section_search).get_max_registered() >
        search(course_id_search, course_section_search).get_registered()
      ) {
        if (
          search(course_id_search, course_section_search).search(student) == -1
        ) {
          search(course_id_search, course_section_search).add_student(student);
          student.add_course(search(course_id_search, course_section_search));
          System.out.println("Registration successful.");
        } else {
          System.out.println("Student already registered.");
        }
      } else {
        System.out.println("Cannot register. Course is full.");
      }
    } else {
      System.out.println("Course does not exist.");
    }
  }

  // method to remove a student from a particular course

  public void withdraw(
    int course_id_search,
    int course_section_search,
    Student student
  ) {
    if (search(course_id_search, course_section_search) != null) {
      int student_index = search(course_id_search, course_section_search)
        .search(student);

      if (student_index != 1) {
        search(course_id_search, course_section_search).remove_student(student);
        student.remove_course(search(course_id_search, course_section_search));
      } else {
        System.out.println("Student not registered.");
      }
    } else {
      System.out.println("Course does not exist.");
    }
  }
}
