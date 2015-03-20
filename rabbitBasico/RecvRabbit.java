//package rabbit;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import java.io.*;

// package rabbit;

public class RecvRabbit {


    public static void main(String[] argv) throws Exception {
      String message = null;
      LogicaRabbit aux = new LogicaRabbit();
      Connection conn = aux.connection();
      Channel canal = aux.canal(conn);

    while (true) {
      message = aux.RecvFromRabbit(canal,conn);
      System.out.println(" [x] Received '" + message + "'");
    }
  }
}