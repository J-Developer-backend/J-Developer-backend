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
     * @param fileName
     * @return
     * @throws IOException
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
}
