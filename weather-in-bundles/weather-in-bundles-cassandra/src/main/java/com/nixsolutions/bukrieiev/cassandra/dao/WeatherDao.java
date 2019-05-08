package com.nixsolutions.bukrieiev.cassandra.dao;

import com.datastax.driver.core.Session;
import com.nixsolutions.bukrieiev.api.Weather;
import com.nixsolutions.bukrieiev.cassandra.connector.ConnectionManager;

public class WeatherDao {

    public void write(Weather weather, long timestamp, String place) {
        ConnectionManager manager = new ConnectionManager();
        manager.connect("localhost", null);
        manager.connectToKeyspace("weather");
        StringBuilder builder = new StringBuilder("INSERT INTO weather (time_stamp, place) VALUES (").append(timestamp)
                .append(",").append("'").append(place).append("');");
        Session session = manager.getSession();
        session.execute(builder.toString());
        session.close();

        MainInfoDao mainInfoDao = new MainInfoDao();
        mainInfoDao.write(weather.getMain(), timestamp);
        WindDao windDao = new WindDao();
        windDao.write(weather.getWind(), timestamp);
    }
}
