package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
    public static Response createUser(User payload)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_user_url);
        System.out.println("create user Response="+response.asString());

        return response;
    }
    public static Response readUser(String userName)
    {
        Response response=given()
                .pathParam("username",userName)
                .when()
                .get(Routes.get_user_url);
        return response;

    }
    public static Response updateUser(String userName,User payload)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(Routes.update_user_url);
        System.out.println("update user Response="+response.asString());

        return response;
    }
    public static Response deleteUser(String userName){
        Response response=given()
                .pathParam("username",userName)
                .when()
                .delete(Routes.delete_user_url);
        return response;

    }

}
