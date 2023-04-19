package JsonObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonObj {
	
	public static void main(String[] args) {
		JSONObject jo= new JSONObject();

		jo.put("name", "student");
			JSONObject stuValue= new JSONObject();
			  stuValue.put("id", 0);
			  stuValue.put("batch", "batch@");
		jo.put("stu", stuValue);

			JSONArray courseArr=new JSONArray();
			JSONObject courseItem1=new JSONObject();
			courseItem1.put("information", "test");
			courseItem1.put("id", "3");
			courseItem1.put("name", "course1");
			courseArr.add(courseItem1);
		jo.put("course", courseArr);

			JSONArray studentAddressArr=new JSONArray();
			JSONObject studentAddressItem1=new JSONObject();
			studentAddressItem1.put("additionalinfo", "test info");
			
			JSONArray AddressArr=new JSONArray();
			JSONObject AddressItem1=new JSONObject();
			AddressItem1.put("H.No", "1234");
			AddressItem1.put("Name", "Temp Address");
			AddressItem1.put("locality","Temo locality");
			AddressItem1.put("id", 33);
			JSONObject AddressItem2=new JSONObject();
			AddressItem2.put("H.No", "1234");
			AddressItem2.put("Name", "Temp Address");
			AddressItem2.put("locality","Temo locality");
			AddressItem2.put("id", 33);
			JSONObject AddressItem3=new JSONObject();
			AddressItem3.put("H.No", "1234");
			AddressItem3.put("Name", "Temp Address");
			AddressItem3.put("locality","Temo locality");
			AddressItem3.put("id", 33);
			AddressArr.add(AddressItem1);
			AddressArr.add(AddressItem2);
			AddressArr.add(AddressItem3);
			studentAddressItem1.put("Address",AddressArr);
			
			studentAddressItem1.put("verified", true);
			studentAddressArr.add(studentAddressItem1);
			
			
		jo.put("studentAddress", studentAddressArr);
		
		
	}
	/*{
	  "name": "student",
	   "stu": {
	    "id": 0,
	    "batch": "batch@"
	  },
	  "course": [
	    {
	      "information": "test",
	      "id": "3",
	      "name": "course1"
	    }
	  ],
	  "studentAddress": [
	    {
	      "additionalinfo": "test info",
	      "Address": [
	        {
	          "H.No": "1243",
	          "Name": "Temp Address",
	          "locality": "Temp locality",
	           "id":33          
	        },
	        
	        {
	           "H.No": "1243",
	          "Name": "Temp Address",
	          "locality": "Temp locality", 
	           "id":33                   
	        },        
	        {
	           "H.No": "1243",
	          "Name": "Temp Address",
	          "locality": "Temp locality", 
	           "id":36                   
	        }
	      ],
	"verified": true,
	    }
	  ]
	}*/

}
