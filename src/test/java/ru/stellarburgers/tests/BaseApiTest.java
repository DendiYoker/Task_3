package ru.stellarburgers.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import ru.stellarburgers.api.BurgersApiClient;
import ru.stellarburgers.dto.CreatedUserData;
import ru.stellarburgers.dto.GetDelUserResponse;
import ru.stellarburgers.dto.LoginUserData;
import ru.stellarburgers.dto.UserResponse;;

public class BaseApiTest {

    protected static BurgersApiClient burgersApiClient;

    @BeforeAll
    protected static void setUp() {
        burgersApiClient = new BurgersApiClient();
    }

    /**
     * Удаление пользователя по email и паролю.
     * Выполняет авторизацию, получает токен и удаляет пользователя.
     * Если пользователь не существует — ничего не делает.
     */
    @Step("Удалить пользователя по email и паролю (если существует")
    protected void deleteUserIfExists(LoginUserData loginUserData) {
        try {
            Response loginResponse = loginUser(loginUserData);
            UserResponse userResponse = loginResponse.as(UserResponse.class);

            if(userResponse.getAccessToken() != null){
                deleteUser(userResponse);
            } else {
                Allure.step(String.format("Пользователь не найден в системе, ответ на гет запрос: '%s'",
                        loginResponse.getBody().asString()));
            }

        } catch (Exception e) {
            Allure.step(String.format("Исключение при удалении пользователя '%s': %s", loginUserData.getEmail(), e.getMessage()));
        }

    }


    @Step("Авторизация пользователя в системе")
    protected Response loginUser(LoginUserData loginUserData) {
        return burgersApiClient.postRequestLoginUser(loginUserData);
    }

    @Step("Попытка удаления пользователя ")
    protected void deleteUser(UserResponse userResponse) {

        Response responseDeleteUser = burgersApiClient.deleteRequestDeletedUser(userResponse.getAccessToken());

        GetDelUserResponse delUserResponse = responseDeleteUser.as(GetDelUserResponse.class);

        if(Boolean.TRUE.equals(delUserResponse.getSuccess())){
            Allure.step(String.format("Пользователь удален: '%s'", delUserResponse.getMessage()));
        } else {
            Allure.step(String.format("Ошибка при удалении пользователя '%s'", delUserResponse.getMessage()));
        }

    }

    @Step("Создать пользователя через API")
    public void registerUserApi(CreatedUserData userRequest){
        Response response = burgersApiClient.postRequestRegisterUser(userRequest);
        response.then().assertThat().statusCode(200);
    }

}
