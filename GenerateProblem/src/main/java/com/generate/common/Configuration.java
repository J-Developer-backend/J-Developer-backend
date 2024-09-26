package com.generate.common;

import com.generate.exception.ConfigurationException;

public class Configuration {

    private final int n;
    private final int r;
    private final String e;
    private final String a;

    public Configuration(int n, int r, String e, String a) {
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
    public static Configuration parseConfiguration(String[] params) throws ConfigurationException {
        if (params.length % 2 != 0) {
            throw new ConfigurationException("Wrong number of parameters");
        }
        int n = -1, r = -1;
        String e = null, a = null;
        for (int i = 0; i < params.length; i += 2) {
            switch (params[i]) {
                case "-n" -> n = Integer.parseInt(params[i + 1]);
                case "-r" -> r = Integer.parseInt(params[i + 1]);
                case "-e" -> e = params[i + 1];
                case "-a" -> a = params[i + 1];
                default -> {
                    throw new ConfigurationException("Wrong number of parameters");
                }
            }
        }
        if (!(e == null && a == null) && !(e != null && a != null)) {
            throw new ConfigurationException("Wrong number of parameters");
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
