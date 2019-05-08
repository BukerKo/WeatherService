import com.nixsolutions.bukrieiev.api.MainInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainInfoTest {

    private MainInfo mainInfo;

    @Before
    public void init() {
        mainInfo = new MainInfo();
    }

    @Test
    public void setAndGetTemperatureTest() {
        Double temp = 0.0;
        mainInfo.setTemp(temp);

        Assert.assertEquals(temp, mainInfo.getTemp());
    }

    @Test
    public void setAndGetPressureTest() {
        Double pressure = 0.0;
        mainInfo.setPressure(pressure);

        Assert.assertEquals(pressure, mainInfo.getPressure());
    }

    @Test
    public void setAndGetHumidityTest() {
        Double humidity = 0.0;
        mainInfo.setHumidity(humidity);

        Assert.assertEquals(humidity, mainInfo.getHumidity());
    }

    @Test
    public void toStringTest() {
        MainInfo mainInfo = new MainInfo();

        Assert.assertNotNull(mainInfo.toString());
    }
}
