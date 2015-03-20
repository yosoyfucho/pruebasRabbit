import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import java.io.*;

/*
Programa que recibe mensajes de la cola de RabbitMQ
QUEUE_NAME

compilacion

javac -cp "./lib/rabbitmq-client.jar" RecvRabbit.java

ejecucion

java -cp "./lib/commons-io-1.2.jar:./lib/commons-cli-1.1.jar:./lib/rabbitmq-client.jar:." RecvRabbit

Actualmente falla al recibir en bucle, por solucionar


*/

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
