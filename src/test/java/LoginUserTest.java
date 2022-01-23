import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginUserTest {
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
    }

    @After
    public void tearDown() {
        userClient.delete();
    }

    @Test
    @Story("Авторизация")
    @DisplayName("Позитивный сценарий")
    @Description("Существующий пользак")
    public void loginExistUserTest() {
        userClient.create(user);
        ValidatableResponse response = userClient.login(UserCredentials.from(user));
        assertEquals(null, 200,response.extract().statusCode());
    }

    @Test
    @Story("Авторизация")
    @DisplayName("Негативный сценарий")
    @Description("Несуществующий пользак")
    public void loginNotExistUserTest() {
        ValidatableResponse response = userClient.login(UserCredentials.from(user));
        assertEquals("email or password are incorrect", 401,response.extract().statusCode());
    }
}
