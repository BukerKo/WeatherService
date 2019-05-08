package com.nixsolutions.bukrieiev.camel;

        import com.nixsolutions.bukrieiev.api.Weather;

public interface CamelForwarderInterface {

    void forward(Weather weather);
}
