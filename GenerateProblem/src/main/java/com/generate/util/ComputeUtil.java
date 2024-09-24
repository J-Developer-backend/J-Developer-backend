package com.generate.util;

public class ComputeUtil {

    /**
     * 求最小公约数
     * @param a 参数1
     * @param b 参数2
     * @return 最小公约数
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
