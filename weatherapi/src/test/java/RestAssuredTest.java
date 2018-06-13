import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredTest {

    @BeforeClass
    public static void setUp(){

        RestAssured.baseURI = "http://restapi.demoqa.com";
        RestAssured.basePath = "/utilities";

    }

    @Test
    public void checkLocation()
    {

        when().
                get("/locationutil/mylocation").
        then().
                contentType(ContentType.JSON).
        and().
                body("City", equalTo("Sofia")).
        and().
                body("Country", equalTo("Bulgaria"));
    }

    @Test
    public void checkWeather()
    {

        when().
                get("/weatherfull/city/sofia").
        then().
                contentType(ContentType.JSON).
        and().
                body("City", equalTo("Sofia")).
        and().
                body("Temperature", containsString("Degree celsius")).
        and().
                body("Humidity", containsString("Percent")).
        and().
                body("WindSpeed", containsString("Km per hour")).
        and().
                body("WindDirectionDegree", containsString("Degree"));

    }

    @Test
    public void invalidGet(){

        when().
                get("/weatherfull/city/").
        then().
                contentType(ContentType.JSON).
        and().
                body("FaultId", equalTo("FAULT_INVALID_GET_REQUEST")).
        and().
                body("fault", containsString("Invalid or Missing GET"));

    }

}
