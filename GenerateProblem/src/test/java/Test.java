import com.generate.common.Expression;
import com.generate.util.ComputeUtil;
import com.generate.util.ExpressionUtil;
import com.generate.util.RandomUtil;

import java.util.List;
import java.util.Stack;

public class Test {

    @org.junit.Test
    public void test1() {
        int cnt = 0;
        for (int i = 0; i < 10000; i++) {
            List<Expression> expressionList = ExpressionUtil.generateExpression(10);
            Expression result = ComputeUtil.computeExpression(expressionList);
            String expression = ExpressionUtil.parseExpression(expressionList);
            System.out.println(expression + ExpressionUtil.getString(result));
            if (result == null) cnt++;
        }
        System.out.println(cnt);

    }


    @org.junit.Test
    public void test3() {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(6);
        System.out.println(stack.peek());
        stack.add(10);
        stack.push(7);
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        stack.push(8);
        stack.push(9);
        System.out.println(stack.peek());
        System.out.println(stack);

    }

}
