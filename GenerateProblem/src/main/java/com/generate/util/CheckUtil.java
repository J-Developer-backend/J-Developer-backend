package com.generate.util;

import com.generate.common.Expression;
import com.generate.common.SignConstant;
import java.util.List;

public class CheckUtil {

    public static boolean checkExpression(List<Expression> problem, List<List<Expression>> sameAnswerProblems) {
        if(sameAnswerProblems == null || sameAnswerProblems.isEmpty()) {
            return true;
        }
        if (!problem.contains(ExpressionUtil.getSign(SignConstant.PLUS))
                && !problem.contains(ExpressionUtil.getSign(SignConstant.MULTIPLY))) {
            return true;
        }
        for (List<Expression> sameAnswerProblem : sameAnswerProblems) {
            if (sameAnswerProblem.contains(ExpressionUtil.getSign(SignConstant.PLUS))
                    || sameAnswerProblem.contains(ExpressionUtil.getSign(SignConstant.MULTIPLY))) {
                return false;
            }
        }
        return true;
    }

}
