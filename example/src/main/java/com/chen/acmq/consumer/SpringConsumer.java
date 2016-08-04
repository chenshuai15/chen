package com.chen.acmq.consumer;

import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("consumer")
public class SpringConsumer {

	@Autowired
	private JmsTemplate jmsTemplate;

	// 接收消息
	public void recive() {
		try {
			// 使用JMSTemplate接收消息
			TextMessage txtmsg = (TextMessage) jmsTemplate.receive();
			if (null != txtmsg) {
				System.out.println("--- 收到消息内容为: " + txtmsg.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}