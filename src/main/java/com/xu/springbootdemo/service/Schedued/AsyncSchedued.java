package com.xu.springbootdemo.service.Schedued;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableAsync
//@EnableScheduling
//@Component
public class AsyncSchedued {
    private static Logger logger = LoggerFactory.getLogger(AsyncSchedued.class);

    @Async
    @Scheduled(cron = "0/30 * * * * ?")
    public void out() throws InterruptedException {
        logger.info("-------定时任务执行了--out-start-----");
        Thread.sleep(10000);
        logger.info("-------定时任务执行了--out-end-----");
    }

    //异步执行，不需要等待其他线程
    @Async
    @Scheduled(fixedDelay = 1000)
    public void out3(){
        logger.info("-------定时任务执行了--out3---");
    }
}
