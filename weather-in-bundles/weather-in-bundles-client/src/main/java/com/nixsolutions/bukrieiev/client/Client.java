package com.nixsolutions.bukrieiev.client;

import com.nixsolutions.bukrieiev.api.Weather;
import com.nixsolutions.bukrieiev.provider.WeatherServiceRest;

import javax.ws.rs.core.Response;
import java.io.IOException;

public class Client {

    private WeatherServiceRest weatherServiceRest;

    public Client(WeatherServiceRest weatherServiceRest) {
        this.weatherServiceRest = weatherServiceRest;
    }

    public WeatherServiceRest getProvider() {
        return weatherServiceRest;
    }

    public Response getWeatherByZipCode(int zip) throws IOException {

        Weather weather = weatherServiceRest.getByZip(zip);
        return Response.ok(weather).build();
    }

    public Response getWeatherByCityName(String city) throws IOException {
        Weather weather = weatherServiceRest.getByCity(city);
        return Response.ok(weather).build();
    }

}
