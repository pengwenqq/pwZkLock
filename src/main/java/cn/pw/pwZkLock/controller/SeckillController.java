package cn.pw.pwZkLock.controller;

import cn.pw.pwZkLock.zkclient.CuratorClient;
import lombok.extern.log4j.Log4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/pwZkLock")
@Log4j
public class SeckillController {
    @Autowired
    CuratorFramework curatorFramework;
    @Autowired
    CuratorClient curatorClient;

    /**
     * 模拟秒杀的并发场景
     * @param userID 抢库存的用户ID
     * @return true/false
     * @desc
     * 步骤：
     *      1.zk加分布式锁
     *      2.Redis获取库存
     *      3.判断库存是否小于0
     *      4.小于0，返回“商品已售完”,
     *      5.大于0，Redis库存减1，入数据库,返回"恭喜抢到商品",
     *      6.zk解分布式锁
     */
    @RequestMapping(value = "seckKill",method = RequestMethod.POST)
    public String seckKill(String userID){
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, "/curatorLock");
        try {
            //1.加锁
            curatorClient.acquire(mutex);
            //2.Redis获取库存
            //3.判断库存是否小于0
            //4.入数据库
            //5.返回成功
            return "获取库存成功";
        }
        finally {
            //6.解锁
            curatorClient.acquire(mutex);
        }

    }
}
