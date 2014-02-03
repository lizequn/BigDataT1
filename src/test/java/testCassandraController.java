import com.datastax.driver.core.*;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: zzz
 * Date: 02/02/14
 * Time: 19:27
 */
public class testCassandraController {
    private static CassandraController controller;

    @BeforeClass
    public static void getController(){
        controller = CassandraController.getInstance();

    }
    @AfterClass
    public static void closeController(){
        controller.shutDown();
    }
    //@Test
    public void insertRandomInformation(){
        Session session = controller.getSession();
        PreparedStatement statement1 = session.prepare("insert into logbyclient (clientid,accesstime,url,action) values(?,?,?,?)");
        PreparedStatement statement2 = session.prepare("insert into logbyurl (clientid,accesstime,url,action) values(?,?,?,?)");
        int i =0;
        int clientid;
        Date accesstime;
        String url;
        String action;
        Tools tools = new Tools();
        ResultSetFuture  resultSetFuture1 = null;
        ResultSetFuture  resultSetFuture2 = null;
        while(i<10000){
            i++;
            clientid = tools.generateRandomClientId();
            accesstime = tools.generateRandomTime();
            url = tools.generateRandomUrl();
            action = tools.generateRandomAccessAction();
            resultSetFuture1 = session.executeAsync(new BoundStatement(statement1).bind(clientid,accesstime,url,action));
            resultSetFuture2 = session.executeAsync(new BoundStatement(statement2).bind(clientid,accesstime,url,action));

        }
        resultSetFuture1.getUninterruptibly();
        resultSetFuture2.getUninterruptibly();

        assertEquals(i,10000);

    }
    @Test
    public void getInfoByClientIdAndDate(){
        Calendar startcalendar = Calendar.getInstance();
        startcalendar.set(2000, 03, 01, 02, 23, 44);
        Calendar endcalendar = Calendar.getInstance();
        endcalendar.set(2000,05,05,05,05,05);
        final int clientid = 7;
        final Date starttime = new Date(startcalendar.getTimeInMillis());
        final Date endtime = new Date(endcalendar.getTimeInMillis());
        Session session = controller.getSession();
        PreparedStatement statement = session.prepare("select * from logbyclient where clientid = ? and accesstime > ? and accesstime < ?");
        ResultSet resultRow =  session.execute(new BoundStatement(statement).bind(clientid,starttime,endtime));
        int i =0;
        for(Row row: resultRow){
            i++;
            System.out.println(row);
        }
        assertTrue(i>0);
    }
    @Test
    public void getInfoByUrl(){
        Calendar startcalendar = Calendar.getInstance();
        startcalendar.set(2000, 03, 01, 02, 23, 44);
        Calendar endcalendar = Calendar.getInstance();
        endcalendar.set(2000,05,05,05,05,05);
        final Date starttime = new Date(startcalendar.getTimeInMillis());
        final Date endtime = new Date(endcalendar.getTimeInMillis());
        final String url = "BBB";
        Session session = controller.getSession();
        PreparedStatement statement = session.prepare("select count(*) from logbyurl where url = ? and accesstime > ? and accesstime < ?");
        ResultSet resultRow =  session.execute(new BoundStatement(statement).bind(url,starttime,endtime));
        long result = resultRow.all().get(0).getLong(0);
        System.out.println(result);
        assertTrue(result>=0);
    }


}
