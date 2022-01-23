import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderTest {
    private OrderClient orderClient;
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
        orderClient = new OrderClient();
    }

    @After
    public void tearDown() {
        userClient.delete();
    }

    @Test
    @Story("Создание заказа у авторизованного пользака")
    @DisplayName("Негативный сценарий")
    @Description("Неверный ингредиент")
    public void createOrderAuthUserWithWrongHashIngridientTest() {
        String token = userClient.create(user).extract().path("accessToken");
        IngridientsDataJson body = new IngridientsDataJson("test");
        ValidatableResponse response = orderClient.create(body, token);
        Assert.assertTrue(response.extract().statusCode() == 500);
    }

    @Test
    @Story("Создание заказа у неавторизованного пользака")
    @DisplayName("Негативный сценарий")
    @Description("Неверный ингредиент")
    public void createOrderNotAuthUserWithWrongHashIngridientTest() {
        IngridientsDataJson body = new IngridientsDataJson("test");
        ValidatableResponse response = orderClient.create(body);
        Assert.assertTrue(response.extract().statusCode() == 500);
    }
}
