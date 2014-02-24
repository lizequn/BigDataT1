import com.datastax.driver.core.Session;

/**
 * @Auther: Li Zequn
 * Date: 24/02/14
 */
public class InitDataBase {
    public static void createKeySpaceAndTable(){
        Session session = CassandraController.getInstance().getSession();


        session.execute(" create table if not exists \"logbyclient\"(\n" +
                "  clientid int,\n" +
                "  accesstime timestamp,\n" +
                "  url text,\n" +
                "  status int,\n" +
                "  size int,\n" +
                "  primary key(clientid,accesstime,url)\n" +
                "  );");
        session.execute(" create table if not exists \"logbyurl\"(\n" +
                "  clientid int,\n" +
                "  accesstime timestamp,\n" +
                "  url text,\n" +
                "  status int,\n" +
                "    size int,\n" +
                "  primary key(url,accesstime,clientid)\n" +
                "  );");
        CassandraController.getInstance().shutDown();
    }
}
