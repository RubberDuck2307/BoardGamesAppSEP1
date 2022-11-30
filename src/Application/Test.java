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
    LocalDateTime startingDate = LocalDateTime.now();
    LocalDateTime endingDate = LocalDateTime.of(2022, 12, 31, 01,02);
    Event event = new Event(0, "name ex", "place", startingDate, endingDate,"", new ArrayList<>(), "", "");
    EventsList eventsList = new EventsList();
    eventsList.addEvent(event);
    FileReader.saveEventsList(eventsList);

    }
}
