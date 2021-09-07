package tests.apiTests;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSpecificResourceTest extends RestAPITest{

    @Test
    public void getSpecificResourceTest(){
        Response response = getSpecificResourceDetails("1");
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("delectus aut autem"));
    }
}
