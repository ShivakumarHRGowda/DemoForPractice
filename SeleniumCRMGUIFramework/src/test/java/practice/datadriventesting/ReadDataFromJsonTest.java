package practice.datadriventesting;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadDataFromJsonTest {
	public static void main(String[] args) throws Exception{
       JSONParser parser=new JSONParser();
       //converting JSON file type into Java Object type
       Object obj=parser.parse(new FileReader("C:\\Users\\User\\Desktop\\appCommonData.json"));
       //casting object type into JSONObject type
       JSONObject map=(JSONObject)obj;
       System.out.println(map.get("browser"));
       System.out.println(map.get("url"));
       System.out.println(map.get("username"));
       System.out.println(map.get("password"));
       System.out.println(map.get("timeout"));
	}
}
