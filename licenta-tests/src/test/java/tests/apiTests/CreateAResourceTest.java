package tests.apiTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateAResourceTest extends RestAPITest{

    @Test
    public void createAResourceTest(){
        JSONObject body = createJSONBody("test title","test body","4");
        Response response = createAResource(body);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("test body"));
        Assert.assertTrue(responseBody.contains("test title"));
    }

    @AfterMethod
    public void cleanResource(){
        deleteSpecificResourceDetails("4");
    }
}
