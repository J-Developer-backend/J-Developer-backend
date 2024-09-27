package com.generate.main;

import com.generate.common.Configuration;
import com.generate.common.Expression;
import com.generate.exception.ConfigurationNumberException;
import com.generate.exception.NumberOrLimitException;
import com.generate.exception.ProblemException;
import com.generate.util.CheckUtil;
import com.generate.util.ComputeUtil;
import com.generate.util.ExpressionUtil;
import com.generate.util.FileUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    //总题目生成数
    private static int total = 0;
    //不良题目数
    private static int failed = 0;
    //存放题目
    private static final List<List<Expression>> problems = new ArrayList<>();
    //存放答案
    private static final List<Expression> answers = new ArrayList<>();
    //存放正确的题号
    private static final List<Integer> correct = new ArrayList<>();
    //存放错误的题号
    private static final List<Integer> wrong = new ArrayList<>();

    /**
     * 主函数
     * @param args 参数
     */
    public static void main(String[] args) {
        try {
            //获取参数
            Configuration configuration = Configuration.parseConfiguration(args);
            //判断需求，生成题目or检查答案
            if (configuration.getE() == null && configuration.getA() == null) {
                //生成题目
                generateProblem(configuration.getN(), configuration.getR());
                //将题目输出到文件中
                FileUtil.writeProblem("Exercises.txt", problems);
                FileUtil.writeAnswer("Answers.txt", answers);
            } else {
                //读取题目和答案
                read(configuration.getE(), configuration.getA());
                //将所给题目重新计算
                for (int i = 0; i < problems.size(); i++) {
                    Expression correctAnswer = ComputeUtil.computeExpression(problems.get(i));
                    //判断所给题目是否能计算出结果
                    if (correctAnswer != null) {
                        //比对答案
                        if (correctAnswer.equals(answers.get(i))) {
                            //正确
                            correct.add(i + 1);
                        } else {
                            //错误
                            wrong.add(i + 1);
                        }
                    } else {
                        throw new ProblemException("提供的题目不合理");
                    }
                }
                //将统计结果输出到文件
                FileUtil.writeResult("Grade.txt", correct, wrong);
            }
        } catch (ConfigurationNumberException | NumberOrLimitException | ProblemException e) {
            System.out.println("题目总生成数" + total + "，不良率：" + 1.0 * failed / total);
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
        //缓存答案以及题目，用于判重
        Map<Expression, List<List<Expression>>> problemMap = new HashMap<>();
        int num = 0, id = 1;
        while (num < n) {
            List<Expression> problem = ExpressionUtil.generateExpression(r);
            Expression answer = ComputeUtil.computeExpression(problem);
            //校验：判合理以及判重
            if (answer != null && !CheckUtil.checkExpression(problem, problemMap.get(answer))) {
                problems.add(problem);
                answers.add(answer);
                System.out.println("题目" + id + "：" + ExpressionUtil.parseString(problem) + ExpressionUtil.getString(answer));
                List<List<Expression>> sameAnswerProblems = problemMap.get(answer);
                if (sameAnswerProblems == null) {
                    sameAnswerProblems = new ArrayList<>();
                    sameAnswerProblems.add(problem);
                    problemMap.put(answer, sameAnswerProblems);
                } else {
                    sameAnswerProblems.add(problem);
                }
                num++;
                id++;
            } else {
                failed++;
            }
            total++;
            //题目生成可行性判断
            if (1.0 * failed / total > 0.9 && 1.0 * total / n > 10) {
                throw new NumberOrLimitException("已生成" + total + "道题目，但不良率达到0.9，可能由于n、r值不合理，以至于不能生成指定数量的题目，更改n、r值或重新运行");
            }
        }
    }


    /**
     * 读取题目及答案
     * @param problemPath 题目文件路径
     * @param answerPath 答案文件路径
     */
    public static void read(String problemPath, String answerPath) {
        List<String> problemStrings = FileUtil.readProblemOrAnswer(problemPath);
        List<String> answerStrings = FileUtil.readProblemOrAnswer(answerPath);
        for (String problemString : problemStrings) {
            problems.add(ExpressionUtil.parseExpression(problemString));
        }
        for (String answerString : answerStrings) {
            answers.add(ExpressionUtil.parseOneExpression(answerString));
        }
        System.out.println("读取题目以及答案：");
        for (int i = 0; i < problems.size(); i++) {
            System.out.println("题目" + (i + 1) + "：" + ExpressionUtil.parseString(problems.get(i))
                    + ExpressionUtil.getString(answers.get(i)));
        }
    }

}
