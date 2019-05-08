import com.nixsolutions.bukrieiev.api.MainInfo;
import com.nixsolutions.bukrieiev.api.Weather;
import com.nixsolutions.bukrieiev.api.WindInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WeatherTest {

    private Weather weather;

    @Before
    public void init() {
        weather = new Weather();
    }

    @Test
    public void setAndGetMainInfoTest() {
        MainInfo info = new MainInfo();
        info.setHumidity(0.0);
        info.setPressure(0.0);
        info.setTemp(0.0);
        weather.setMain(info);

        MainInfo actual = weather.getMain();

        Assert.assertEquals(info, actual);
    }

    @Test
    public void setAndGetWindInfoTest() {
        WindInfo info = new WindInfo();
        info.setSpeed(0.0);
        weather.setWind(info);

        WindInfo actual = weather.getWind();

        Assert.assertEquals(info, actual);
    }

    @Test
    public void toStringTest() {
        Weather weather = new Weather();

        Assert.assertNotNull(weather.toString());
    }

}
