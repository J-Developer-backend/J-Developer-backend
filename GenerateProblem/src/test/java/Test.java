import com.generate.util.ExpressionUtil;
import com.generate.util.RandomUtil;

public class Test {

    @org.junit.Test
    public void test1() {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtil.randInt(2, 2));
        }
    }

    @org.junit.Test
    public void test2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(ExpressionUtil.parseExpression(ExpressionUtil.generateExpression(10)));
        }
    }

}
