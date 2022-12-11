package Application;

import Model.BoardGame;
import Model.Event;
import Model.EventsList;
import Model.Reservation;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Test
{
  public static void main(String[] args)
      throws ParserConfigurationException, TransformerException
  {
    LocalDate fromDate = LocalDate.now();
    LocalDate toDate = LocalDate.of(2022, 12, 9);
    LocalDate fromDate2 = LocalDate.of(2022, 12, 1);
    Reservation reservation = new Reservation(0,0,fromDate,toDate,"haha");
    Reservation reservation1 = new Reservation(0,0, fromDate2, toDate, "caa");
    ArrayList<Reservation> arrayList = new ArrayList<>();
    arrayList.add(reservation);
    arrayList.add(reservation1);
    arrayList.sort(Comparator.comparing(Reservation::getFrom));



  }
}
