package JavaUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonRead {
	
public static String readJson() throws IOException {
	
	//files.readallbytes convert content of string to byte
	//string class convert byte to string
	String json=new String(Files.readAllBytes(Paths.get("./src/main/resources/addPlace.json")));
	return json;
	
}
	
}

