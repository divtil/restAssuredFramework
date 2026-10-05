import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import collections.ReusableMethods;

import files.Payload;

public class Basics {

		ExtentSparkReporter spark ;
		ExtentReports extentReport = new ExtentReports();
		ExtentTest test;
		String path;
	
		@BeforeSuite
		public void config() {
		System.out.println("in Before Test");
		path = System.getProperty("user.dir") + "/report/index.html";
		spark = new ExtentSparkReporter((System.getProperty("user.dir"))+"/report/index.html");
		
		//spark.config().setReportName(result.getMethod().getMethodName());
		//spark.config().setDocumentTitle("Test Results");
	//	Log.builder().timestamp("date").re
		
		extentReport.attachReporter(spark); 
		
		System.out.println("Report path: " + path); 
		//System.out.println("Test Name: " + result.getMethod().getMethodName());
	}

	 @BeforeMethod
    public void createTest(ITestResult result) {

        test = extentReport.createTest(
                result.getMethod().getMethodName()
        );

        test.info("Test Started");
	
    }

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		@Test(priority=1, dataProvider="AddPlace", dataProviderClass=Snippetss.class)
		
		public void RPA(){
			
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String res = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body(matchesJsonSchemaInClasspath("AddPlace.json")).
		extract().response().asPrettyString();

			JsonPath node=new JsonPath(res);
			String token = node.get("token");

		RequestSpecification req = new RequestSpecBuilder()
		.addParam("", "")
		.setContentType(ContentType.JSON).setBaseUri("https://rahulshettyacademy.com").build();

		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).expectBody(matchesJsonSchemaInClasspath("AddPlace.json")).build();
		
		System.out.println("***************************************************");
		System.out.println(res);
		JsonPath js=new JsonPath(res);
		String reference = js.get("reference");
		System.out.println(reference); 
		

/*		JsonPath js=new JsonPath(response); //for parsing Json
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//Update Place
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
	String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
	String actualAddress =js1.getString("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, "Pacific ocean");
	//Cucumber Junit, Testng
	*/
}	
@AfterSuite
public void tearDown() 
{ 
	extentReport.flush(); 
	System.out.println("Extent report generated successfully."); 
	test.addScreenCaptureFromBase64String(path);
} 

}
