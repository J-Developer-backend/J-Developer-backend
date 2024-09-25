import com.generate.common.Expression;
import com.generate.util.CheckUtil;
import com.generate.util.ComputeUtil;
import com.generate.util.ExpressionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {

    @org.junit.Test
    public void test1() {
        int cnt = 0, a = 0, b = 0;
        HashMap<Expression, List<List<Expression>>> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            List<Expression> expressionList = ExpressionUtil.generateExpression(10);
            Expression result = ComputeUtil.computeExpression(expressionList);
            if (result != null) {
                List<List<Expression>> sameAnswerProblems = map.get(result);
                if (CheckUtil.checkExpression(expressionList, sameAnswerProblems)) {
                    b++;
                    if (sameAnswerProblems == null) {
                        sameAnswerProblems = new ArrayList<>();
                        sameAnswerProblems.add(expressionList);
                        map.put(result, sameAnswerProblems);
                    } else {
                        sameAnswerProblems.add(expressionList);
                    }
                } else {
                    a++;
                }
            } else{
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(a);
        System.out.println(b);
        System.out.println(map.entrySet().size());
    }


    @org.junit.Test
    public void test3() {
        String a = "a1a1";
        String[] split = a.split("1");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
        System.out.println(split.length);
    }

}
