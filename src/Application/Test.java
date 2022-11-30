package Application;

import Model.Event;
import Model.EventsList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Test
{
  public static void main(String[] args)
      throws ParserConfigurationException, TransformerException
  {
      LocalDate localDate = LocalDate.now();
      LocalDateTime localDateTime = localDate.atTime(2,23);
      System.out.println(localDate);

    }
}
