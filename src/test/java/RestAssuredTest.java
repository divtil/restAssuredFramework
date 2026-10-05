import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTest {

    @Test
    public void addPlaceApiShouldReturnSuccess() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given()
                .log().all()
                .queryParam("key", "qaclick123")
                .contentType(ContentType.JSON)
                .body(Payload.AddPlace())
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .extract()
                .response()
                .asString();

        JsonPath json = new JsonPath(response);
        String placeId = json.getString("place_id");

        Assert.assertNotNull(placeId, "place_id should not be null");
        Assert.assertFalse(placeId.isEmpty(), "place_id should not be empty");
    }
}
