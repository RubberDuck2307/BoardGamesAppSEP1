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

    LocalDate localDate = LocalDate.of(2003,03,24);
    Reservation reservation = new Reservation(2,3,localDate,localDate,"xx",false,2);
    Reservation reservation5 = new Reservation(5,2,3,localDate,localDate,"xx",false,2);
    Reservation reservation6 = new Reservation(2,3,localDate,localDate,"xx",false,2);

    ReservationsList reservationsList = new ReservationsList();
    reservationsList.addReservation(reservation);
    reservationsList.addReservation(reservation5);
    reservationsList.addReservation(reservation6);
    System.out.println(reservationsList);
    FileReader.saveReservationsList(reservationsList);

    ReservationsList reservationsList1 = FileReader.readReservations();
    System.out.println(reservationsList1);
  }
}
