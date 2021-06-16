package net.mov51.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateHelper {
    public static String getFileSafeDate(){
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss") ;

        return dateFormat.format(date);
    }
}
