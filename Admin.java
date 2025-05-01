
public class Admin extends User {

  private String AdminPassword;

  public Admin(String userId, String name, String email, String phone, String address, String AdminPassword) {
    super(userId, name, email, phone, address);
    this.AdminPassword = AdminPassword;
  }

  public String getAdminPassword() {
    return AdminPassword;
  }

  public void setAdminPassword(String adminPassword) {
    this.AdminPassword = adminPassword;
  }

  public String toString() {
    return super.toString() + "AdminPassword: " + AdminPassword + "\n";

  }

}
