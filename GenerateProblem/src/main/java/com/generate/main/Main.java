package com.generate.main;

import com.generate.common.Configuration;
import com.generate.common.Expression;
import com.generate.exception.ConfigurationException;
import com.generate.exception.NumberOrLimitException;
import com.generate.exception.ProblemException;
import com.generate.util.ComputeUtil;
import com.generate.util.ExpressionUtil;
import com.generate.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<List<Expression>> problems = new ArrayList<>();
    private static final List<Expression> answers = new ArrayList<>();
    private static final List<Integer> correct = new ArrayList<>();
    private static final List<Integer> wrong = new ArrayList<>();

    /**
     * 主函数
     * @param args 参数
     */
    public static void main(String[] args) {
        try {
            Configuration configuration = Configuration.parseConfiguration(args);
            if (configuration.getE() == null && configuration.getA() == null) {
                generateProblem(configuration.getN(), configuration.getR());
                write();
            } else {
                List<String> problemStrings = read(configuration.getE());
                List<String> answerStrings = read(configuration.getA());
                for (String problemString : problemStrings) {
                    problems.add(ExpressionUtil.parseExpression(problemString));
                }
                for (String answerString : answerStrings) {
                    answers.add(ExpressionUtil.parseOneExpression(answerString));
                }
                for (int i = 0; i < problems.size(); i++) {
                    Expression expression = ComputeUtil.computeExpression(problems.get(i));
                    if (expression != null) {
                        if (expression.equals(answers.get(i))) {
                            correct.add(i + 1);
                        } else {
                            wrong.add(i + 1);
                        }
                    } else {
                        throw new ProblemException("提供的题目不合理");
                    }
                }
                FileUtil.writeResult("Grade.txt", correct, wrong);
            }
        } catch (ConfigurationException | NumberOrLimitException | ProblemException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成题目
     * @param n 数量
     * @param r 限制
     * @throws NumberOrLimitException 参数不合理异常
     */
    public static void generateProblem(int n, int r) throws NumberOrLimitException {
        int num = 0, total = 0, failed = 0;
        while (num < n) {
            List<Expression> problem = ExpressionUtil.generateExpression(r);
            Expression answer = ComputeUtil.computeExpression(problem);
            //TODO 校验
            if (answer != null) {
                problems.add(problem);
                answers.add(answer);
                num++;
            } else {
                failed++;
            }
            total++;
            if (1.0 * failed / total > 0.5) {
                throw new NumberOrLimitException("题目生成不良率达到0.5，所以r值不合理，以至于不能生成指定数量的题目");
            }
        }
    }

    /**
     * 输出题目和答案
     */
    public static void write() {
        FileUtil.writeProblem("Exercises.tx", problems);
        FileUtil.writeAnswer("Answers.txt", answers);
    }

    /**
     * 读取题目
     * @param path 题目文件路径
     * @return 题目字符串集合
     */
    public static List<String> read(String path) {
        return FileUtil.readProblem(path);
    }

}
