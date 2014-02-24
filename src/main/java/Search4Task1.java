import com.datastax.driver.core.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: Li Zequn
 * Date: 24/02/14
 */
public class Search4Task1 {
    public static void getInfoByClientIdAndDate(int id,Date start,Date end){
        Session session = CassandraController.getInstance().getSession();
        PreparedStatement statement = session.prepare("select * from logbyclient where clientid = ? and accesstime > ? and accesstime < ?");
        ResultSet resultRow =  session.execute(new BoundStatement(statement).bind(id,start,end).setFetchSize(100));
        int i =0;
        Iterator<Row> iterator = resultRow.iterator();
        while(iterator.hasNext()){
            if(resultRow.getAvailableWithoutFetching() == 100 && !resultRow.isFullyFetched()){
                resultRow.fetchMoreResults();
                System.out.println("do fetch");
            }
            Row row = iterator.next();
            i++;
            System.out.println(row);
        }
        System.out.println("result:"+i);
    }
    public void getInfoByUrl(List<String> urls,Date start,Date end){

        for(String url:urls){
            Session session = CassandraController.getInstance().getSession();
            PreparedStatement statement = session.prepare("select count(*) from logbyurl where url = ? and accesstime > ? and accesstime < ?");
            ResultSet resultRow =  session.execute(new BoundStatement(statement).bind(url,start,end));
            long result = resultRow.all().get(0).getLong(0);
            System.out.println(result);
        }
    }
}
