package api.endpoints;

import api.payload.Store;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class StoreEndpoints {
 static ResourceBundle getUrl(){
     ResourceBundle routes=ResourceBundle.getBundle("routes");
     return routes;
 }
    public static Response createUser(Store payload)
    {
        String post_url=getUrl().getString("post_url");
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        System.out.println("create user Response="+response.asString());

        return response;
    }
    public static Response readStore(int id)
    {
        String get_url=getUrl().getString("get_url");
        Response response=given()
                .pathParam("orderId",id)
                .when()
                .get(get_url);
        return response;

    }
    public static Response getInventory()
    {
        String getInventory_url=getUrl().getString("getInventory_url");
        Response response=given()

                .when()
                .get(getInventory_url);
        return response;

    }
    public static Response deleteUser(int id){
     String delete_url=getUrl().getString("delete_url");
        Response response=given()
                .pathParam("orderId",id)
                .when()
                .delete(delete_url);
        return response;

    }

}
