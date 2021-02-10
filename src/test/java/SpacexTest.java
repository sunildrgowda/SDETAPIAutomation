import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SpacexTest {
	
	String message = "This mission launches the eighteenth batch of operational Starlink satellites, which are version 1.0, from SLC-40. It is the nineteenth Starlink launch overall. The satellites will be delivered to low Earth orbit and will spend a few weeks maneuvering to their operational altitude. The booster is expected to land on an ASDS.";
	String flightNumber = "116";
	@Test
	public void verifySpaceXResponse() {
		
		RestAssured.baseURI = "https://api.spacexdata.com/v4/launches/latest";
		String rs = given().when().get().then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(rs);
		Assert.assertEquals(message, js.getString("details"));
		Assert.assertTrue(js.getBoolean("success"));
		Assert.assertTrue(js.getBoolean("fairings.recovery_attempt"));
		Assert.assertEquals(js.getString("flight_number"),flightNumber);
		
	}


}
