package Application;

import Model.Event;
import Model.EventsList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Test
{
  public static void main(String[] args)
      throws ParserConfigurationException, TransformerException
  {
    LocalDate fromDate = LocalDate.now();
    LocalDate toDate = LocalDate.of(2022, 12, 1);

    Event event = new Event(0, "name ex", "place ex", fromDate, 2, 3, toDate, 4, 5, "",
        new ArrayList<>(), "", ""); EventsList eventsList = new EventsList();
    eventsList.addEvent(event);
    FileReader.saveEventsList(eventsList);

  }
}
