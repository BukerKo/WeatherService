import com.nixsolutions.bukrieiev.cassandra.service.WeatherServiceCassandra;
import com.nixsolutions.bukrieiev.client.Client;
import com.nixsolutions.bukrieiev.provider.WeatherServiceRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {

    @Mock
    private WeatherServiceRest weatherServiceRest;
    private Client client;

    @Before
    public void init() {
        client = new Client(weatherServiceRest);
    }

    @Test
    public void getProviderTest() {
        WeatherServiceRest serviceRest = client.getProvider();

        Assert.assertNotNull(serviceRest);
    }

    @Test
    public void getWeatherByZipCodeTest() throws Exception {
        Response response = client.getWeatherByZipCode(94040);
        Assert.assertEquals(200, response.getStatusInfo().getStatusCode());
    }

    @Test
    public void getWeatherByCityName() throws Exception {
        Assert.assertEquals(200, client.getWeatherByCityName("Kharkiv").getStatusInfo().getStatusCode());
    }
}
