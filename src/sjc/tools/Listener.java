package sjc.tools;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ��Ϣ����
 * @author xing
 *
 */
public class Listener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("�յ�����Ϣ��"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
