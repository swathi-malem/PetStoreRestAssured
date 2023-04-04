package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;
    User userPayload;

    public Logger logger; // for logs

    @BeforeClass
    public void setup()
    {
        faker=new Faker();
        userPayload=new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

   logger= LogManager.getLogger(this.getClass());
    }

    @Test(priority=1)
    public void testPostUser()
    {
        logger.info("----------Create user----------");
     //   System.out.println("User Tests Payload="+userPayload.toString());
        Response response= UserEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("----------user is created----------");

    }

    @Test(priority=2)
    public void testGetUserByName()
    {
        logger.info("----------get user----------");

        Response response=UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("---------- user datails are displayed----------");

    }
    @Test(priority=3)
    public void testUpdateUserByName()

    {
        logger.info("----------Update user----------");

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response= UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
       //checkin data after updating
       Response responseAfterUpdate=UserEndpoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("----------Updated user datails----------");



    }
    @Test(priority=4)
    public void testDeleteUserByName(){
        logger.info("----------Delete user----------");

        Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("----------user details are deleted----------");

    }

}
