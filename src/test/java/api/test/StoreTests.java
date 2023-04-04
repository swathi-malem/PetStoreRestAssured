package api.test;

import api.endpoints.StoreEndpoints;
import api.endpoints.UserEndpoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class StoreTests {
    Faker faker;
    Store userPayload;

    public Logger logger; // for logs

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new Store();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setPetId(faker.random().hashCode());
        userPayload.setQuantity(faker.number().randomDigitNotZero());
        // userPayload.setShipDate(faker.date().toString());
        userPayload.setComplete(faker.random().nextBoolean());


        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostStore() {
        logger.info("----------Create user----------");
        //   System.out.println("User Tests Payload="+userPayload.toString());
        Response response = StoreEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("----------user is created----------");

    }

    @Test(priority = 2)
    public void testGetStore() {
        logger.info("----------get user----------");

        Response response = StoreEndpoints.readStore(this.userPayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("---------- user datails are displayed----------");

    }
   @Test(priority  =3)

    public void testGetInventoryStore() {
        logger.info("----------get user----------");

        Response response = StoreEndpoints.getInventory();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("---------- user datails are displayed----------");

    }
    @Test(priority = 4)

    public void testDeleteStore() {
        logger.info("----------get user----------");

        Response response = StoreEndpoints.deleteUser(this.userPayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("---------- user datails are displayed----------");

    }
}
