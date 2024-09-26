package com.generate.common;

import com.generate.exception.ConfigurationNumberException;

public class Configuration {


    private final int n;
    private final int r;
    private final String e;
    private final String a;

    public Configuration(int n, int r, String e, String a) throws ConfigurationNumberException {
        this.n = n;
        this.r = r;
        this.e = e;
        this.a = a;
    }

    /**
     * 解析参数
     * @param params 参数
     * @return 参数对象
     */
    public static Configuration parseConfiguration(String[] params) throws ConfigurationNumberException {
        if (params.length % 2 != 0) {
            throw new ConfigurationNumberException("参数异常");
        }
        int n = -1, r = -1;
        String e = null, a = null;
        for (int i = 0; i < params.length; i += 2) {
            switch (params[i]) {
                case "-n" -> n = Integer.parseInt(params[i + 1]);
                case "-r" -> r = Integer.parseInt(params[i + 1]);
                case "-e" -> e = params[i + 1];
                case "-a" -> a = params[i + 1];
                default -> throw new ConfigurationNumberException("参数异常");
            }
        }
        if ((r == -1 || n == -1) && e == null && a == null) {
            throw new ConfigurationNumberException("参数异常");
        }
        if (!(e == null && a == null) && !(e != null && a != null)) {
            throw new ConfigurationNumberException("参数异常");
        }
        return new Configuration(n, r, e, a);
    }

    public int getN() {
        return n;
    }

    public int getR() {
        return r;
    }

    public String getE() {
        return e;
    }

    public String getA() {
        return a;
    }
}
