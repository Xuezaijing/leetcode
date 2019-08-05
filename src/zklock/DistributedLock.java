package zklock;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {
    /**
     * 获取锁，如果没有得到就等待
     * @throws Exception
     */
    void acquire() throws Exception;

    /**
     * 获取锁，直到超时
     * @param time
     * @param timeUnit
     * @return
     * @throws Exception
     */
    boolean acquire(long time, TimeUnit timeUnit) throws Exception;

    /**
     * 释放锁
     * @throws Exception
     */
    void release() throws Exception;

}
