import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetOrderTest {
    private OrderClient orderClient;
    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
        orderClient = new OrderClient();
    }

    @Test
    @Story("Получение заказа")
    @DisplayName("Пзитивный сценарий")
    @Description("Авторизованный пользак")
    public void getOrderAuthUserTest() {
        String token = userClient.create(user).extract().path("accessToken");
        ValidatableResponse response = orderClient.get(token);
        assertEquals("Test is fail", 200,response.extract().statusCode());
    }

    @Test
    @Story("Получение заказа")
    @DisplayName("Негативный сценарий")
    @Description("Неавторизованный пользак")
    public void getOrderNotAuthUserTest() {
        ValidatableResponse response = orderClient.get();
        Assert.assertTrue(response.extract().statusCode() == 401);
        Assert.assertEquals("You should be authorised", response.extract().path("message"));
    }
}
