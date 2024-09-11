import java.io.File;
import java.math.BigInteger;
import java.util.Arrays;

public class TestModule {
    /**
     * 此单元测试仅适用当前项目测试样例中的文件命名方式，即原文件：orig.txt，抄袭文件：xx_xx_xx.txt
     * 答案文件中的重复率数据的顺序与抄袭文件按名称排序相同
     * @throws Exception yc异常
     */
    @org.junit.Test
    public void test() throws Exception {
        File parentFile = new File("D:\\GDUTLearning\\大三上\\软件工程\\作业\\个人项目\\3122004739\\测试文本");
        //选出原文件和抄袭文件
        File[] originFiles = parentFile.listFiles((fileName) -> fileName.getName().contains("orig.txt"));
        File[] copyFiles = parentFile.listFiles((fileName) -> fileName.getName().contains("_"));
        assert originFiles != null;
        assert copyFiles != null;
        Arrays.sort(copyFiles);
        //取出原文件地址
        String originFileName = originFiles[0].getAbsolutePath();
        //设置答案文件地址
        String answerFileName = parentFile.getAbsolutePath() + "\\ans.txt";
        File ansFile =  new File(answerFileName);
        if (ansFile.exists()) {
            if (ansFile.delete()) {
                System.out.println("已清除上次测试答案数据");
            }
        }
        //对每个抄袭文件测试
        for (File copyFileName : copyFiles) {
            FileInterface fileInterface = new FileInterface(originFileName, copyFileName.getAbsolutePath(), answerFileName);
            // 原文件和抄袭文件文本读取
            String originText = fileInterface.readOriginFile();
            String copyText = fileInterface.readCopyFile();

            // 文本预处理
            String originContext = TextParserUtil.clean(originText);
            String copyContext = TextParserUtil.clean(copyText);

            // 生成文本指纹
            BigInteger originSimHash = SimHashUtil.simHash(originContext);
            BigInteger copySimHash = SimHashUtil.simHash(copyContext);

            // 计算海明距离
            int hammingDistance = HammingUtil.hammingDistance(originSimHash, copySimHash);

            // 计算相似度
            double similarity = HammingUtil.getSimilarity(hammingDistance);

            // 将相似度输出
            fileInterface.writeAnswerFile(similarity);
            System.out.println("原文件：" + originFileName);
            System.out.println("抄袭文件：" + copyFileName.getAbsolutePath());
            System.out.println("重复率：" + similarity);
        }
    }

}
