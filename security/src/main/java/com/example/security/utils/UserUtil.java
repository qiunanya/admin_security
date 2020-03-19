package com.example.security.utils;

import java.util.Random;

/**
 * @author: Lan
 * @date: 2019/4/8 10:33
 * @description:
 */
public class UserUtil {

    /**
     * 姓
     */
    private static String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许"};

    /**
     * 手机号码前缀
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    /**
     * 返回手机号码
     */
    public static String createTel() {
        Random random = new Random();
        int index = random.nextInt(telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1000,9999));
        String third = String.valueOf(getNum(1000,9999));
        return first + second + third;
    }

    private static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1)) + start;
    }

    public static void main(String[] args) {
        System.out.println(getNum(1000,9999));
    }


    /**
     * 随机获取常用字
     *
     * @return
     */
    private static String getChinese() {
        String string = null;
        int highPos, lowPos;
        Random random = new Random();
        highPos = 176 + Math.abs(random.nextInt(71));
        random = new Random();
        lowPos = 161 + Math.abs(random.nextInt(94));

        byte[] bytes = new byte[2];
        bytes[0] = (new Integer(highPos)).byteValue();
        bytes[1] = (new Integer(lowPos)).byteValue();
        string = new String(bytes);
        return string;
    }

    /**
     * 获取随机姓名
     *
     * @return
     */
    public static String createName() {
        Random random = new Random();
        int nextInt = random.nextInt(Surname.length - 1);
        //获取随机的姓
        String name = Surname[nextInt];

        //从常用字中选取一个或两个作为名
        if (random.nextBoolean()) {
            name += getChinese() + getChinese();
        } else {
            name += getChinese();
        }
        return name;
    }
}
