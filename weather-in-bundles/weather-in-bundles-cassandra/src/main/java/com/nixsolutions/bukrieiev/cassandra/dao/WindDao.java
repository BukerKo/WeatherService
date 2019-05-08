package com.nixsolutions.bukrieiev.cassandra.dao;

import com.datastax.driver.core.Session;
import com.nixsolutions.bukrieiev.api.WindInfo;
import com.nixsolutions.bukrieiev.cassandra.connector.ConnectionManager;

public class WindDao {

    public void write(WindInfo info, long timestamp)  {
        ConnectionManager manager = new ConnectionManager();
        manager.connect("localhost", null);
        manager.connectToKeyspace("weather");
        StringBuilder builder = new StringBuilder("INSERT INTO wind JSON '").append(info.toString());
        builder.deleteCharAt(builder.length() - 1);
        builder.append(",\"time_stamp\":").append(timestamp).append("}';");
        Session session = manager.getSession();
        session.execute(builder.toString());
        session.close();
    }
}
