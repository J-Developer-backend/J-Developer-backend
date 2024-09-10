import java.io.FileInputStream;
import java.io.IOException;

public class FileIO {
    private final String originFileName;
    private final String copyFileName;
    private final String answerFileName;
    public FileIO(String originFileName, String copyFileName, String answerFileName) {
        this.originFileName = originFileName;
        this.copyFileName = copyFileName;
        this.answerFileName = answerFileName;
    }

    /**
     * 文件读取
     * @param fileName 文件名
     * @return 文件内容
     * @throws IOException 读取异常
     */
    private String readFile(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1024];
        int numRead;
        while ((numRead = fis.read(buffer, 0, buffer.length)) != -1) {
            sb.append(new String(buffer, 0, numRead));
        }
        return sb.toString();
    }

    /**
     * 获取源文件
     * @return 文件内容
     * @throws IOException 读取异常
     */
    public String readOriginFile() throws IOException {
        return readFile(originFileName);
    }

    /**
     * 获取源文件
     * @return 文件内容
     * @throws IOException 读取异常
     */
    public String readCopyFile() throws IOException {
        return readFile(copyFileName);
    }
}
