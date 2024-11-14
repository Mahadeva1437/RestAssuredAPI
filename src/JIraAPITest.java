import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JIraAPITest {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI="https://testingall3928.atlassian.net/";
	String BugId=	given().header("Content-Type","application/json")
		.header("Authorization","Basic dGVzdGluZ2FsbDM5MjhAZ21haWwuY29tOkFUQVRUM3hGZkdGMGdudWQ1MU96dzJHQ1d1ODRBd3VEenVtc2tiZTZuUndEWm1MYnY4NFlER0tjd2hsdUZ4QmxJYmFrT3FOZmRvblBtbWlDNmwwV2I2c05VVGVmdVQwaFFMMTBCMmpxX2hCZFlEZGxoR1FpZG5SSkNkbkh1bzd5M2w1aUstRHFXaHdRUkdGZGVQaUROczlwU1dwdkt5V3g5WEpTRzQ2YmZhREhGc1Z4a1pjOW1IND00NEZENUVENw==")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"RES\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"End to End Module"
				+ " not working\",\r\n"
				+ "              \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
//		.log().all()
		.post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().asString();
		
	JsonPath js=new JsonPath(BugId);
	String issueId=js.getString("id");
	System.out.println(issueId);
	System.out.println("===========================================================");
		
	
	//Attachment file
	
	given().header("X-Atlassian-Token","no-check").pathParam("key", issueId)
	.header("Authorization","Basic dGVzdGluZ2FsbDM5MjhAZ21haWwuY29tOkFUQVRUM3hGZkdGMGdudWQ1MU96dzJHQ1d1ODRBd3VEenVtc2tiZTZuUndEWm1MYnY4NFlER0tjd2hsdUZ4QmxJYmFrT3FOZmRvblBtbWlDNmwwV2I2c05VVGVmdVQwaFFMMTBCMmpxX2hCZFlEZGxoR1FpZG5SSkNkbkh1bzd5M2w1aUstRHFXaHdRUkdGZGVQaUROczlwU1dwdkt5V3g5WEpTRzQ2YmZhREhGc1Z4a1pjOW1IND00NEZENUVENw==")
	.multiPart("file",new File("C:\\Users\\Official\\Desktop\\Images PowerPoints\\photo_2024-02-09_19-24-26.jpg"))
//	.log().all()
	.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	
	System.out.println("===========================================================");
	
	given()
	.header("Authorization","Basic dGVzdGluZ2FsbDM5MjhAZ21haWwuY29tOkFUQVRUM3hGZkdGMGdudWQ1MU96dzJHQ1d1ODRBd3VEenVtc2tiZTZuUndEWm1MYnY4NFlER0tjd2hsdUZ4QmxJYmFrT3FOZmRvblBtbWlDNmwwV2I2c05VVGVmdVQwaFFMMTBCMmpxX2hCZFlEZGxoR1FpZG5SSkNkbkh1bzd5M2w1aUstRHFXaHdRUkdGZGVQaUROczlwU1dwdkt5V3g5WEpTRzQ2YmZhREhGc1Z4a1pjOW1IND00NEZENUVENw==")
	.pathParam("key", issueId)
	.get("rest/api/2/issue/{key}").then().log().all().assertThat().statusCode(200);
		
	System.out.println("===========================================================");
	}
}
