package Bootstrap.Tools;

import java.util.Calendar;
import java.util.Date;

public class DateTools {
    public static Date addSeconds(Date date, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }
}
