
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.*;



public class LogicaRabbit {

  private final static String QUEUE_NAME = "qRabbitMQ";

  public Connection connection () throws IOException
  {
    Connection conn = null;
    try
    {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      conn = factory.newConnection();
    }
    catch (Exception e)
    {
      System.err.println("Fallo al establecer la conexión\n");
      System.out.print("trace: ");
      e.printStackTrace();
    }
    return conn;
  }

  public Channel canal (Connection conn) throws IOException
  {
    Channel channel = null;
    try
    {
      channel = conn.createChannel();
    }
    catch (Exception e)
    {
      System.err.println("Fallo al establecer la conexión\n");
      System.out.print("trace: ");
      e.printStackTrace();
    }
    return channel;
  }

  void cierreTot (Connection conn, Channel canal) throws IOException
  {
    try
    {
      canal.close();
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Fallo al cerrar la conexión y el canal\n");
      System.out.print("trace: ");
      e.printStackTrace();
    }
  }


  public void SendToRabbit (Channel canal, String msg, Connection conn)
  {
    try
    {
      canal.queueDeclare(QUEUE_NAME, false,false,false,null);
      canal.basicPublish("",QUEUE_NAME,null,msg.getBytes());
      System.out.println("LogicaRabbit=> Mensaje Enviado");
      cierreTot(conn,canal);
      // canal.close();
      //
      // conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Fallo al enviar hacia Rabbit");
      System.out.print("trace: ");
      e.printStackTrace();
    }


  }

  public String RecvFromRabbit(Channel canal, Connection conn)
  {
    String message = null;
    try
    {
      canal.queueDeclare(QUEUE_NAME,false,false,false,null);
      QueueingConsumer consumer = new QueueingConsumer(canal);
      canal.basicConsume(QUEUE_NAME,true,consumer);

      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
      message = new String (delivery.getBody());
      //cierreTot(conn,canal);

    }
    catch (Exception e)
    {
      System.err.println("Fallo al recibir de Rabbit\n");
      System.out.print("trace: ");
      e.printStackTrace();
    }
    return message;
  }


}
