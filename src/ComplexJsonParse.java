import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	
	public static void main(String[] args) {
		
		JsonPath js=new JsonPath(Payload.CoursePrice());
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		int totalAmount=js.getInt("dashboard.purchaseamount");
		System.out.println(totalAmount);
		
		String courseTitle=js.get("courses[0].title");
		System.out.println(courseTitle);
		System.out.println("/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
		
		for(int i=0;i<count;i++) {
			
		
		System.out.println(js.get("courses["+i+"].title").toString());
		System.out.println(js.get("courses["+i+"].price").toString());
		}
		
		System.out.println("print nof copies sold by RPA");
		
		for(int i=0;i<count;i++) {
			String titleCourses=js.get("courses["+i+"].title");
			if(titleCourses.equalsIgnoreCase("Selenium Python")) {
				
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
			
		}
		
		
	}
}
