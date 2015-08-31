package jreyes.movie;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import static java.lang.System.out;

public class CassandraConnector {

    private Cluster cluster;
    private static final String ipAddress = "localhost";
    private static final int port = 9042;
    private static CassandraConnector instance;

    private CassandraConnector(){
        this.cluster = Cluster.builder().addContactPoint(ipAddress).withPort(port).build();
        final Metadata metadata = cluster.getMetadata();
        out.printf("Connected to cluster: %s\n", metadata.getClusterName());
        for(final Host host : metadata.getAllHosts()){
            out.printf("Datacenter: %s; Host: %s; Rack: %s\n",host.getDatacenter(), host.getAddress(), host.getRack());
        }
    }

    public static Session connect() throws Exception {
        if(instance == null){
            instance = new CassandraConnector();
        }
        try {
            return instance.cluster.connect();
        }catch (Exception e){
            throw e;
        }
    }

    public static void close(Cluster cluster){
        try{
            if(cluster != null){
                cluster.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
