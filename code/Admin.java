// an admin has an immutable login

public class Admin extends User implements AInterface {

  public Admin() {
    this.username = "Admin";
    this.password = "Admin001";
  }
}
