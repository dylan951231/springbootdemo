package com.xu.springbootdemo.service.Schedued;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

public class MySchedualed {
    private static Logger logger = LoggerFactory.getLogger(MySchedualed.class);
    /**
     * cron  *   *   *   *   *   *
     *       秒  分  时  日  月  周   (不指定周时用？替代)
     *       0/15 *  *   *   *   ？  从0开始每15秒执行一次
     *       0   0   00  *   *   ?   每天0点执行
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void out() throws InterruptedException {
        logger.info("-------定时任务执行了--out-start-----");
        Thread.sleep(10000);
        logger.info("-------定时任务执行了--out-end-----");
    }

    /**
     * initDelay默认 启动后延迟1s执行
     * 之后每隔五秒执行
     */
    @Scheduled(initialDelay = 1000,fixedDelay = 50000)
    public void out2(){
        logger.info("-------定时任务执行了--out2---");
    }

}
