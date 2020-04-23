package com.wuzixuan.flink_study.utils;

import com.wuzixuan.flink_study.action.WordCount;

public class NewThreadOfFlink implements Runnable {
    public void run() {
        WordCount count = new WordCount();
        count.streamWordCount("localhost",7777);
    }
}
