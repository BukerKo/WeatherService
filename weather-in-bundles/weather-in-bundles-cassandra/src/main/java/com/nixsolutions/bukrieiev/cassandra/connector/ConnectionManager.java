package com.nixsolutions.bukrieiev.cassandra.connector;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;


public class ConnectionManager {

    private Cluster cluster;

    private Session session;

    public void connect(String node) {
        Builder b = Cluster.builder().addContactPoint(node);
        b.withPort(9042);
        cluster = b.build();

        session = cluster.connect();
    }

    public void connect(String node, Integer port) {
        node = "cassandra";
        Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();
        session = cluster.connect();
    }

    public void connectToKeyspace(String keyspace) {
        session.execute("USE " + keyspace + ";");
    }

    public Session getSession() {
        return this.session;
    }

    public void close() {
        session.close();
        cluster.close();
    }

}
