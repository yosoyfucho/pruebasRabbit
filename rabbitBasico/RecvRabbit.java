import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import java.io.*;


public class RecvRabbit {


    public static void main(String[] argv) throws Exception {
      int cont = 0;
      String message = null;
      LogicaRabbit aux = new LogicaRabbit();
      Connection conn = aux.connection();
      Channel canal = aux.canal(conn);
      System.out.println("Esperando a que lleguen mensajes");
    while (true) {

      message = aux.RecvFromRabbit(canal,conn);
      cont++;
      System.out.println("Mensajes :"+ cont);
      System.out.println(" [x] Received '" + message + "'");
      System.out.println("Esperando mas mensajes");
    }
  }
}
