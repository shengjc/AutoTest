package sjc.tools;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ActiveMQ的监听类消息监听
 * @author shengjc
 * 目前未使用，consumer类中使用的匿名类的方式设置了监听类
 */
public class ListenerMQ implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("收到的消息："+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
