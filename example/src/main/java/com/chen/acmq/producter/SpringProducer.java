package com.chen.acmq.producter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("producter")
public class SpringProducer {

	// Spring的模板，封装了很多功能
	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(final String msg) {
		// 使用JMSTemplate可以很简单的实现发送消息
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}