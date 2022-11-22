import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main
{
  public static void main(String[] args)
      throws ParserConfigurationException, TransformerException, IOException,
      SAXException
  {
    EventsList eventsList = new EventsList();
    FileReader.saveEventsList(eventsList);
  }
}
