import com.datastax.driver.core.*;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

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
//        controller = CassandraController.getInstance();

    }
    @AfterClass
    public static void closeController(){
        //controller.shutDown();
    }
    @Test
    public void getInformation() throws ParseException, InterruptedException, IOException {
        InitDataBase.createKeySpaceAndTable();
        DataInput input = new DataInput("D:/","loglite");
        //input.transferData();
        Calendar startcalendar = Calendar.getInstance();
        startcalendar.set(1998, 03, 01, 02, 23, 44);
        Calendar endcalendar = Calendar.getInstance();
        endcalendar.set(1998,05,05,05,05,05);
        final Date starttime = new Date(startcalendar.getTimeInMillis());
        final Date endtime = new Date(endcalendar.getTimeInMillis());
        Search4Task1.getInfoByClientIdAndDate(1,starttime,endtime);
    }



}
