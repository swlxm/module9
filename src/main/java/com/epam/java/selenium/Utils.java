package com.epam.java.selenium;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public String genTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

}
