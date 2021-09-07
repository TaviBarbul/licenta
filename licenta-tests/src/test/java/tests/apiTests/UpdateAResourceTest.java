package tests.apiTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateAResourceTest extends RestAPITest{

    @BeforeMethod
    public void createAResource(){
        JSONObject body = createJSONBody("test title","test body","6");
        createAResource(body).then().statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void updateAResourceTest(){
        JSONObject body = createJSONBody("test title edited","test body edited","6");
        Response response = updateAResource(body);
        response.then().statusCode(HttpStatus.SC_OK);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("test title edited"));
        Assert.assertTrue(responseBody.contains("test body edited"));
    }

    @AfterMethod
    public void deleteResource(){
        deleteSpecificResourceDetails("6");
    }
}
