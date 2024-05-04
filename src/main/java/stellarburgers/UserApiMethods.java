package stellarburgers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiMethods extends TestDataAndConstants {

    @Step("Создание пользователя с заданными параметрами")
    public Response create(User user) {
        return given()
                .baseUri(URL_HOME_PAGE)
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register");
    }

    @Step("Получение токена для удаления юзера")
    public Response login(UserLoginDetails userLoginDetails) {
        return given()
                .baseUri(URL_HOME_PAGE)
                .header("Content-type", "application/json")
                .body(userLoginDetails)
                .post("/api/auth/login");
    }

    @Step("Удаление пользователя по токену ")
    public Response delete(String accessToken) {
        return given()
                .baseUri(URL_HOME_PAGE)
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }

}
