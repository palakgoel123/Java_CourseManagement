import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Application extends Thread implements Serializable {

  private static Admin admin = new Admin();
  private static CourseList course_list = new CourseList();
  private static StudentList student_list = new StudentList();
  private static CourseList course_list_saved = null;
  private static StudentList student_list_saved = null;
  private static Scanner input = new Scanner(System.in);
  private static int selection;
  private static final String secret_phrase = "done";
  int course_id_search001;
  int course_section_search001;
  Student student001;

  public void run() {
    course_list.register(
      course_id_search001,
      course_section_search001,
      student001
    );
  }

  public static void main(String[] args) {
    // deserialize objects if already serialized

    deserialize();

    // begin menu sequence

    login();

    // serialize objects at program end

    serialize();
  }

  // method to handle user log-in

  public static void login() {
    boolean approved = false;

    while (true) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.print(
        "-----------------------------------------------------------------------------------------"
      );
      System.out.println(
        "\nWelcome to the IIIT Lucknow's Course Management System. Please login.\nType \"done\" to exit."
      );
      System.out.println(
        "-----------------------------------------------------------------------------------------"
      );
      System.out.print("Username: ");
      String username_input = input.next();

      if (username_input.equals(secret_phrase)) {
        break;
      }

      System.out.print("Password: ");
      String password_input = input.next();

      if (password_input.equals(secret_phrase)) {
        break;
      }

      if (
        username_input.equals(admin.get_username()) &&
        password_input.equals(admin.get_password())
      ) {
        approved = true;
        admin_menu();
      } else {
        for (int i = 0; i < student_list.get_student_list().size(); i++) {
          if (
            username_input.equals(
              student_list.get_student_list().get(i).get_username()
            ) &&
            password_input.equals(
              student_list.get_student_list().get(i).get_password()
            )
          ) {
            approved = true;
            student_menu(student_list.get_student_list().get(i));
          }
        }
      }

      if (!approved) {
        System.out.println("\nAccount not found.");
      }

      approved = false;
    }
  }

  // method for the admin menu which grants access to all other methods

  public static void admin_menu() {
    selection = 0;

    while (selection != 3) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.print(
        "------------------------------------------------------------------------------"
      );
      System.out.println(
        "\nWelcome, Admin. Please select a menu:\n\n1. Course Management\n2. Info Portal\n3. Exit"
      );
      System.out.println(
        "------------------------------------------------------------------------------"
      );
      System.out.print("Enter: ");
      selection = Integer.parseInt(input.next());
      input.nextLine();

      while (selection != 1 && selection != 2 && selection != 3) {
        System.out.print("Invalid. Enter: ");
        selection = Integer.parseInt(input.next());
        input.nextLine();
      }

      if (selection == 1) {
        while (selection != 6) {
          System.out.println(
            "--------------------------COURSE MANAGEMENT----------------------------------"
          );
          System.out.println(
            "\n1. Create a new course\n2. Delete a course\n3. Edit a course\n4. Display info about a course\n5. Register a student\n6. Exit\n"
          );
          System.out.print("Enter: ");
          selection = Integer.parseInt(input.next());
          input.nextLine();

          while (selection < 1 || selection > 6) {
            System.out.print("Invalid. Enter: ");
            selection = Integer.parseInt(input.next());
            input.nextLine();
          }

          if (selection == 1) {
            course_list.add_course();
          }

          if (selection == 2) {
            System.out.print("\nEnter the ID of the course to remove: ");
            int course_id_search = Integer.parseInt(input.next());
            input.nextLine();
            System.out.print("Enter the section # of the course: ");
            int course_section_search = Integer.parseInt(input.next());
            input.nextLine();

            course_list.delete(course_id_search, course_section_search);
          }

          if (selection == 3) {
            System.out.print("\nEnter the ID of the course to edit: ");
            int course_id_search = Integer.parseInt(input.next());
            input.nextLine();
            System.out.print("Enter the section # of the course: ");
            int course_section_search = Integer.parseInt(input.next());
            input.nextLine();

            course_list.edit(course_id_search, course_section_search);
          }

          if (selection == 4) {
            System.out.print("\nEnter the ID of the course to view: ");
            int course_id_search = Integer.parseInt(input.next());
            input.nextLine();
            System.out.print("Enter the section # of the course: ");
            int course_section_search = Integer.parseInt(input.next());
            input.nextLine();

            course_list.view(course_id_search, course_section_search);
          }

          if (selection == 5) {
            student_list.add_student();
          }
        }
      }

      if (selection == 2) {
        while (selection != 6) {
          System.out.println(
            "--------------------------INFO PORTAL----------------------------------"
          );
          System.out.println(
            "\n1. View all courses\n2. View all full courses\n3. View course registry\n4. View student registration\n5. View all students registered\n6. Exit\n"
          );
          System.out.print("Enter: ");
          selection = Integer.parseInt(input.next());
          input.nextLine();

          while (selection < 1 || selection > 6) {
            System.out.print("Invalid. Enter: ");
            selection = Integer.parseInt(input.next());
            input.nextLine();
          }

          if (selection == 1) {
            course_list.view_all(false);
          }

          if (selection == 2) {
            course_list.view_all(true);
          }

          if (selection == 3) {
            System.out.print("\nEnter the ID of the course to view registry: ");
            int course_id_search = Integer.parseInt(input.next());
            input.nextLine();
            System.out.print("Enter the section # of the course: ");
            int course_section_search = Integer.parseInt(input.next());
            input.nextLine();

            course_list.view_registry(course_id_search, course_section_search);
          }

          if (selection == 4) {
            System.out.print(
              "\nEnter the ID of the student to view their registration: "
            );
            int student_id_search = Integer.parseInt(input.next());
            input.nextLine();

            student_list.view_registration(student_id_search);
          }
          if (selection == 5) {
            System.out.println("Names of Registered Student are as follows : ");
            for (int i = 0; i < student_list.get_student_list().size(); i++) {
              System.out.println(
                student_list.get_student_list().get(i).get_student_id() + 
                " : " +
                student_list.get_student_list().get(i).get_first_name() +
                " " +
                student_list.get_student_list().get(i).get_last_name()
              );
            }
          }
        }
      }
    }
  }

  // method for the student menu which allows access to select methods

  public static void student_menu(Student student) {
    selection = 0;
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.print(
      "---------------------------------------------------------------------------------"
    );
    System.out.println(
      "\nWelcome, " +
      student.get_first_name() +
      " " +
      student.get_last_name() +
      " student of IIITL."
    );
    System.out.println(
      "---------------------------------------------------------------------------------"
    );
    while (selection != 6) {
      System.out.println(
        "\n1. View all courses\n2. View all full courses\n3. Register to a course\n4. Withdraw from a course\n5. View your registration\n6. Exit\n"
      );
      System.out.print("Enter: ");
      selection = Integer.parseInt(input.next());
      input.nextLine();

      while (selection < 1 || selection > 6) {
        System.out.print("Invalid. Enter: ");
        selection = Integer.parseInt(input.next());
        input.nextLine();
      }

      if (selection == 1) {
        course_list.view_all(false);
      }

      if (selection == 2) {
        course_list.view_all(true);
      }

      if (selection == 3) {
        System.out.print(
          "\nEnter the ID of the course to which you would like to register: "
        );
        int course_id_search = Integer.parseInt(input.next());
        input.nextLine();
        System.out.print("Enter the section # of the course: ");
        int course_section_search = Integer.parseInt(input.next());
        input.nextLine();

        // course_list.register(course_id_search, course_section_search, student);
        Application register = new Application();
        register.course_id_search001 = course_id_search;
        register.course_section_search001 = course_section_search;
        register.student001 = student;
        register.start();
      }

      if (selection == 4) {
        System.out.print(
          "\nEnter the ID of the course to which you would like to withdraw: "
        );
        int course_id_search = Integer.parseInt(input.next());
        input.nextLine();
        System.out.print("Enter the section # of the course: ");
        int course_section_search = Integer.parseInt(input.next());
        input.nextLine();

        course_list.withdraw(course_id_search, course_section_search, student);
      }

      if (selection == 5) {
        student_list.view_registration(student.get_student_id());
      }
    }
  }

  // method to save data to a file

  public static void serialize() {
    try {
      // file writer

      FileOutputStream course_list_fos = new FileOutputStream("CourseList.ser");
      FileOutputStream student_list_fos = new FileOutputStream(
        "StudentList.ser"
      );

      // object writer

      ObjectOutputStream course_list_oos = new ObjectOutputStream(
        course_list_fos
      );
      ObjectOutputStream student_list_oos = new ObjectOutputStream(
        student_list_fos
      );

      // write objects to stream

      course_list_oos.writeObject(course_list);
      student_list_oos.writeObject(student_list);

      // close streams

      course_list_fos.close();
      student_list_fos.close();
      course_list_oos.close();
      student_list_oos.close();

      System.out.println("\nData saved.\n");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  // method to read data from a file

  public static void deserialize() {
    if (
      new File("CourseList.ser").exists() &&
      new File("StudentList.ser").exists()
    ) {
      try {
        // file reader

        FileInputStream course_list_fis = new FileInputStream("CourseList.ser");
        FileInputStream student_list_fis = new FileInputStream(
          "StudentList.ser"
        );

        // object reader

        ObjectInputStream course_list_ois = new ObjectInputStream(
          course_list_fis
        );
        ObjectInputStream student_list_ois = new ObjectInputStream(
          student_list_fis
        );

        // read object into empty variable, explicitly cast

        course_list_saved = (CourseList) course_list_ois.readObject();
        student_list_saved = (StudentList) student_list_ois.readObject();

        // replace lists with saved data

        course_list = new CourseList(course_list_saved);
        student_list = new StudentList(student_list_saved);

        // close readers

        course_list_ois.close();
        student_list_ois.close();
        course_list_fis.close();
        student_list_fis.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      }
    } else {
      System.out.println("File not found");
    }
  }
}
