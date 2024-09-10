import java.math.BigInteger;

/**
 * 计算海明距离以及相似度
 * @author J
 */
public class HammingUtil {
    /**
     * 计算海明距离,海明距离越小说明越相似，等于0时证明完全相似
     * @param simHash1 文本1的文本指纹
     * @param simHash2 文本2的文本指纹
     * @return 海明距离
     */
    public static int hammingDistance(BigInteger simHash1, BigInteger simHash2) {
        BigInteger m = new BigInteger("1")
                .shiftLeft(SimHashUtil.HASH_BITS)
                .subtract(new BigInteger("1"));
        BigInteger x = simHash1.xor(simHash2).and(m);
        int distance = 0;
        while (x.signum() != 0) {
            distance += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return distance;
    }

    /**
     * 根据海明距离返回相似度
     * @param distance 海明距离
     * @return  相似度
     **/
    public static double getSimilarity(int distance) {
        return 1 - (double) distance / SimHashUtil.HASH_BITS;
    }
}
