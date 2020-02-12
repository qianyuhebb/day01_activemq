package com.sgg.controller;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 时间：  2020/2/12
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
public class JmsproducerTest {

        public static  final String ACTIVEMQ_URL="tcp://192.168.25.128:61616";
        public static  final String QUEUE_NAME="queue01";
    public static void main(String[] args) throws JMSException {

        //1.创建按连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.由工厂创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.由连接创建会话（连个参数，一个是事务。一个是签收）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.由会话创建目的地（topic或者是queue）
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.由会话创建消息生产者
        MessageProducer messageProducer = session.createProducer(queue);
        for (int i = 1; i <=6 ; i++) {
            //6.由会话session创建消息
            TextMessage message = session.createTextMessage("msg***"+i);
            //7.由生产者发送消息
            messageProducer.send(message);
        }
         //8.关闭相关资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("mp消息发送到queue完成*******");

    }
}
