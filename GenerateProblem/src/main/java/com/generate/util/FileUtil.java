package com.generate.util;

import com.generate.common.Expression;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class FileUtil {


    /**
     * 读取题目或答案
     * @param filePath 文件路径
     * @return 表达式列表
     */
    public static List<String> readProblemOrAnswer(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line, s;
            while ((line = br.readLine()) != null) {
                if (line.contains("’")) {
                    System.out.println("分数中的“'”应为英文的单引号");
                    throw new RuntimeException();
                }
                s = line.substring(line.indexOf('.') + 1);
                list.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println(filePath + "不存在");
        } catch (IOException e) {
            System.out.println("发生异常");
        }
        return list;
    }

    /**
     * 输出题目
     * @param filePath 题目文件路径
     * @param problemList 题目集合
     */
    public static void writeProblem(String filePath, List<List<Expression>> problemList) {
        deleteFile(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(filePath, true);
            int id = 1;
            for (List<Expression> exceptions : problemList) {
                String problem = ExpressionUtil.parseString(exceptions);
                problem = id + "." + problem + "\n";
                fos.write(problem.getBytes());
                id++;
            }
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(filePath + "不存在");
        } catch (IOException e) {
            System.out.println("文件输出异常");
        }
    }

    /**
     * 输出题目答案
     * @param filePath 答案路径
     * @param answerList 答案路径
     */
    public static void writeAnswer(String filePath, List<Expression> answerList) {
        deleteFile(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(filePath, true);
            int id = 1;
            for (Expression answer : answerList) {
                String problem = ExpressionUtil.getString(answer);
                problem = id + "." + problem + "\n";
                fos.write(problem.getBytes());
                id++;
            }
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println(filePath + "不存在");
        } catch (IOException e) {
            System.out.println("文件输出异常");
        }
    }

    /**
     * 输出题目正误统计
     * @param filePath 答案路径
     * @param correctList 正确题号
     * @param wrongList 错误题号
     */
    public static void writeResult(String filePath, List<Integer> correctList, List<Integer> wrongList) {
        StringJoiner sj1 = new StringJoiner("，", "（", "）");
        StringJoiner sj2 = new StringJoiner("，", "（", "）");
        for (Integer id : correctList) {
            sj1.add(id + "");
        }
        for (Integer id : wrongList) {
            sj2.add(id + "");
        }
        String correct = "Correct：" + correctList.size() + sj1 + "\n";
        String wrong = "Wrong：" + wrongList.size() + sj2 + "\n";
        deleteFile(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(filePath, true);
            fos.write(correct.getBytes());
            fos.write(wrong.getBytes());
            fos.close();
            System.out.println("统计结果：");
            System.out.print(correct);
            System.out.print(wrong);
        } catch (FileNotFoundException e) {
            System.out.println(filePath + "不存在");
        } catch (IOException e) {
            System.out.println("文件输出异常");
        }
    }

    /**
     * 删除历史文件
     * @param filePath 文件路径
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            boolean delete = file.delete();
            if (delete) {
                System.out.println("已清除历史文件" + filePath);
            }
        }
    }

}
