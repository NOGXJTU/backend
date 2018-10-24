package com.volunteer.commonweal.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");//设置日期格式
        return df.format(new Date());
    }
}
