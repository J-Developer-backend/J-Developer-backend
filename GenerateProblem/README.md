| 这个作业属于哪个课程 | <https://edu.cnblogs.com/campus/gdgy/CSGrade22-34>           |
| -------------------- | ------------------------------------------------------------ |
| 这个作业要求在哪里   | <https://edu.cnblogs.com/campus/gdgy/CSGrade22-34/homework/13230> |
| 这个作业的目标       | 结对实现一个自动生成小学四则运算题目的命令行程序项目         |
# 一、项目开发人员以及仓库地址
## 1、开发人员

- 3122004739——黄健

## 2、项目仓库地址

<https://github.com/J-Developer-backend/J-Developer-backend>

该项目位于GenerateProblem文件夹中

# 二、PSP表格记录各模块预估耗时以及实际耗时
| PSP2.1                                        | Personal Software Process Stages               | 预估耗时（分钟） | 实际耗时（分钟） |
| :-------------------------------------------- | :--------------------------------------------- | :--------------: | :--------------: |
| Planning                                      | 计划                                           |        30        |        35        |
| &middot;Estimate                              | &middot;估计这个任务需要多少时间               |        30        |        35        |
| Development                                   | 开发                                           |       930        |                  |
| &middot;Analysis                              | &middot;需求分析（包括学习新技能）             |       120        |                  |
| &middot;Design Spec                           | &middot;生成设计文档                           |        30        |                  |
| &middot;Design Review                         | &middot;设计复审（和同事审核设计方案）         |        30        |                  |
| &middot;CodingStandard                        | &middot;代码规范（为目前的开发制定合适的规范） |        30        |                  |
| &middot;Design                                | &middot;具体设计                               |       300        |                  |
| &middot;Coding                                | &middot;具体编码                               |       300        |                  |
| &middot;Code Review                           | &middot;代码复审                               |        60        |                  |
| &middot;Test                                  | &middot;测试（自我测试，修改代码，提交修改）   |        60        |                  |
| reporting                                     | 报告                                           |        90        |                  |
| &middot;Test Repor                            | &middot;测试报告                               |        30        |                  |
| &middot;Size Measurement                      | &middot;计算工作量                             |        30        |                  |
| &middot;Postmortem & Process Improvement Plan | &middot;事后总结，并提出过程改进计划           |        30        |                  |
| 合计                                          |                                                |       1050       |                  |

# 三、需求分析

## 1、题目

实现一个自动生成小学四则运算题目的命令行程序（也可以用图像界面，具有相似功能）。

## 2、说明

自然数：0, 1, 2, …。

- 真分数：1/2, 1/3, 2/3, 1/4, 1’1/2, …。
- 运算符：+, −, ×, ÷。
- 括号：(, )。
- 等号：=。
- 分隔符：空格（用于四则运算符和等号前后）。
- 算术表达式：e = n | e1 + e2 | e1 − e2 | e1 × e2 | e1 ÷ e2 | (e)，其中e, e1和e2为表达式，n为自然数或真分数。

- 四则运算题目：e = ，其中e为算术表达式。

## 3、需求

**（1）使用 -n 参数控制生成题目的个数**

​		例如使用命令Myapp.exe -n 10，将生成10个题目。

**（2）使用 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围**

​		例如使用命令Myapp.exe -r 10，将生成10以内（不包括10）的四则运算题目。该参数可以设置为1或其他自然数。该参数必须给定，否则程序报错并给出帮助信息。

**（3）生成的题目中计算过程不能产生负数**

​		算术表达式中如果存在形如e1− e2的子表达式，那么e1≥ e2。

**（4）生成的题目中如果存在形如e1÷ e2的子表达式，那么其结果应是真分数。**

**（5）每道题目中出现的运算符个数不超过3个。**

**（6）程序一次运行生成的题目不能重复，即任何两道题目不能通过有限次交换+和×左右的算术表达式变换为同一道题目。**

​		例如，23 + 45 = 和45 + 23 = 是重复的题目，6 × 8 = 和8 × 6 = 也是重复的题目。3+(2+1)和1+2+3这两个题目是重复的，由于+是左结合的，1+2+3等价于(1+2)+3，也就是3+(1+2)，也就是3+(2+1)。但是1+2+3和3+2+1是不重复的两道题，因为1+2+3等价于(1+2)+3，而3+2+1等价于(3+2)+1，它们之间不能通过有限次交换变成同一个题目。**生成的题目存入执行程序的当前目录下的Exercises.txt文件**，格式如下：

​		1.四则运算题目1

​		2.四则运算题目2

​			……

其中真分数在输入输出时采用如下格式，真分数五分之三表示为3/5，真分数二又八分之三表示为2‘3/8。

**（7）在生成题目的同时，计算出所有题目的答案，并存入执行程序的当前目录下的Answers.txt文件**，格式如下：

​		1.答案1

​		2.答案2

特别的，真分数的运算如下例所示：1/6 + 1/8 = 7/24。

**（8）程序应能支持一万道题目的生成**。

**（9）程序支持对给定的题目文件和答案文件，判定答案中的对错并进行数量统计**

​		输入参数：Myapp.exe -e <exercisefile>.txt -a <answerfile>.txt，将统计结果输出到文件Grade.txt，格式如下：

​		Correct: 5 (1, 3, 5, 7, 9)

​		Wrong: 5 (2, 4, 6, 8, 10)

​		其中“:”后面的数字5表示对/错的题目的数量，括号内的是对/错题目的编号。为简单起见，假设输入的题目都是按照顺序编号的符合规范的题目。

## 4、分析

- 参数控制
  - -n：控制题目数量
  - -r：控制题目数值范围
  - -e：题目文件
  - -a：答案文件
- 题目随机生成
  - 计算过程不能出现负数、非真分数
  - 运算符不超过3个
  - 题目应该符合运算法则
- 题目不能重复
  - 任何两道题目不可通过有限次加法交换律或乘法交换律变换成同一道题目
- 题目以及答案保存
  - 将合法的题目及其答案按规范保存到对应的文件中
- 题目计算
  - 计算四则运算表达式的结果
- 读取题目以及答案
  - 由文件中获取题目信息以及答案
- 数量统计
  - 统计题目的对与错

# 四、能效分析



# 五、设计实现过程

## 1、四则运算表达式

- 将四则运算题目的操作数和运算符单独视为一个对象，设计Expression类封装操作数或运算符，四则运算题目就是Expression对象的集合。

## 2、参数控制

- 设计Configuration类对输入的参数进行封装，利用Configuration对象完成参数控制。
- 设计parseConfiguration(String paramStr)方法解析输入的参数并封装成Configuration对象。

## 3、题目生成

设计ExpressionUtil工具类提供题目生成所需的所有方法，有如下功能：

- 运算数的生成
  - 设计getValue(int limitSize)方法获取自然数，getFraction(int limitSize)方法获取真分数。两个方法都需要指定数值的限制范围，使用产生随机数的方法进行生成。
- 运算符的生成
  - 设计getSign(String sign)方法获取运算符，包括+, −, ×, ÷、=、(、)。
- 题目的生成
  - 设计generateExpression(int limitSize)方法生成题目，随机确定运算数的个数，根据运算数个数确定是否产生括号，若可以产生，随机确定其位置。完成上面的配置后，通过调用getValue(int limitSize)、getFraction(int limitSize)、getSign(String sign)方法，从左往右生成四则运算题目。

## 4、题目校验

设计CheckUtil工具类提供方法实现新生成的题目与已生成的题目进行判重，有如下功能：

- 判重
  - 设计checkTowProblems(List<Expression> problem1, List<Expression> problem2)方法实现两个题目的判重。
  - 设计checkExpression(List<List<Expression>> problem)方法实现新生成的题目与已生成的答案相同的题目进行判重。

## 5、题目计算

设计ComputeUtil工具类提供实现计算功能的方法，有如下功能：

- 支撑计算
  - 设计gcd(int a, int b)方法获取最大公约数，用于约分。
  - 设计lcm(int a, int b)方法获取最小公倍数，用于同分。
- 后缀表达式计算
  - 设计getExpressionsRPN(List<Expression> expressions)获取题目的后缀表达式，避免由于括号造成的繁琐计算。
  - 设计computeRPN(List<Expression> expressions)计算题目的后缀表达式，最终得到题目的结果。
  - 设计computeExpression(List<Expression> expressions)方法条用以上方法实现四则运算表达式的计算。
- 四则运算
  - 加法：设计plus(Expression ex1, Expression ex2)方法实现。
  - 减法：设计minus(Expression ex1, Expression ex2)方法实现。
  - 乘法：设计multiply(Expression ex1, Expression ex2)方法实现。
  - 除法：设计divide(Expression ex1, Expression ex2)方法实现。
- 分数化简
  - 设计simplyFraction(int numerator, int denominator)方法将分数numerator/denominator化简。

## 6、题目与字符串的转换

在ExpressionUtil工具类还提供了表达式集合与字符串的转换方法，有如下功能：

- 题目转字符串
  
  - 设计getString(Expression expression)方法实现单个表达式转换为字符串。
  
  - 设计parseString(List<Expression> expressionList)方法实现表达式集合转换为字符串。
  
- 字符串转表达式集合
  - 自然数：设计parseValue(String valueStr)方法将自然数字符串转自然数表达式。
  - 分数：设计parseFraction(String fractionStr)方法将分数字符串转分数表达式。
  - 运算符：直接判别运算符，并调用getSign(String sign)方法。
  - 单个表达式：设计parseOneExpression(String valueStr)方法通过不同条件调用parseValue(String valueStr)、parseFraction(String fractionStr)、getSign(String sign)返回单个表达式对象。
  - 题目：设计parseExpression(String expressionStr)方法将题目字符串转为表达式集合。

## 7、文件读写

设计FileUtil工具类完成文件的读写操作，有如下功能：

- 读：设计readProblemOrAnswer(String filePath)方法读取题目或者答案。
- 写：
  - 设计writeProblem(String filePath, List<List<Expression>> problemList)方法输出题目
  - 设计writeAnswer(String filePath, List<Expression> answerList)方法输出答案
  - 设计writeResult(String filePath, List<Integer> correctList, List<Integer> wrongList)方法输出题目与答案的正误统计结果。
- 删：设计deleteFile(String filePath)方法删除历史文件

# 六、代码说明

- 题目生成

  - 思路：

    - 直接确定运算数的个数，随后运算符的个数也就确定了，因为每个运算数的后面必然有一个运算符，最后确定是否要加括号以及其位置。因为四则运算题目的运算符不允许超过3个，也就是运算数不能超过4个，所以可以随机产生2到4个的运算数，再随机确定括号，最后从左往右生成题目。

  - 代码实现

      ```java
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
      ```

- 题目计算

  - 思路：
    - 由于部分题目存在括号，可以考虑将题目转化为后缀表达式进行计算。
    
  - 代码实现

    ```java
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
    ```

- 题目校验

  - 思路：

    - 首先考虑到重复的题目答案必然相同，并且运算符、运算数之和都相同，因此对于每个新生成的题目，都只需与具有相同答案的题目进行判重即可，对于判重的方法，秉持宁判错不放过的思想，只有满足答案、运算符、运算数之和相同的两个题目都认定为重复的题目。虽然影响程序性能，但可以确保程序正确性。

  - 代码实现：

    ```java
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
    ```

# 七、测试运行

## 1、题目生成测试

- 参数 ```java -jar .\main.jar -n 10 -r 10```

- 结果

  ![image-20240927211324263](C:\Users\J\AppData\Roaming\Typora\typora-user-images\image-20240927211324263.png)

## 2、题目正误测试

- 参数

  ![image-20240927211610384](C:\Users\J\AppData\Roaming\Typora\typora-user-images\image-20240927211610384.png)

- 结果

  ![image-20240927211639718](C:\Users\J\AppData\Roaming\Typora\typora-user-images\image-20240927211639718.png)

# 八、项目小结

