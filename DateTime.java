package calorieintake;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

    //class to get current date and time

    public String getDate(){
        String dayStr = dayFormat.format(new Date());
        String fullDateStr = dayStr + " " + dateFormat.format(cal.getTime());

        return fullDateStr;
    }

    public String getTime(){
        String timeStr = timeFormat.format(new Date());

        return timeStr;
    }

    private Calendar cal = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private Format dayFormat = new SimpleDateFormat("EEEE");

}
