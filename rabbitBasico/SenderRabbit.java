//package rabbit;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.text.ParseException;
import java.io.*;

// package rabbit;

public class SenderRabbit
{
  public static void main (String[] argv) throws Exception
  {
    LogicaRabbit aux = new LogicaRabbit();
    Connection conn = aux.connection();
    Channel canal = aux.canal(conn);

    // int cont = argv.length();
    String message = null;
    // if (cont == 0)
    // {
      message = "No hay mensaje en la llamada";
      System.out.println("Enviando mensaje a través de Rabbit");
      aux.SendToRabbit(canal,message,conn);
    // }
    // else
    // {
    //
    //   for (int i = 0; i<argv.length();i++)
    //   {
    //     System.out.println("Enviando mensaje a través de Rabbit");
    //     aux.SendToRabbit(canal,argv[i],conn);
    //   }
    // }
  }
}
