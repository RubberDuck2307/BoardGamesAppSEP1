import java.time.LocalDate;

public class Reservation extends Borrowing
{


  public Reservation(int ID, int playerID, int gameID, LocalDate from,
      LocalDate to, String comment)
  {
    super(ID, playerID, gameID, from, to, comment);
  }

  public Reservation(int playerID, int gameID, LocalDate from, LocalDate to,
      String comment)
  {
    super(playerID, gameID, from, to, comment);
  }


}
