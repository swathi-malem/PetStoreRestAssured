package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {
@Test(priority =1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userId,String userName,String fName,String lName, String useremail,String pwd,String ph) {
    User userPayload=new User();
    userPayload.setId(Integer.parseInt(userId));
    userPayload.setUsername(userName);
    userPayload.setFirstName(fName);
    userPayload.setLastName(lName);
    userPayload.setEmail(useremail);
    userPayload.setPassword(pwd);
    userPayload.setPhone(ph);

    Response response= UserEndpoints.createUser(userPayload);
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(),200);

}
    @Test(priority =2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
public void testGetUser(String userName){

    Response response=UserEndpoints.readUser(userName);
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(),200);


}
 //   @Test(priority =3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
//public void testUpdateUser(String userName){

  //  }
   @Test( priority  =3, dataProvider = "UserNames" , dataProviderClass= DataProviders.class)
    public void testDeleteUser(String userName){
  Response response=UserEndpoints.deleteUser(userName);
  response.then().log().all();
  Assert.assertEquals(response.getStatusCode(),200);
    }

}
