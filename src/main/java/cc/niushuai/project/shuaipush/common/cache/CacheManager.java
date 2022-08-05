package cc.niushuai.project.shuaipush.common.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存缓存 线程安全问题不知道存不存在
 *
 * @author niushuai
 * @date 2022/8/5 15:28
 */
public class CacheManager<K, V> {

    /**
     * 缓存实例
     */
    private final ConcurrentHashMap<K, CacheObject<V>> caches = new ConcurrentHashMap<>();

    /**
     * 默认管理器对象
     */
    private static final CacheManager<String, Object> DEFAULT = new CacheManager<>();

    public CacheManager() {
        new ClearThread("CacheManagerClearThread").start();
    }

    public CacheManager(String threadName) {
        new ClearThread(threadName).start();
    }

    /**
     * 额外提供的管理器
     */
    public static <K, V> CacheManager<K, V> newInstance() {
        return new CacheManager<>();
    }

    /**
     * 额外提供的管理器
     */
    public static <K, V> CacheManager<K, V> newInstance(String clearThreadName) {
        return new CacheManager<>(clearThreadName);
    }

    /**
     * 自带一个管理器 默认值
     */
    public static CacheManager<String, Object> getDefault() {
        return DEFAULT;
    }

    /**
     * 存储元素
     */
    public CacheObject<V> put(K key, V value) {
        return put(key, value, 0);
    }

    /**
     * 存储元素
     */
    public CacheObject<V> put(K key, V value, long expireIn) {
        CacheObject<V> obj = new CacheObject<>(value, expireIn);

        this.caches.put(key, obj);

        return obj;
    }

    /**
     * 获取元素
     */
    public V get(K key) {
        CacheObject<V> vCacheObject = this.caches.get(key);
        return null == vCacheObject || vCacheObject.isExpire() ? null : vCacheObject.getValue();
    }

    /**
     * 移除元素
     */
    public V remove(K key) {
        V v = get(key);

        this.caches.remove(key);

        return v;
    }

    /**
     * 重设过期时间
     */
    public void expireIn(K key, long expireIn) {
        this.put(key, get(key), expireIn);
    }

    /**
     * 清空元素
     */
    public int clear() {
        int size = this.caches.size();
        this.caches.clear();
        return size;
    }

    private class ClearThread extends Thread {
        public ClearThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    List<K> expireKeys = new ArrayList<>();
                    Set<Map.Entry<K, CacheObject<V>>> entries = caches.entrySet();

                    // 遍历时发现过期key时记录, 后续再移除
                    entries.forEach(item -> {
                        CacheObject<V> value = item.getValue();
                        if (value.isExpire()) {
                            expireKeys.add(item.getKey());
                        }
                    });

                    // 移除key
                    expireKeys.forEach(key -> caches.remove(key));

                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
