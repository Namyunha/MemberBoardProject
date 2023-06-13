package com.example.memberboardproject.util.SwUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SwUtilClass {
    public static String dateFormat(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

}
