import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.text.ParseException;
import java.io.*;

/*
Programa que envía a la cola de RabbitMQ
o bien cadenas de texto introducidos en la llamada
o un mensaje generico si no hay cadena introducida

Añadimos un flag al principio del mensaje enviado que despues
sera evaluado por el receptor

compilacion

javac -cp "./lib/rabbitmq-client.jar" SenderRabbit.java

ejecucion

java -cp "./lib/commons-io-1.2.jar:./lib/commons-cli-1.1.jar:./lib/rabbitmq-client.jar:." SenderRabbit


*/


public class SenderRabbit
{
  public static void main (String args[]) throws Exception
  {
    LogicaRabbit auxRabbit = new LogicaRabbit();
    Connection connRabbit = auxRabbit.connection();
    Channel canalRabbit = auxRabbit.canal(connRabbit);
    String message = null;


    if (args.length < 1)
    {
      message = "No hay mensaje en la llamada";
      message = "0"+" "+message;
      System.out.println("Enviando mensaje a través de Rabbit : "+ message);
      auxRabbit.SendToRabbit(canalRabbit,message,connRabbit);
    }

    else
    {
      int cont = args.length;
      String auxiliar = null;
      for (int i = 0; i<cont; i++)
      {
        if (i == 0)
          auxiliar = args[i];
        else
          {
              auxiliar = auxiliar + " " + args[i];
          }
      }
      if (auxiliar!=null)
      {
      auxiliar = "1" + " "+ auxiliar;
      System.out.println("Enviando mensaje a través de Rabbit : "+ auxiliar);
      auxRabbit.SendToRabbit(canalRabbit,auxiliar,connRabbit);
      }
      else
        System.out.println("Se ha producido un error al enviar auxiliar");
      }

  }
}
