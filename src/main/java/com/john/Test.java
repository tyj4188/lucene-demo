/*
 * <p>
 * Copyright: Copyright (c) 2018-07-31 15:12
 * <p>
 * Company: 武汉斑马快跑
 * <p>
 *
 * @author tongyongjian
 * @email tongyongjian@bmkp.cn
 * @version 1.0.0
 */

package com.john;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 标题、简要说明.
 * 类详细说明.
 *
 * @author tongyongjian
 * @date 2018/7/31
 */
public class Test {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = format.parse("2018-07-01 00:00:00");
        int duration = 7;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, duration);
        Long time = calendar.getTimeInMillis();
        System.out.println(format.format(calendar.getTime()));
        System.out.println(time);
        Date now = new Date();
        System.out.println(time - now.getTime());

        Random random = new Random();
        int agreeSeed = 400 - 200 + 1;
        int disagreeSeed = 200 - 100 + 1;
        System.out.println(random.nextInt(agreeSeed) + 200);
    }
}
