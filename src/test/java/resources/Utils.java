package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Utils {
    public static RequestSpecification req;
    TestDataBuild testDataBuild = new TestDataBuild();


    public RequestSpecification requstSpecification(String name, String lang, String address) throws IOException {
        if(req==null) {
            String fileName = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
            PrintStream log = new PrintStream(new FileOutputStream("D:\\Automation Repos\\MasterChanges\\RestAssuredWithCucumber\\Logs\\Log" + fileName));
            RestAssured.baseURI = getTestDataProp("BaseUrl");
            String response = given().queryParams(getTestDataProp("queryKeyParam"), getTestDataProp("queryValueParam"))
                    .body(testDataBuild.addPlacePaylod(name, lang, address)).log().all().when().
                            post(getTestDataProp("postUri")).then().log().all().assertThat().statusCode(200).extract().response().asString();
            System.out.println(response);
            System.out.println("******************");
            req = new RequestSpecBuilder().setBaseUri(getTestDataProp("BaseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public static String getTestDataProp(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("D:\\Automation Repos\\MasterChanges\\RestAssuredWithCucumber\\src\\test\\java\\resources\\TestData.Properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public RequestSpecification requestSpecification() throws IOException
    {

        if(req==null)
        {
            PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
            req=new RequestSpecBuilder().setBaseUri(getTestDataProp("BaseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;


    }

    public static String getJsonPath(Response response, String key)
    {
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
