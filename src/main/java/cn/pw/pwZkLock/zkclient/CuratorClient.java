package cn.pw.pwZkLock.zkclient;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CuratorClient {


    /**
     * 获得锁
     */
    public void acquire(InterProcessMutex mutex){
        try {
            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解锁
     */
    public void release(InterProcessMutex mutex){
        try {
            mutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
