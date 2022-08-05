package cc.niushuai.project.shuaipush.common.cache;

import lombok.Data;

/**
 * 缓存对象
 *
 * @author niushuai
 * @date 2022/8/5 15:38
 */
@Data
public class CacheObject<V> {

    /**
     * 缓存内容
     */
    private V value;

    /**
     * 有效期 毫秒 0表示不过期
     */
    private long expireIn;

    /**
     * 创建时间 默认实例化当前对象的时间
     */
    private long createTime = System.currentTimeMillis();

    public CacheObject(V value) {
        this.value = value;
        this.expireIn = 3000;
    }

    public CacheObject(V value, long expireIn) {
        this.value = value;
        this.expireIn = expireIn;
    }

    public boolean isExpire() {
        return System.currentTimeMillis() - (this.getCreateTime() + this.getExpireIn()) > 0;
    }
}
