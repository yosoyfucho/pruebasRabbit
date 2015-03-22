import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;


/*
NO HACE USO DE LOGICARABBIT

compilacion

javac -cp "./lib/rabbitmq-client.jar" Recv.java

ejecucion

java -cp "./lib/commons-io-1.2.jar:./lib/commons-cli-1.1.jar:./lib/rabbitmq-client.jar:." Recv


*/

public class Recv2 {

    private final static String QUEUE_NAME = "qRabbitMQ";


    public static void main(String[] argv) throws Exception {

    char aux;

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    QueueingConsumer consumer = new QueueingConsumer(channel);
    channel.basicConsume(QUEUE_NAME, true, consumer);

    while (true) {
      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
      String message = new String(delivery.getBody());
      aux = (char)message.charAt(0);
      switch (aux)
      {
        case '0':
          System.out.println("Mensaje por defecto");
          break;
        case '1':
          System.out.println("Mensaje explicito en la llamada");
          break;
        default:
          System.out.println("Fallo");
          break;
      }
      System.out.println(" [x] Received '" + message + "'");
    }
  }
}
