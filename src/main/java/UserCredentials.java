public class UserCredentials {
    public String name;
    public String email;
    public String password;

    public UserCredentials(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static UserCredentials from(User user) {
        return new UserCredentials(user.name, user.email, user.password);
    }
}
