package com.generate.util;

import com.generate.common.Expression;
import com.generate.common.SignConstant;
import java.util.List;

public class CheckUtil {

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
        //新题目没有加号和乘号，不会重复
        if (!newProblem.contains(ExpressionUtil.getSign(SignConstant.PLUS))
                && !newProblem.contains(ExpressionUtil.getSign(SignConstant.MULTIPLY))) {
            return false;
        }
        for (List<Expression> sameAnswerProblem : sameAnswerProblems) {
            //判断两个题目是否重复
            boolean b = checkTowProblems(newProblem, sameAnswerProblem);
            if (b) {
                System.out.println(ExpressionUtil.parseString(newProblem) + "=====" + ExpressionUtil.parseString(sameAnswerProblem));
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两个题目是否重复
     * @param problem1 题目1
     * @param problem2 题目2
     * @return 是否重复
     */
    public static boolean checkTowProblems(List<Expression> problem1, List<Expression> problem2) {
        if (!problem2.contains(ExpressionUtil.getSign(SignConstant.PLUS))
                && !problem2.contains(ExpressionUtil.getSign(SignConstant.MULTIPLY))) {
            return false;
        }
        Expression e1 = new Expression(true, false, false, 0, 0, 1, null);
        Expression e2 = new Expression(true, false, false, 0, 0, 1, null);
        for (Expression expression : problem1) {
            if (expression.isValue() || expression.isFraction()) {
                e1 = ComputeUtil.plus(e1, expression);
            }
        }
        for (Expression expression : problem2) {
            if (expression.isValue() || expression.isFraction()) {
                e2 = ComputeUtil.multiply(e2, expression);
            }
        }
        return e1.equals(e2);
    }


}
