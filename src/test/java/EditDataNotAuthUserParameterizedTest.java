import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EditDataNotAuthUserParameterizedTest {
    private final UserDataJson body;
    private UserClient userClient;

    public EditDataNotAuthUserParameterizedTest(UserDataJson body) {
        this.body = body;
    }

    @Parameterized.Parameters
    public static Object[][] getUserInfo() {
        return new Object[][]{
                {new UserDataJson(RandomStringUtils.randomAlphabetic(10), null, null)},
                {new UserDataJson(null, (RandomStringUtils.randomAlphabetic(10) + "@mailinator.com").toLowerCase(), null)},
                {new UserDataJson(null, null, RandomStringUtils.randomAlphabetic(10))}
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @Test
    @Story("Редактирование данных у неавторизованного пользака")
    @DisplayName("Негативный сценарий")
    @Description("Нужно авторизоваться для смены данных")
    public void editNameAndEmailAndPasswordNotAuthUserParameterizedTest() {
        ValidatableResponse response = userClient.editData(body);
        assertEquals("You should be authorised", 401,response.extract().statusCode());
    }
}
