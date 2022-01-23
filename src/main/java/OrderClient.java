import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {
    private static final String ORDER_PATH = "api/orders";
    private static final String INGRIDIENTS_PATH = "api/ingredients";

    @Step
    public IngridientsJson getDataIngridients() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(INGRIDIENTS_PATH)
                .body().as(IngridientsJson.class);
    }

    @Step
    public ValidatableResponse create(IngridientsDataJson ingridients, String bearerToken) {
        return given()
                .headers("Authorization", bearerToken)
                .spec(getBaseSpec())
                .body(ingridients)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step
    public ValidatableResponse create(IngridientsDataJson ingridients) {
        return given()
                .spec(getBaseSpec())
                .body(ingridients)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step
    public ValidatableResponse get() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }

    @Step
    public ValidatableResponse get(String bearerToken) {
        return given()
                .headers("Authorization", bearerToken)
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}