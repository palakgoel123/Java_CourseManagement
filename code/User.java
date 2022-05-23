// a basic user

import java.io.Serializable;

public class User implements Serializable {

  protected String username;
  protected String password;
  protected String first_name;
  protected String last_name;

  public User() {}

  public User(
    String username,
    String password,
    String first_name,
    String last_name
  ) {
    this.username = username;
    this.password = password;
    this.first_name = first_name;
    this.last_name = last_name;
  }

  public String get_username() {
    return username;
  }

  public void set_username(String username) {
    this.username = username;
  }

  public String get_password() {
    return password;
  }

  public void set_password(String password) {
    this.password = password;
  }

  public String get_first_name() {
    return first_name;
  }

  public void set_first_name(String first_name) {
    this.first_name = first_name;
  }

  public String get_last_name() {
    return last_name;
  }

  public void set_last_name(String last_name) {
    this.last_name = last_name;
  }
}
