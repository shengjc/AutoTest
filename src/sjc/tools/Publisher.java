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
 * ActiveMQ的发布者
 */
public class Publisher {
    public static final String url = "tcp://localhost:61616";// 缺省端口，如果要改，可在apache-activemq-5.13.3\conf中的activemq.xml中更改端口号
    ConnectionFactory factory;	// 连接工厂
    Connection connection;	// 连接
    Session session;	// 会话 接受或者发送消息的线程
    MessageProducer producer;	// 消息生产者
    Destination[] destinations;	// 消息的目的地
    ComunicateMode comunicateMode = ComunicateMode.pubsub;

    enum ComunicateMode {
        p2p, pubsub
    }

    public Publisher(ComunicateMode mode) throws JMSException {
        this.comunicateMode = mode;
     // 实例化连接工厂
        factory = new ActiveMQConnectionFactory(url);// 这里的url也可以不指定，java代码将默认将端口赋值为61616
        connection = factory.createConnection();	// 通过连接工厂获取连接
        try {
            connection.start();	// 启动连接
        } catch (JMSException e) {
            connection.close();	//异常情况关闭连接
            throw e;
        }
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);	// 创建Session,第一个参数是开启事务true,第二个参数是消息确认机制属性
        producer = session.createProducer(null); //创建生产者
    }

    //设置目的地，支持Topic和Queue两种方式
    protected void setDestinations(String[] stocks) throws JMSException {
        destinations = new Destination[stocks.length];
        for (int i = 0; i < stocks.length; i++) {
            destinations[i] = comunicateMode == ComunicateMode.pubsub ? session
                    .createTopic("Topic." + stocks[i]) : session
                    .createQueue("Queue." + stocks[i]);
        }
    }

    //发送消息
    protected void sendMessage(String msg) throws JMSException {
        for (Destination item : destinations) {
            TextMessage msgMessage = session.createTextMessage(msg);
            producer.send(item, msgMessage);
            System.out.println(String.format("成功向Topic為【%s】发送消息【%s】",
                    item.toString(), msgMessage.getText()));
        }
    }

    protected void close() throws JMSException {
        if (connection != null)
            connection.close();
    }

    public static void main(String[] args) throws JMSException,
            InterruptedException, IOException {
        Publisher publisher = new Publisher(ComunicateMode.pubsub);// 这里可以修改消息传输方式为pubsub
        publisher.setDestinations(new String[] { "1", "2", "3" });
        BufferedReader reader = null;
        String contentString = "";
        do {
            System.out.println("请输入要发送的内容(exit退出):");
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
