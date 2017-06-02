package sjc.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * @author shengjc
 * ActiveMQ�ķ�����
 */
public class Publisher {
    public static final String url = "tcp://localhost:61616";// ȱʡ�˿ڣ����Ҫ�ģ�����apache-activemq-5.13.3\conf�е�activemq.xml�и��Ķ˿ں�
    ConnectionFactory factory;	// ���ӹ���
    Connection connection;	// ����
    Session session;	// �Ự ���ܻ��߷�����Ϣ���߳�
    MessageProducer producer;	// ��Ϣ������
    Destination[] destinations;	// ��Ϣ��Ŀ�ĵ�
    ComunicateMode comunicateMode = ComunicateMode.pubsub;

    enum ComunicateMode {
        p2p, pubsub
    }

    public Publisher(ComunicateMode mode) throws JMSException {
        this.comunicateMode = mode;
     // ʵ�������ӹ���
        factory = new ActiveMQConnectionFactory(url);// �����urlҲ���Բ�ָ����java���뽫Ĭ�Ͻ��˿ڸ�ֵΪ61616
        connection = factory.createConnection();	// ͨ�����ӹ�����ȡ����
        try {
            connection.start();	// ��������
        } catch (JMSException e) {
            connection.close();	//�쳣����ر�����
            throw e;
        }
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);	// ����Session,��һ�������ǿ�������true,�ڶ�����������Ϣȷ�ϻ�������
        producer = session.createProducer(null); //����������
    }

    //����Ŀ�ĵأ�֧��Topic��Queue���ַ�ʽ
    protected void setDestinations(String[] stocks) throws JMSException {
        destinations = new Destination[stocks.length];
        for (int i = 0; i < stocks.length; i++) {
            destinations[i] = comunicateMode == ComunicateMode.pubsub ? session
                    .createTopic("Topic." + stocks[i]) : session
                    .createQueue("Queue." + stocks[i]);
        }
    }

    //������Ϣ
    protected void sendMessage(String msg) throws JMSException {
        for (Destination item : destinations) {
            TextMessage msgMessage = session.createTextMessage(msg);
            producer.send(item, msgMessage);
            System.out.println(String.format("�ɹ���Topic�顾%s��������Ϣ��%s��",
                    item.toString(), msgMessage.getText()));
        }
    }

    protected void close() throws JMSException {
        if (connection != null)
            connection.close();
    }

    public static void main(String[] args) throws JMSException,
            InterruptedException, IOException {
        Publisher publisher = new Publisher(ComunicateMode.pubsub);// ��������޸���Ϣ���䷽ʽΪpubsub
        publisher.setDestinations(new String[] { "1", "2", "3" });
        BufferedReader reader = null;
        String contentString = "";
        do {
            System.out.println("������Ҫ���͵�����(exit�˳�):");
            reader = new BufferedReader(new InputStreamReader(System.in));
            contentString = reader.readLine();
            if (contentString.equals("exit"))
                break;
            publisher.sendMessage(contentString);
        } while (!contentString.equals("exit"));
        reader.close();
        publisher.close();
    }
}
