package com.generate.common;

public class Configuration {

    private int n;
    private int r;
    private String e;
    private String a;

    public Configuration(int n, int r, String e, String a) {
        this.n = n;
        this.r = r;
        this.e = e;
        this.a = a;
    }

    /**
     * 解析参数
     * @param paramStr 参数
     * @return
     */
    public static Configuration parseConfiguration(String paramStr) {
        String[] params = paramStr.split(" ");
        if (params.length % 2 == 1) {
            return null;
        }
        int n = 0, r = 0;
        String e = null, a = null;
        for (int i = 0; i < params.length; i += 2) {
            switch (params[i]) {
                case "-n" -> n = Integer.parseInt(params[i + 1]);
                case "-r" -> r = Integer.parseInt(params[i + 1]);
                case "-e" -> e = params[i + 1];
                case "-a" -> a = params[i + 1];
                default -> {
                    return null;
                }
            }
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
