package ru.stellarburgers.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.stellarburgers.dto.LoginUserData;
import ru.stellarburgers.dto.CreatedUserData;

import static io.restassured.RestAssured.given;

public class BurgersApiClient {


    public BurgersApiClient() {
        RestAssured.baseURI = BurgersApiConfig.BASE_URL;
        RestAssured.filters(new AllureRestAssured()
                .setRequestTemplate("http-request.ftl")
                .setResponseTemplate("http-response.ftl"));
    }

    //создать юзвера
    public Response postRequestRegisterUser(CreatedUserData userRequest){

        String endpoint = BurgersApiConfig.USER_REGISTRATION;

        return given()
                .header("Content-Type", "application/json")
                .body(userRequest)
                .when()
                .post(endpoint);
    }

    //авторизация пользователя
    public Response postRequestLoginUser(LoginUserData userLoginReq){

        String endpoint = BurgersApiConfig.USER_AUTHORIZATION;

        return given()
                .header("Content-Type", "application/json")
                .body(userLoginReq)
                .when()
                .post(endpoint);
    }

    // удаление пользователя
    public Response deleteRequestDeletedUser(String tokenAuth){

        String endpoint = BurgersApiConfig.DELETE_OR_GET_OR_EDIT_INFORM_USER;

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", tokenAuth)
                .when()
                .delete(endpoint);
    }

}
