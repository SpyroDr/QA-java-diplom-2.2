import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateUserParameterizedTest {
    private User user;
    private final int statusCode;
    private final String message;
    private UserClient userClient;


    public CreateUserParameterizedTest(User user, int statusCode, String message) {
        this.user = user;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Parameterized.Parameters
    public static Object[][] getUserInfo() {
        return new Object[][]{
                {User.getRandom(), 200, null},
                {User.getWithEmailAndName(), 403, "Email, password and name are required fields"},
                {User.getWithEmailAndPassword(), 403, "Email, password and name are required fields"},
                {User.getWithNameAndPassword(), 403, "Email, password and name are required fields"}
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @After
    public void tearDown() {
        userClient.delete();
    }

    @Test
    @Story("Создание юзера: параметризованный тест")
    @DisplayName("Положительный и негативные сценарии")
    @Description("Со всеми полями и без одного из полей")
    public void userCreatedAllTest() {
        ValidatableResponse response = userClient.create(user);
        Assert.assertTrue(statusCode == response.extract().statusCode());
        Assert.assertEquals(message, response.extract().path("message"));
    }
}
