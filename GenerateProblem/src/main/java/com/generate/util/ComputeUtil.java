package com.generate.util;

import com.generate.common.Expression;
import com.generate.common.SignConstant;

import java.util.*;

public class ComputeUtil {


    /**
     * 求最大公约数
     *
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

    /**
     * 获取两数的最小公倍数
     * @param a 参数1
     * @param b 参数2
     * @return 最小公倍数
     */
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * 求取表达式集合的答案表达式
     *
     * @param expressions 表达式集合
     * @return 答案表达式
     */
    public static Expression computeExpression(List<Expression> expressions) {
        //不合理的表达式集合
        if (expressions == null || expressions.isEmpty()) {
            return null;
        }
        //获取后缀表达式
        List<Expression> expressionsRPN = getExpressionsRPN(expressions);
        //计算后缀表达式并返回
        return computeRPN(expressionsRPN);
    }

    /**
     * 计算后缀表达式
     * @param expressions 原表达式
     * @return 后缀表达式
     */
    public static List<Expression> getExpressionsRPN(List<Expression> expressions) {
        if (expressions == null || expressions.isEmpty()) {
            return null;
        }
        List<Expression> result = new ArrayList<>();
        //栈用于存放符号
        Stack<Expression> stack = new Stack<>();
        for (Expression expression : expressions) {
            //符号需要考虑进栈与出栈
            if (expression.isOperationSign()) {
                switch (expression.getOperationSign()) {
                    //右括号，将左括号及其到栈顶的符号出栈
                    case SignConstant.RIGHT_PARENTHESES -> {
                        while (!stack.isEmpty()) {
                            Expression temp = stack.pop();
                            if (temp.getOperationSign().equals(SignConstant.LEFT_PARENTHESES)) {
                                break;
                            }
                            result.add(temp);
                        }
                    }
                    //左括号直接进栈
                    case SignConstant.LEFT_PARENTHESES -> stack.push(expression);
                    //加减运算符，将占清空后或栈顶为左括号进栈
                    case SignConstant.PLUS, SignConstant.MINUS -> {
                        while (!stack.isEmpty()) {
                            Expression temp = stack.peek();
                            if (temp.getOperationSign().equals(SignConstant.LEFT_PARENTHESES)) {
                                break;
                            }
                            result.add(temp);
                            stack.pop();
                        }
                        stack.push(expression);
                    }
                    //乘除运算符，只有在栈空或优先级大于栈顶符号时进栈，否则将栈中的符号弹出，直至满足条件后进栈
                    case SignConstant.MULTIPLY, SignConstant.DIVIDE -> {
                        if (stack.isEmpty()) {
                            stack.push(expression);
                        } else {
                            while (!stack.empty()) {
                                Expression top = stack.peek();
                                if (top.getOperationSign().equals(SignConstant.PLUS) ||
                                        top.getOperationSign().equals(SignConstant.MINUS) ||
                                        top.getOperationSign().equals(SignConstant.LEFT_PARENTHESES)) {
                                    break;
                                } else {
                                    stack.pop();
                                    result.add(top);
                                }
                            }
                            stack.push(expression);
                        }
                    }
                }
            } else {    //数值不用进栈
                result.add(expression);
            }
        }
        //最后清空栈
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * 计算后缀表达式
     * @param expressions 后缀表达式
     * @return 计算结果，null表示不合法
     */
    public static Expression computeRPN(List<Expression> expressions) {
        if (expressions == null || expressions.isEmpty()) {
            return null;
        }
        Stack<Expression> stack = new Stack<>();
        for (Expression expression : expressions) {
            //符号要进行对应运算
            if (expression.isOperationSign()) {
                Expression temp, ex1, ex2;
                temp = null;
                ex2 = stack.pop();
                ex1 = stack.pop();
                switch (expression.getOperationSign()) {
                    case SignConstant.PLUS -> temp = plus(ex1, ex2);
                    case SignConstant.MINUS -> temp = minus(ex1, ex2);
                    case SignConstant.MULTIPLY -> temp = multiply(ex1, ex2);
                    case SignConstant.DIVIDE -> temp = divide(ex1, ex2);
                }
                if (temp != null) {
                    stack.push(temp);
                } else {
                    return null;
                }
            } else {    //数值直接进栈
                stack.push(expression);
            }
        }
        return stack.pop();
    }

    /**
     * 通过分母和分子获取表达式
     * @param numerator 分子
     * @param denominator 分母
     * @return 表达式，null表示不合法
     */
    public static Expression getFraction(int numerator, int denominator) {
        if (numerator < 0) {
            return null;
        }
        int newValue = numerator / denominator;
        numerator = numerator % denominator;
        if (numerator == 0) {
            return new Expression(true, false, false, newValue, 0, 1, null);
        } else {
            int gcd = gcd(numerator, denominator);
            denominator /= gcd;
            numerator /= gcd;
            return new Expression(false, true, false, newValue, numerator, denominator, null);
        }
    }

    /**
     * 加法运算
     * @param ex1 表达式1
     * @param ex2 表达式2
     * @return 计算结果，null表示不合法
     */
    public static Expression plus(Expression ex1, Expression ex2) {
        int newDenominator = lcm(ex1.getDenominator(), ex2.getDenominator());
        int newNumerator = (ex1.getValue() * ex1.getDenominator() + ex1.getNumerator()) * (newDenominator / ex1.getDenominator())
                + (ex2.getValue() * ex2.getDenominator() + ex2.getNumerator()) * (newDenominator / ex2.getDenominator());
        return getFraction(newNumerator, newDenominator);
    }

    /**
     * 减法运算
     * @param ex1 表达式1
     * @param ex2 表达式2
     * @return 计算结果，null表示不合法
     */
    public static Expression minus(Expression ex1, Expression ex2) {
        int newDenominator = lcm(ex1.getDenominator(), ex2.getDenominator());
        int newNumerator = (ex1.getValue() * ex1.getDenominator() + ex1.getNumerator()) * (newDenominator / ex1.getDenominator())
                - (ex2.getValue() * ex2.getDenominator() + ex2.getNumerator()) * (newDenominator / ex2.getDenominator());
        return getFraction(newNumerator, newDenominator);
    }

    /**
     * 乘法运算
     * @param ex1 表达式1
     * @param ex2 表达式2
     * @return 计算结果，null表示不合法
     */
    public static Expression multiply(Expression ex1, Expression ex2) {
        int newDenominator = ex1.getDenominator() * ex2.getDenominator();
        int newNumerator = (ex1.getValue() * ex1.getDenominator() + ex1.getNumerator()) * (ex2.getValue() * ex2.getDenominator() + ex2.getNumerator());
        return getFraction(newNumerator, newDenominator);
    }

    /**
     * 除法运算
     * @param ex1 表达式1
     * @param ex2 表达式2
     * @return 计算结果，null表示不合法
     */
    public static Expression divide(Expression ex1, Expression ex2) {
        if (ex2.getValue() == 0 && ex2.getNumerator() == 0) {
            return null;
        }
        int numerator1 = ex1.getValue() * ex1.getDenominator() + ex1.getNumerator();
        int numerator2 = ex2.getValue() * ex2.getDenominator() + ex2.getNumerator();
        int newDenominator = ex1.getDenominator() * numerator2;
        int newNumerator = numerator1 * ex2.getDenominator();
        return getFraction(newNumerator, newDenominator);
    }

}

