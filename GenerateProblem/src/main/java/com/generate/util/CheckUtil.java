package com.generate.util;

import com.generate.common.Expression;
import com.generate.common.SignConstant;

import java.util.ArrayList;
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
            if (b) return true;
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
        List<Expression> newProblem = new ArrayList<>(List.copyOf(problem1));
        List<Expression> sameAnswerProblems = new ArrayList<>(List.copyOf(problem2));
        addParenthesisToProblem(newProblem);
        addParenthesisToProblem(sameAnswerProblems);

        return false;
    }

    /**
     * 给题目的添加括号
     * @param problem 题目
     */
    public static void addParenthesisToProblem(List<Expression> problem) {
        //先给乘号加
        for (int i = 0; i < problem.size(); i++) {
            if (problem.get(i).equals(ExpressionUtil.getSign(SignConstant.MULTIPLY))) {
                addParenthesisToProblem(i, problem);
            }
        }
        //再给加号加
        for (int i = 0; i < problem.size(); i++) {
            if (problem.get(i).equals(ExpressionUtil.getSign(SignConstant.PLUS))) {
                addParenthesisToProblem(i, problem);
            }
        }
    }

    /**
     * 给题目的添加括号
     * @param i 加号或乘号下标
     * @param problem 题目
     */
    public static void addParenthesisToProblem(int i, List<Expression> problem) {
        if (i - 2 >= 0
                && !problem.get(i - 2).equals(ExpressionUtil.getSign(SignConstant.LEFT_PARENTHESES))
                && i + 2 < problem.size()
                && !problem.get(i + 2).equals(ExpressionUtil.getSign(SignConstant.RIGHT_PARENTHESES))) {
            problem.add(i - 2, ExpressionUtil.getSign(SignConstant.LEFT_PARENTHESES));
            problem.add(i + 2, ExpressionUtil.getSign(SignConstant.RIGHT_PARENTHESES));
        }
    }

}
