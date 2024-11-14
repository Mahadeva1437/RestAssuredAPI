import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	
	public void CoursePrice() {
		int sum=0;
		
		
		JsonPath js=new JsonPath(Payload.CoursePrice());
		int count= js.getInt("courses.size()");
		System.out.println(count);
		for (int i=0;i<count;i++) {
			
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			
			int amount=price*copies;
			 sum=sum+amount;
			System.out.println(price+" * "+copies+" = "+amount);
		}
		
		System.out.println(sum);
		int totalprice=js.getInt("dashboard.purchaseamount");
		System.out.println(totalprice);

		Assert.assertEquals(totalprice, sum);
	}

}
