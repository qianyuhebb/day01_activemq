package com.sgg.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 时间：  2020/2/12
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
public class JmsConsumer_topic_persistent {
    public static final String ACTIVEMQ_URL = "tcp://192.168.25.128:61616";
    // 1对1 的队列
    public static final String TOPIC_NAME = "topic02";

    public static void main(String[] args) throws Exception{
        System.out.println("我是Z3消费者");
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂连接 connection  和 启动
        Connection connection = activeMQConnectionFactory.createConnection();
        //  启动
        connection.setClientID("z33");

        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用队列）
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber =session.createDurableSubscriber(topic,"remark****");
        connection.start();
        Message message = topicSubscriber.receive();
        System.out.println(message);
       while (message !=null){
           TextMessage textMessage = (TextMessage) message;
           System.out.println("收到了持久化消息："+textMessage.getText());
           message =topicSubscriber.receive(1000L);
       }
        session.close();
        connection.close();


    }


}
