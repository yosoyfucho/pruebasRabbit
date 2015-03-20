import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.text.ParseException;
import java.io.*;


public class SenderRabbit
{
  public static void main (String args[]) throws Exception
  {
    LogicaRabbit aux = new LogicaRabbit();
    Connection conn = aux.connection();
    Channel canal = aux.canal(conn);
    String message = null;


    if (args.length < 1)
    {
      message = "No hay mensaje en la llamada";
      System.out.println("Enviando mensaje a través de Rabbit : "+ message);
      aux.SendToRabbit(canal,message,conn);
    }

    else
    {
      int cont = args.length;
      String auxiliar = null;
      for (int i = 0; i<cont; i++)
      {
        if (i == 0)
          auxiliar = args[i] + "_";
        else
          {
            if(i<cont -1)
              auxiliar = auxiliar + " " + args[i] + " ";
            else
              auxiliar = auxiliar + " " + args[i];
          }
      }
      if (auxiliar!=null)
      {
      System.out.println("Enviando mensaje a través de Rabbit : "+ auxiliar);
      aux.SendToRabbit(canal,auxiliar,conn);
      }
      else
        System.out.println("Se ha producido un error al enviar auxiliar");
    }

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
