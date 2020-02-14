package com.sgg.spring;

import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 时间：  2020/2/13
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：   如果不加注解，就需要在配置文件里面自定义一个bean
 * 返回值：
 **/
@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (null != message  && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            try {
                System.out.println("TextMessage222****消费者的消息："+textMessage.getText());
            }catch (JMSException e) {
                e.printStackTrace();
            }
        }else if (null != message  && message instanceof MapMessage){
            MapMessage mapMessage = (MapMessage)message;
            try {
                System.out.println("MapMessage****消费者的消息："+mapMessage.getString("map"));
            }catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
