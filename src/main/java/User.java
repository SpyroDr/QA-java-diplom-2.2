
import org.apache.commons.lang3.RandomStringUtils;

public class User {

    public String email;
    public String password;
    public String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public static User getRandom() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@mailinator.com";
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String name = RandomStringUtils.randomAlphabetic(10);
        return new User(email, password, name);
    }



    public User setEmailAndName(String email, String name) {
        this.email = email;
        this.name = name;
        return this;
    }

    public User setEmailAndPassword(String email, String password) {
        this.email = email;
        this.password = password;
        return this;
    }

    public User setWithNameAndPassword(String name, String password) {
        this.name = name;
        this.password = password;
        return this;
    }

    public static User getWithEmailAndName() {
        return new User().setEmailAndName(RandomStringUtils.randomAlphabetic(10) + "@mailinator.com", RandomStringUtils.randomAlphabetic(10));
    }

    public static User getWithEmailAndPassword() {
        return new User().setEmailAndPassword(RandomStringUtils.randomAlphabetic(10) + "@mailinator.com", RandomStringUtils.randomAlphabetic(10));
    }

    public static User getWithNameAndPassword() {
        return new User().setWithNameAndPassword(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }
}
