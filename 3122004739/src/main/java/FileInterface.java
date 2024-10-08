import java.io.*;


/**
 * 文件读写类
 * @author J
 */
public class FileInterface {
    private final String originFileName;
    private final String copyFileName;
    private final String answerFileName;
    public FileInterface(String originFileName, String copyFileName, String answerFileName) {
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
        fis.close();
        return sb.toString();
    }

    /**
     * 写出重复率
     * @param repetitionRate 重复率
     * @throws IOException 异常
     */
    public void writeAnswerFile(double repetitionRate, boolean mode) throws IOException {
        FileOutputStream fos = getFileOutputStream(answerFileName, mode);
        String answer = String.format("%.2f\n", repetitionRate);
        fos.write(answer.getBytes());
        fos.close();
    }
    private FileOutputStream getFileOutputStream(String fileName, boolean mode) throws IOException {
        return new FileOutputStream(fileName, mode);
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
