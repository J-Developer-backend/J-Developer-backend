package com.generate.common;

public class Expression {

    private final boolean isValue;    //自然数标志
    private final boolean isFraction;     //分数标志
    private final boolean isOperationSign;    //符号标志
    private final int value;  //自然数值
    private final int numerator;      //分子
    private final int denominator;    //分母
    private final String operationSign;    //运算符

    public Expression(boolean isValue, boolean isFraction, boolean isOperationSign,int value, int numerator, int denominator, String operationSign) {
        this.isValue = isValue;
        this.isFraction = isFraction;
        this.isOperationSign = isOperationSign;
        this.value = value;
        this.numerator = numerator;
        this.denominator = denominator;
        this.operationSign = operationSign;
    }

    public boolean isValue() {
        return isValue;
    }

    public boolean isFraction() {
        return isFraction;
    }

    public boolean isOperationSign() {
        return isOperationSign;
    }

    public int getValue() {
        return value;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public String getOperationSign() {
        return operationSign;
    }

}
