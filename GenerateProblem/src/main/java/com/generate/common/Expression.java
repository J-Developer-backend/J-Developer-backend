package com.generate.common;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(isValue, isFraction, isOperationSign, value, numerator, denominator, operationSign);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Expression other = (Expression) obj;
        if (isValue) {
            return other.isValue && value == other.value;
        }
        if (isFraction) {
            return other.isFraction && value == other.value && numerator == other.numerator && denominator == other.denominator;
        }
        if (isOperationSign) {
            return other.isOperationSign && operationSign.equals(other.operationSign);
        }
        return false;
    }
}
