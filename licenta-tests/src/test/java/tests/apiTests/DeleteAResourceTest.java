package tests.apiTests;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteAResourceTest extends RestAPITest{

    @BeforeMethod
    public void createAResource(){
        JSONObject body = createJSONBody("test title","test body","5");
        createAResource(body).then().statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void deleteAResourceTest(){
        deleteSpecificResourceDetails("5").then().statusCode(HttpStatus.SC_OK);
    }
}
