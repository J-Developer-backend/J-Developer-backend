import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        //命令参数获取
        String originFileName = args[0];
        String copyFileName = args[1];
        String answerFileName = args[2];

        //文件读写对象
        FileInterface fileIO = new FileInterface(originFileName, copyFileName, answerFileName);

        try {
            // 原文件和抄袭文件文本读取
            String originText = fileIO.readOriginFile();
            String copyText = fileIO.readCopyFile();

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

            // 将相似度写入答案文件
            fileIO.writeAnswerFile(similarity);

        } catch (IOException e) {
            System.err.println("文件路径错误");
        }


    }
}
