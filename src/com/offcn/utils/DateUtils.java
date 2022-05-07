package com.offcn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
    public static Date setDate(String str) throws ParseException {
        return sdf.parse(str);
    }
    public static String setString(Date date){
        return sdf.format(date);
    }
}
