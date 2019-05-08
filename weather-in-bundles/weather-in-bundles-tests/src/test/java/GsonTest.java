import com.nixsolutions.bukrieiev.api.Weather;
import com.nixsolutions.bukrieiev.handler.GsonMessageBodyHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GsonTest {

    private GsonMessageBodyHandler handler;


    @Before
    public void init() {
        handler = new GsonMessageBodyHandler();
    }

    @Test
    public void isReadableTest() {
        Assert.assertTrue(handler.isReadable(GsonMessageBodyHandler.class,
                GsonMessageBodyHandler.class, null,
                MediaType.APPLICATION_JSON_TYPE));
    }

    @Test
    public void isWritableTest() {
        Assert.assertTrue(handler.isWriteable(null, null, null, null));
    }

    @Test
    public void getSizeTest() {
        Assert.assertEquals(-1, handler.getSize(null, null, null, null, null));
    }

    @Test
    public void writeTest() throws IOException {
        Weather weather = new Weather();
        String out = "";
        OutputStream outputStream = new ByteArrayOutputStream();
        handler.writeTo(weather, Weather.class, Weather.class, null, null, null, outputStream);

        Assert.assertEquals("{}", outputStream.toString());
    }
}
