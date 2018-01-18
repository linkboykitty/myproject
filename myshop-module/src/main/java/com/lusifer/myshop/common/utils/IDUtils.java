package com.lusifer.myshop.common.utils;

import java.util.Random;

public class IDUtils {
    /**
     * id生成
     */
    public static long genId() {
        // 取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        // 加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        // 如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

    public static void main(String[] args) {
        for (int i = 0 ; i < 10 ; i++) {
            System.out.println(genId());
        }
    }
}
