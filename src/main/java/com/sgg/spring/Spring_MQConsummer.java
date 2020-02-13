package com.sgg.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * 时间：  2020/2/13
 * 创建者：  Administrator 钟文
 * 描述：
 * 参数：
 * 返回值：
 **/
@Service
public class Spring_MQConsummer {

    @Autowired
    private JmsTemplate jmsTemplate;
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Spring_MQConsummer  sm = (Spring_MQConsummer)ac.getBean("spring_MQConsummer");

        String s = (String) sm.jmsTemplate.receiveAndConvert();
        System.out.println(" *** 消费者消息"+s);
    }
}
