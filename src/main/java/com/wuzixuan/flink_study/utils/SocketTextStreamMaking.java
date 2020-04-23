package com.wuzixuan.flink_study.utils;

import java.io.*;
import java.net.*;
import java.util.Random;

public class SocketTextStreamMaking {
    static private ServerSocket serverSocket = null;
    static private Socket socket = null;
    static private boolean isInited = false;
    static private DataOutputStream dataOutputStream = null;
    //创建socket
    static private void initSocet(int port) throws IOException {
        if (!isInited){
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            isInited = true;
        }else {

        }
    }

    //关闭资源
    static private void close() throws IOException {
        dataOutputStream.close();
        socket.close();
        serverSocket.close();

    }

    //获取输出流
    static private DataOutputStream getDataOutputStream(int port) throws IOException {
        initSocet(port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        return dataOutputStream;
    }

    //发送数据
    static public void sendMessage(int messageNumber,int port) throws IOException, InterruptedException {

        byte[] bytes = new byte[2];
        Random random = new Random();
        for (int i = 0; i < messageNumber; i++) {
            //random.nextBytes(bytes);
            dataOutputStream = getDataOutputStream(port);
            dataOutputStream.writeChar(random.nextInt(3)+65);
            dataOutputStream.writeChar(10);
            dataOutputStream.flush();
            System.out.println(i);
            Thread.sleep(1000);

        }
        close();
    }
}
