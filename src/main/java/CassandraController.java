import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * Created with IntelliJ IDEA.
 * User: zzz
 * Date: 02/02/14
 * Time: 19:08
 */
public class CassandraController {

    private Cluster cluster;
    private Session session;
    private final String keyspace;
    private static CassandraController INSTANCE = new CassandraController();
    private CassandraController(){
        this.keyspace = "task1";

    }
    public static CassandraController getInstance(){
        return INSTANCE;
    }
    public void shutDown(){
        closeConnect();
    }

    public Session getSession(){
        setupConnect();
        return session;
    }



    private void setupConnect(){
        cluster = new Cluster.Builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect();
        session.execute("CREATE KEYSPACE if not exists  \"task1\" with replication =" +
                "  {'class': 'SimpleStrategy', 'replication_factor': 1 } ;");
        session = cluster.connect(keyspace);
    }

    private void closeConnect(){
        session.shutdown();
        cluster.shutdown();
    }





}
