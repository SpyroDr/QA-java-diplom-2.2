import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditDataAuthUserNotParameterizedTest {
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
    @Story("Редактирование данных у авторизованного пользака")
    @DisplayName("Позитивный сценарий")
    @Description("Смена имени или имейла")
    public void editPasswordAuthUserTest() {
        String token = userClient.create(user).extract().path("accessToken");
        UserDataJson body = new UserDataJson(null, null, (RandomStringUtils.randomAlphabetic(10)));
        userClient.editData(body, token);
        ValidatableResponse response = userClient.login(new UserDataJson(null, user.email, body.getKey("password")));
        assertEquals("Test is fail", 200,response.extract().statusCode());
    }
}
