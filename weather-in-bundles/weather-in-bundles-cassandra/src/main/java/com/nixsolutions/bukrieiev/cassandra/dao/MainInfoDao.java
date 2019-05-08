package com.nixsolutions.bukrieiev.cassandra.dao;

import com.datastax.driver.core.Session;
import com.nixsolutions.bukrieiev.api.MainInfo;
import com.nixsolutions.bukrieiev.cassandra.connector.ConnectionManager;

public class MainInfoDao {

    public void write(MainInfo info, long timestamp) {
        ConnectionManager manager = new ConnectionManager();
        manager.connect("localhost", null);
        manager.connectToKeyspace("weather");
        StringBuilder builder = new StringBuilder("INSERT INTO main JSON '").append(info.toString());
        builder.deleteCharAt(builder.length() - 1);
        builder.append(",\"time_stamp\":").append(timestamp).append("}';");
        Session session = manager.getSession();
        session.execute(builder.toString());
        session.close();
    }
}
