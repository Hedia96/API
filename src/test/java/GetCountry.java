import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GetCountry {
    @Test
    public void getCountryByParam()
    {
        //logs REQ,RES
        given().log().all().
                when().get("http://api.zippopotam.us/us/90210")
                .then().assertThat().log().body().statusCode(200).body("places.'place name'",hasItem("Beverly Hills"));
        // the in hasItem should be the first parameter is a collection
    }
    @DataProvider(name="parameterAndPlaces")
    public static Object[][] parameterAndPlaces()
    {
        return  new Object[][]
                {
                        {"us","90210","Beverly Hills"},{"ca","B2R","Waverley"}
                };
    }
    @Test(dataProvider= "parameterAndPlaces")
        public void parametersRestAsss(String parCountry,String parAbb,String place)
    {
        // query parameter http://api.zippopotam.us/?text=aya
        // path parameter http://api.zippopotam.us/us/90210"
        /**
         * 1-create test data collection can be used by any TestNG or Junit as @Dataprovider
         * as not provided by RestAssured itself
         * 2- Apply the data in @Test which responsibilty of unit frameworks
         */
        given().log().all()
                .pathParam("Countrycode",parCountry).pathParam("ZipCode",parAbb)
                .when().
                get("http://api.zippopotam.us/{Countrycode}/{ZipCode}").
                then().
                    assertThat().
                    body("places[0].'place name'",equalTo(place));




         }
}



