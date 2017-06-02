package sjc.tools;

import java.io.IOException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * @author shengjc
 * ActiveMQ的订阅者
 */
public class Consumer {
    public static final String url = "tcp://172.16.0.17:61616";// 缺省端口，如果要改，可在apache-activemq\conf中的activemq.xml中更改端口号
    ConnectionFactory factory;
    Connection connection;
    Session session;
    MessageConsumer[] consumers;
    ComunicateMode comunicateMode = ComunicateMode.pubsub;

    enum ComunicateMode {
        p2p, pubsub
    }

    public Consumer(ComunicateMode mode, String[] destinationNames)
            throws JMSException {
        this.comunicateMode = mode;
        factory = new ActiveMQConnectionFactory(url);// 这里的url也可以不指定，java代码将默认将端口赋值为61616
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        consumers = new MessageConsumer[destinationNames.length];
        for (int i = 0; i < destinationNames.length; i++) {
            Destination destination = comunicateMode == ComunicateMode.pubsub ? session
                    .createTopic( destinationNames[i]) : session
                    .createQueue( destinationNames[i]);                    
            consumers[i] = session.createConsumer(destination);
            consumers[i].setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println(String.format("收到消息【%s】",
                                ((TextMessage) message).getText()));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void close() throws JMSException {
        if (connection != null)
            connection.close();
    }

    public static void main(String[] args) throws JMSException, IOException {
        Consumer consumer = new Consumer(ComunicateMode.pubsub,
                new String[] {"winsion.kingkong.monitor.topic"});// 这里可以修改消息传输方式为pubsub
        System.in.read();
        consumer.close();
    }

}
