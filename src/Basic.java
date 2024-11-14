import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.Payload;
import files.ReUsebleMethods;

public class Basic {

	public static void main(String[] args) {

		// given - all input details
		// when - Submit API -resource ,http method
		// Then - validate the parameter
		// add place -> update place with new address -> Get place ti validate if new
		// address us present in response
		//content of the file to string >content of filr can convert in                              to byte-> byte data  to string 

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).body("status", equalTo("OK")).header("Content-Type", "application/json;charset=UTF-8").extract()
				.response().asString();
		System.out.println("========Post Output Response========");
		System.out.println(response);

		JsonPath js = new JsonPath(response); // for parsing Json
		String placeId = js.getString("place_id");
		System.out.println(placeId);

		System.out.println("*******************************************");

		System.out.println("========Put Output Response========");

		String newPlaceAddress = "70 Summer walk, USA";

		// Update Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newPlaceAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		System.out.println("*******************************************");
		System.out.println("========Get Output Response========");
		// Get Place
		String getPlaceAddress = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		JsonPath js1 = ReUsebleMethods.rawToJson(getPlaceAddress);
		String ActualAddress = js1.getString("address");
		System.out.println(ActualAddress);
		
		
	}

}
