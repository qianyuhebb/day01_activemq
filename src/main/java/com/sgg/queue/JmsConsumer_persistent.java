package com.sgg.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 时间：  2020/2/12
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
public class JmsConsumer_persistent {
    public static final String ACTIVEMQ_URL = "tcp://192.168.25.128:61616";
    public static final String QUEUE_NAME = "queue01";   // 1对1 的队列

    public static void main(String[] args) throws Exception{
        System.out.println("我是3号消费者");
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂连接 connection  和 启动
        Connection connection = activeMQConnectionFactory.createConnection();
        //  启动
        connection.start();
        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用队列）
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while(true){
            // 这里是 TextMessage 是因为消息发送者是 TextMessage ， 接受处理的
            // 也应该是这个类型的消息  receiver有一个重载方法，是有时间参数的
            TextMessage message = (TextMessage)messageConsumer.receive();
            if (null != message){
                System.out.println("****persistent消费者的消息："+message.getText());
            }else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();



    }


}
