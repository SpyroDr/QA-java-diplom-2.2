import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EditDataAuthUserParameterizedTest {
    private final UserDataJson body;
    private final String userDataParam;
    private User user;
    private UserClient userClient;

    public EditDataAuthUserParameterizedTest(UserDataJson body, String userDataParam) {
        this.body = body;
        this.userDataParam = userDataParam;
    }

    @Parameterized.Parameters
    public static Object[][] getUserInfo() {
        return new Object[][]{
                {new UserDataJson(RandomStringUtils.randomAlphabetic(10), null, null), "name"},
                {new UserDataJson(null, (RandomStringUtils.randomAlphabetic(10) + "@mailinator.com").toLowerCase(), null), "email"},
        };
    }

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
    @Description("Смена пароля")
    public void editNameAndEmailAuthUserParameterizedTest() {
        String token = userClient.create(user).extract().path("accessToken");
        ValidatableResponse response = userClient.editData(body, token);
        HashMap<String, String> userInfo = response.extract().path("user");
        Assert.assertEquals(body.getKey(userDataParam), userInfo.get(userDataParam));
        assertEquals("Test is fail", 200,response.extract().statusCode());
    }
}
