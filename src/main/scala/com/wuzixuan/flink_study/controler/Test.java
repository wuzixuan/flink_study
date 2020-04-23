package com.wuzixuan.flink_study.controler;

import com.wuzixuan.flink_study.utils.NewThreadOfFlink;
import com.wuzixuan.flink_study.utils.SocketTextStreamMaking;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Test {
    public static void main(String[] args) throws InterruptedException, IOException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.execute(new NewThreadOfFlink());
        Thread.sleep(1000);
        SocketTextStreamMaking.sendMessage(20,7777);
    }
}
