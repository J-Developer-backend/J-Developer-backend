/**
 * 文本预处理类
 * @author J
 */
public class TextParserUtil {

    /**
     * 文本预处理
     * @param text 文本
     * @return 处理后的文本
     */
    public static String clean(String text) {
        String punctuationRegex = "[，。/；’【】、·！《》？：“”{}|@#￥%…^&*+—.?!~`\"';:\n\r\t]+";
        text = text.replaceAll(punctuationRegex, "");
        text = text.replaceAll(" ", "");
        return text;
    }

}
