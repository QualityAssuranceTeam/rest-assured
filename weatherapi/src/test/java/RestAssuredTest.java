import com.jayway.restassured.http.ContentType;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredTest {

    @Test
    public void checkLocation()
    {
        when().
                get("http://restapi.demoqa.com/utilities/locationutil/mylocation").
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
                get("http://restapi.demoqa.com/utilities/weatherfull/city/sofia").
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

}
