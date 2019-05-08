package com.nixsolutions.bukrieiev.cassandra.utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CassandraUtils {
    static String CREATE_KEYSPACE;
    static String DROP_KEYSPACE;
    private static final String RESOURCES_DIR = "weather-in-bundles/weather-in-bundles-cassandra/src/main/resources";


    private Cluster cluster;
    private Session session;


    private String CQL_FILE;

    public CassandraUtils(String cqlFile) {

        this.CQL_FILE = cqlFile;

        String contactPointsStr = System.getProperty("contactPoints");
        if (contactPointsStr == null) {
            contactPointsStr = "cassandra";
        }

        cluster = Cluster.builder().addContactPoints(contactPointsStr.split(",")).build();
        session = cluster.connect();
    }

    void internalSetup() throws IOException {
        this.runfile();
    }

    public void runfile() throws IOException {

        String readFileIntoString = readFileIntoString(CQL_FILE);

        String[] commands = readFileIntoString.split(";");

        for (String command : commands) {

            String cql = command.trim();

            if (cql.isEmpty()) {
                continue;
            }

            if (cql.toLowerCase().startsWith("drop")) {
                this.runAllowFail(cql);
            } else {
                this.run(cql);
            }
        }
    }

    void runAllowFail(String cql) {
        try {
            run(cql);
        } catch (InvalidQueryException e) {
        }
    }

    public void run(String cql) {
        session.execute(cql);
    }

    void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
        }
    }


    void shutdown() {
        session.close();
        cluster.close();
    }

    public static String readFileIntoString(String filename) throws IOException {

        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        File file = new File(RESOURCES_DIR, filename);

        try {
            String currentLine;
            br = new BufferedReader(new FileReader(file));

            while ((currentLine = br.readLine()) != null) {
                buffer.append(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return buffer.toString();
    }

    public void setCQL_FILE(String CQL_FILE) {
        this.CQL_FILE = CQL_FILE;
    }

}
