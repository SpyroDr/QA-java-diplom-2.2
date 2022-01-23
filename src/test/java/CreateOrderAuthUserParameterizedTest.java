import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderAuthUserParameterizedTest {
    private final IngridientsDataJson body;
    private final int code;
    private final String message;
    private Boolean success;
    private OrderClient orderClient;
    private UserClient userClient;
    private User user;

    public CreateOrderAuthUserParameterizedTest(IngridientsDataJson body, int code, String message, Boolean success) {
        this.body = body;
        this.code = code;
        this.message = message;
        this.success = success;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderInfo() {
        return new Object[][]{
                {new IngridientsDataJson("61c0c5a71d1f82001bdaaa6f"), 200, null, true},
                {new IngridientsDataJson(null), 400, "Ingredient ids must be provided", false},
        };
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
        orderClient = new OrderClient();
    }

    @Test
    @Story("Создание заказа у авторизованного пользака")
    @DisplayName("Негативный сценарий")
    @Description("Неверный ингредиент")
    public void createOrderAuthUserWithAndWithoutIngridientsTest() {
        String token = userClient.create(user).extract().path("accessToken");
        ValidatableResponse response = orderClient.create(body, token);
        Assert.assertTrue(response.extract().statusCode() == code);
        Assert.assertTrue(response.extract().path("success") == success);
        Assert.assertEquals(message, response.extract().path("message"));
    }
}