package com.ycy.util;

import java.util.Random;

public class RandomData {
    /**
     * 获取随机数
     * @param min
     * @param max
     * @return
     */
    public int getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }
}
