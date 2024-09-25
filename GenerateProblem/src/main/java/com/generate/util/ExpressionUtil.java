package com.generate.util;

import com.generate.common.Expression;
import com.generate.common.SignConstant;

import java.util.ArrayList;
import java.util.List;

public class ExpressionUtil {

    private static final String[] signs = {SignConstant.PLUS, SignConstant.MINUS, SignConstant.MULTIPLY, SignConstant.DIVIDE};

    /**
     * 生成由limitSize限制的自然数
     * @param limitSize 限制范围
     * @return 自然数表达式
     */
    private static Expression getValue(int limitSize) {
        int value = RandomUtil.randInt(0, limitSize - 1);
        int numerator = 0;
        int denominator = 1;
        return new Expression(true, false, false, value, numerator, denominator, null);
    }

    /**
     * 生成由limitSize限制的真分数
     * @param limitSize 限制范围
     * @return 真分数表达式
     */
    private static Expression getFraction(int limitSize) {
        int value = RandomUtil.randInt(0, limitSize - 1);
        int denominator = RandomUtil.randInt(2, limitSize - 1);
        int numerator = RandomUtil.randInt(1, denominator - 1);
        int gcd = ComputeUtil.gcd(numerator, denominator);
        denominator /= gcd;
        numerator /= gcd;
        return new Expression(false, true, false, value, numerator, denominator, null);
    }



    /**
     * 生成符号
     * @param sign 符号
     * @return 符号表达式
     */
    private static Expression getSign(String sign) {
        return new Expression(false, false,true, 0, 0, 0, sign);
    }

    /**
     * 返回表达式的字符串
     * @param expression 表达式
     * @return 表达式的字符串
     */
    public static String getString(Expression expression) {
        if (expression == null) return null;
        if (expression.isValue()) {
            return String.valueOf(expression.getValue());
        } else if (expression.isFraction()) {
            if (expression.getValue() == 0) return expression.getNumerator() + "/" + expression.getDenominator();
            else return expression.getValue() + "’" + expression.getNumerator() + "/" + expression.getDenominator();
        } else if (expression.isOperationSign()) {
            return expression.getOperationSign();
        }
        return null;
    }

    /**
     * 生成四则运算表达式
     * @param limitSize 数值限制范围
     * @return 构成四则运算表达式的表达式集合
     */
    public static List<Expression> generateExpression(int limitSize) {
        List<Expression> expressionList = new ArrayList<>();
        //运算数个数
        int expressionNum = RandomUtil.randInt(2, 4);
        //是否要括号
        boolean hasParentheses = expressionNum > 2 && RandomUtil.randInt(0, 1) == 1;
        //括号位置，第index个运算数的左或右
        int leftParenthesesIndex = -1;
        int rightParenthesesIndex = -1;
        if (hasParentheses) {
            leftParenthesesIndex = RandomUtil.randInt(1, expressionNum - 1);
            rightParenthesesIndex = RandomUtil.randInt(leftParenthesesIndex + 1, expressionNum);
        }
        //如果括号括住整个表达式则不需要加括号
        if (leftParenthesesIndex == 1 && rightParenthesesIndex == expressionNum) {
            leftParenthesesIndex = -1;
            rightParenthesesIndex = -1;
        }
        //逐个生成运算数以及运算符，并根据需要添加括号
        for (int i = 1; i <= expressionNum ; i++) {
            //添加左括号
            if (i == leftParenthesesIndex) {
                expressionList.add(getSign(SignConstant.LEFT_PARENTHESES));
            }
            //生成运算数
            boolean valueOrFraction = RandomUtil.randInt(0, 1) == 1;
            if (valueOrFraction) {
                expressionList.add(getValue(limitSize));
            } else if (limitSize > 2) {
                expressionList.add(getFraction(limitSize));
            } else {    //限制参数等于1或2时不存在合理的真分数
                return null;
            }
            //添加右括号
            if (i == rightParenthesesIndex) {
                expressionList.add(getSign(SignConstant.RIGHT_PARENTHESES));
            }
            //添加在运算数之后的运算符，最后一个运算数不用添加
            if (i == expressionNum) break;
            int operationSignIndex;
            if (limitSize == 1) {
                operationSignIndex = RandomUtil.randInt(0, signs.length - 2);
            } else {
                operationSignIndex = RandomUtil.randInt(0, signs.length - 1);
            }
            expressionList.add(getSign(signs[operationSignIndex]));
        }
        //最后添加等号
        expressionList.add(getSign(SignConstant.EQUAL));
        return expressionList;
    }

    /**
     * 根据表达式集合形成题目
     * @param expressionList 表达式集合
     * @return 四则运算题目
     */
    public static String parseExpression(List<Expression> expressionList) {
        if (expressionList == null || expressionList.isEmpty()) return null;
        StringBuilder expressionStr = new StringBuilder();
        for (Expression expression : expressionList) {
            expressionStr.append(getString(expression));
        }
        return expressionStr.toString();
    }

}
