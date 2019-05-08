package com.nixsolutions.bukrieiev.cassandra.service;

import com.nixsolutions.bukrieiev.api.Weather;
import com.nixsolutions.bukrieiev.api.WeatherServiceCassandraInterface;
import com.nixsolutions.bukrieiev.cassandra.dao.WeatherDao;
import com.nixsolutions.bukrieiev.cassandra.utils.CassandraUtils;

import java.io.IOException;

public class WeatherServiceCassandra implements WeatherServiceCassandraInterface {

    public void registerCall(Weather weather, String place) {
        WeatherDao dao = new WeatherDao();
        dao.write(weather, System.currentTimeMillis(), place);
    }

    public void startUp() throws IOException {
        String dropFile = "drop_schema.cql";
        CassandraUtils utils = new CassandraUtils(dropFile);
        utils.run("drop keyspace if exists weather;");
        utils.run("create keyspace if not exists weather WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1' };");
        utils.run("use weather");
        utils.run("create table if not exists weather.main (\n" +
                "    time_stamp timestamp primary key,\n" +
                "    humidity double,\n" +
                "    pressure double,\n" +
                "    temp double);\n");
        utils.run("create table if not exists weather.wind (\n" +
                "    time_stamp timestamp primary key,\n" +
                "    speed double);\n");
        utils.run("create table if not exists weather.weather (\n" +
                "    time_stamp timestamp primary key,\n" +
                "    place text);");

    }
}
