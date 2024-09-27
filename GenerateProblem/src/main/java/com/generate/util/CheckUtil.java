package com.generate.util;

import com.generate.common.Expression;
import com.generate.common.SignConstant;
import java.util.List;

public class CheckUtil {

    private static int plusNum = 0, multiplyNum = 0, valueNum = 0, minusNum = 0, divideNum = 0;
    private static Expression valueSum = new Expression(true, false, false, 0, 0, 1, null);

    /**
     * 判重
     * @param newProblem 新题目
     * @param sameAnswerProblems 以生成的相同答案的题目
     * @return 是否重复
     */
    public static boolean checkExpression(List<Expression> newProblem, List<List<Expression>> sameAnswerProblems) {
        //无相同答案的题目，不会重复
        if(sameAnswerProblems == null || sameAnswerProblems.isEmpty()) {
            return false;
        }
        valueNum = 0;
        plusNum = 0;
        multiplyNum = 0;
        divideNum = 0;
        minusNum = 0;
        for (Expression e : newProblem) {
            if (e.isValue() || e.isFraction()) {
                valueNum++;
                valueSum = ComputeUtil.plus(valueSum, e);
            } else if (e.isOperationSign() && e.getOperationSign().equals(SignConstant.PLUS)) {
                plusNum++;
            } else if (e.isOperationSign() && e.getOperationSign().equals(SignConstant.MULTIPLY)) {
                multiplyNum++;
            } else if (e.isOperationSign() && e.getOperationSign().equals(SignConstant.DIVIDE)) {
                divideNum++;
            } else if (e.isOperationSign() && e.getOperationSign().equals(SignConstant.MINUS)) {
                minusNum++;
            }
        }
        //新题目没有加号和乘号，不会重复
        if (plusNum == 0 && multiplyNum == 0) {
            return false;
        }
        for (List<Expression> sameAnswerProblem : sameAnswerProblems) {
            //判断两个题目是否重复
            boolean b = checkTowProblems(sameAnswerProblem);
            if (b) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两个题目是否重复
     * @param problem 题目
     * @return 是否重复
     */
    public static boolean checkTowProblems(List<Expression> problem) {
        int value = 0;
        int plus = 0;
        int multiply = 0;
        int divide = 0;
        int minus = 0;
        Expression sum = new Expression(true, false, false, 0, 0, 1, null);
        for (Expression e : problem) {
            if (e.isValue() || e.isFraction()) {
                value++;
                sum = ComputeUtil.plus(sum, e);
            } else if (e.isOperationSign() && e.getOperationSign().equals(SignConstant.PLUS)) {
                plus++;
            } else if (e.isOperationSign() && e.getOperationSign().equals(SignConstant.MULTIPLY)) {
                multiply++;
            } else if (e.isOperationSign() && e.getOperationSign().equals(SignConstant.DIVIDE)) {
                divide++;
            } else if (e.isOperationSign() && e.getOperationSign().equals(SignConstant.MINUS)) {
                minus++;
            }
        }
        if (plus != plusNum || multiply != multiplyNum || value != valueNum || divide != divideNum || minus != minusNum) {
            return false;
        }
        return sum.equals(valueSum);
    }


}
