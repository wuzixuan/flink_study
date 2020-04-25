package com.wuzixuan.flink_study.controler

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

object WordCountStart {
  def main(args: Array[String]): Unit = {
    //获取环境
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    //获取socket文本流
    val dataStream: DataStream[String] = environment.socketTextStream("hd01",7777,"\n".head)
    //
    import org.apache.flink.api.scala._
    val wordCount :DataStream[(String, Int)] = dataStream.flatMap(s => s.split(" ")).map(s => (s,1)).keyBy(0).sum(1)

    wordCount.print().setParallelism(2)

    environment.execute("word count")

  }
}
