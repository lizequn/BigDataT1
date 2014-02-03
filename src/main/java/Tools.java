import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: zzz
 * Date: 02/02/14
 * Time: 19:54
 */
public class Tools {
    private Random r  = new Random();
    private Vector<String> action = new Vector<>();
    private Vector<String> url = new Vector<>();
    private Vector<Integer> clientId = new Vector<>();
    public Tools(){
        action.add("Get");
        action.add("Post");
        action.add("Delete");
        url.add("AAA");
        url.add("BBB");
        url.add("CCC");
        url.add("DDD");
        url.add("EEE");
        url.add("FFF");
        url.add("GGG");
        url.add("HHH");
        url.add("III");
        url.add("JJJ");
        url.add("KKK");
        url.add("LLL");
        url.add("MMM");
        url.add("NNN");
        url.add("OOO");
        url.add("PPP");
        url.add("QQQ");
        url.add("RRR");
        url.add("SSS");
        url.add("TTT");
        for(int i = 0;i<20;i++){
            clientId.add(i);
        }
    }

    public  Date generateRandomTime(){
        Calendar calendar = Calendar.getInstance();
        int year = 2000;
        int month = (r.nextInt(12)+1);
        int day = (r.nextInt(28)+1);
        int hour = r.nextInt(24);
        int min = r.nextInt(60);
        int sec = r.nextInt(60);
        calendar.set(year,month,day,hour,min,sec);
        return new Date(calendar.getTimeInMillis());
    }
    public String generateRandomUrl(){
        return url.get(r.nextInt(url.size()));
    }
    public String generateRandomAccessAction() {
        return action.get(r.nextInt(action.size()));
    }
    public int generateRandomClientId(){
        return clientId.get(r.nextInt(clientId.size()));
    }

}
