package com.example.memberboardproject.util.kmUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KmUtilClass {
    public static String kmDateFormat(LocalDateTime dateTime){
        if(dateTime == null){
            return null;
        }else {
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
