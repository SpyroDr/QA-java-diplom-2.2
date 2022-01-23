import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CreateUserTest {

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
    @Story("Создание юзера")
    @DisplayName("Создание юзера с одинаковыми данными")
    @Description("Нельзя создать юзера с однинаковыми данными")
    public void userCanNotBeEqual() {

        // Act
        userClient.create(user);
        ValidatableResponse statusCodeNegativeResponse = userClient.create(user);

        // Assert
        assertEquals("User already exists", 403, statusCodeNegativeResponse.extract().statusCode());

    }

}
