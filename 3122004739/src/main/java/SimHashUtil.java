import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成文本指纹类
 * @author J
 */
public class SimHashUtil {
    /**
     * 文本指纹hash的位数
     */
    public static final int HASH_BITS = 64;
    private static final int MIN_WORD_LENGTH = 3;
    private static final String HASH_LMT = "-1";

    /**
     * 生成文本指纹
     * @param context 文本
     * @return 文本指纹
     */
    public static BigInteger simHash(String context) {
        int[] vector = new int[HASH_BITS];
        // 对字符串进行分词
        List<Term> termList = StandardTokenizer.segment(context);
        //词性的权重
        Map<String, Integer> weightOfNature = new HashMap<>(16);
        //给名词的权重是2
        weightOfNature.put("n", 2);
        // 停用的词性
        Map<String, String> stopNatures = new HashMap<>(16);
        //停用标点符号
        stopNatures.put("w", "");
        //设定超频词汇的界限
        int overCount = 50;
        Map<String, Integer> wordCount = new HashMap<>(16);
        for (Term term : termList) {
            //分词字符串
            String word = term.word;
            // 分词属性
            String nature = term.nature.toString();
            if (wordCount.containsKey(word)) {
                int count = wordCount.get(word);
                //超频词过滤
                if (count > overCount) {
                    continue;
                }
                wordCount.put(word, count + 1);
            } else {
                wordCount.put(word, 1);
            }
            // 过滤停用词性
            if (stopNatures.containsKey(nature)) {
                continue;
            }
            //将每一个分词hash为一组固定长度的数列
            BigInteger t = hash(word);
            for (int i = 0; i < HASH_BITS; i++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(i);
                //添加权重
                int weight = 1;
                if (weightOfNature.containsKey(nature)) {
                    weight = weightOfNature.get(nature);
                }
                if (t.and(bitmask).signum() != 0) {
                    // 这里是计算整个文档的所有特征的向量和
                    vector[i] += weight;
                } else {
                    vector[i] -= weight;
                }
            }
        }
        BigInteger simHash = new BigInteger("0");
        for (int i = 0; i < HASH_BITS; i++) {
            if (vector[i] >= 0) {
                simHash = simHash.add(new BigInteger("1").shiftLeft(i));
            }
        }
        return simHash;
    }
    /**
     * 处理单个分词的hash
     * @param word 分词
     * @return 分词的hash
     */
    private static BigInteger hash(String word) {
        if (word == null || word.isEmpty()) {
            return new BigInteger("0");
        } else {
            //word 的长度过短，会导致hash算法失效，因此需要对过短的词补偿
            StringBuilder wordBuilder = new StringBuilder(word);
            while (wordBuilder.length() < MIN_WORD_LENGTH) {
                wordBuilder.append(wordBuilder.charAt(0));
            }
            word = wordBuilder.toString();
            return getWordHash(word);
        }
    }

    /**
     * 对单个的分词进行hash计算;
     * @param word 分词
     * @return 分词的hash
     */
    private static BigInteger getWordHash(String word) {
        char[] wordCharArray = word.toCharArray();
        BigInteger x = BigInteger.valueOf(((long) wordCharArray[0]) << 7);
        BigInteger m = new BigInteger("1000003");
        BigInteger mask = new BigInteger("2").pow(HASH_BITS).subtract(new BigInteger("1"));
        for (char item : wordCharArray) {
            BigInteger temp = BigInteger.valueOf(item);
            x = x.multiply(m).xor(temp).and(mask);
        }
        x = x.xor(new BigInteger(String.valueOf(word.length())));
        if (x.equals(new BigInteger(HASH_LMT))) {
            x = new BigInteger("-2");
        }
        return x;
    }

}

