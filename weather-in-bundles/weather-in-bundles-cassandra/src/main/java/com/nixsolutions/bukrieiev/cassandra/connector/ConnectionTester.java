package com.nixsolutions.bukrieiev.cassandra.connector;

import com.nixsolutions.bukrieiev.cassandra.utils.CassandraUtils;

import java.io.IOException;

public class ConnectionTester {
    public static void main(String[] args) throws IOException {

        String dropFile = "drop_schema.cql";
        String startUpFile = "create_schema.cql";
        CassandraUtils utils = new CassandraUtils(dropFile);
        utils.runfile();
        utils.setCQL_FILE(startUpFile);
        utils.runfile();

//
//        MainInfo info = new MainInfo();
//        info.setHumidity(0.0);
//        info.setPressure(0.0);
//        info.setTemp(0.0);
//        WindInfo windInfo = new WindInfo();
//        windInfo.setSpeed(0.0);
//        Weather weather = new Weather();
//        weather.setWind(windInfo);
//        weather.setMain(info);
//        WeatherDao dao = new WeatherDao();
//        dao.write(weather, System.currentTimeMillis(), "test");
    }
}
