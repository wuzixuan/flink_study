package com.wuzixuan.flink_study.action

//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

class WordCount {
  def streamWordCount(host:String,port:Int): Unit ={
    //创建环境
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    //接受文本流

    val textDstream: DataStream[String]  = environment.socketTextStream(host,port,"\n".charAt(0))

    //导入map和flatmap需要的隐式转换
    import org.apache.flink.api.scala._
    val dataStream: DataStream[(String, Int)] = textDstream.flatMap(_.split(" ")).map((_,1)).keyBy(0).sum(1)

    dataStream.print().setParallelism(1)

    //启动任务
    print("启动任务")
    environment.execute("test of stream wordcount")
  }
}
