package sjc.tools;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ActiveMQ�ļ�������Ϣ����
 * @author shengjc
 * Ŀǰδʹ�ã�consumer����ʹ�õ�������ķ�ʽ�����˼�����
 */
public class ListenerMQ implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("�յ�����Ϣ��"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
