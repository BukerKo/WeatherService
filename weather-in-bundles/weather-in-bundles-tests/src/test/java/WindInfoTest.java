import com.nixsolutions.bukrieiev.api.WindInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WindInfoTest {

    private WindInfo windInfo;

    @Before
    public void init() {
        windInfo = new WindInfo();
    }

    @Test
    public void setAndGetSpeedTest() {
        Double speed = 0.0;
        windInfo.setSpeed(speed);

        Assert.assertEquals(speed, windInfo.getSpeed());
    }

    @Test
    public void toStringTest() {
        WindInfo windInfo = new WindInfo();

        Assert.assertNotNull(windInfo.toString());
    }
}
