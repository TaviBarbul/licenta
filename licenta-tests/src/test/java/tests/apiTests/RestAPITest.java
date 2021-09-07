package tests.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

public class RestAPITest{

    public String mainUrl = "https://jsonplaceholder.typicode.com";

    public Response getSpecificResourceDetails(String resourceId)
    {
       return RestAssured.given()
                         .get(String.format(mainUrl +"/todos/%s",resourceId));
    }

    public void getAllResourceDetails()
    {
        Response response = RestAssured.given().get(mainUrl +"/todos");
        response.then().assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    public JSONObject createJSONBody(String title, String body, String id){
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);
        requestParams.put("body", body);
        requestParams.put("userID", id);
        return requestParams;
    }

    public Response createAResource(JSONObject jsonObject){
        return RestAssured.given()
                          .header("Content-type","application/json")
                          .body(jsonObject.toJSONString())
                          .post(mainUrl +"/posts");
    }

    public Response updateAResource(JSONObject jsonObject){
        return RestAssured.given()
                          .header("Content-type","application/json")
                          .body(jsonObject.toJSONString())
                          .put(String.format(mainUrl +"/posts/%s","1"));
    }


    public Response deleteSpecificResourceDetails(String resourceId)
    {
        return RestAssured.given()
                          .delete(String.format(mainUrl +"/posts/%s",resourceId));
    }
}
