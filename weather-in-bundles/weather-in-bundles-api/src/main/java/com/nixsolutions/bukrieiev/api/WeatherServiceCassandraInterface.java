package com.nixsolutions.bukrieiev.api;

public interface WeatherServiceCassandraInterface {

    void registerCall(Weather weather, String place);
}
