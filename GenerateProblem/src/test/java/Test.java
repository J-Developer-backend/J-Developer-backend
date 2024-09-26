import com.generate.common.Expression;
import com.generate.util.CheckUtil;
import com.generate.util.ComputeUtil;
import com.generate.util.ExpressionUtil;
import com.generate.util.FileUtil;

import java.util.*;

public class Test {

    @org.junit.Test
    public void test1() {
        String s = "12.djfkl1= d";
        System.out.println(s.indexOf('.'));
        System.out.println(s.substring(s.indexOf('.') + 1, s.indexOf('=') - 1));
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
