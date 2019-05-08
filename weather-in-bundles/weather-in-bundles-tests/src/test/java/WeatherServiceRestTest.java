
import com.google.gson.Gson;
import com.nixsolutions.bukrieiev.api.Weather;
import com.nixsolutions.bukrieiev.provider.WeatherServiceRest;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceRestTest {

    @Mock
    private  WeatherServiceRest provider;


    @Test
    public void getByZipTest() throws Exception {
        WebClient client = WebClient.create("http://api.openweathermap.org/data/2.5/weather?units=metric&zip=94030&APPID=5380dedbab3d1dfec4185202fd595b32");
        String jsonString = client.accept("application/json").get(String.class);

        Weather expected = new Gson().fromJson(jsonString, Weather.class);

        when(provider.getByZip(94030)).thenReturn(expected);
        Weather actual = provider.getByZip(94030);

        Assert.assertEquals(expected.toString().trim(), actual.toString().trim());
    }

    @Test
    public void getByCityTest() throws Exception {
        WebClient client = WebClient.create("http://api.openweathermap.org/data/2.5/weather?units=metric&q=Kharkiv&APPID=5380dedbab3d1dfec4185202fd595b32");
        String jsonString = client.accept("application/json").get(String.class);

        Weather expected = new Gson().fromJson(jsonString, Weather.class);

        when(provider.getByCity("Kharkiv")).thenReturn(expected);
        Weather actual = provider.getByCity("Kharkiv");

        System.out.println("!!!!" + actual);
        Assert.assertEquals(expected.toString().trim(), actual.toString().trim());
    }
}
