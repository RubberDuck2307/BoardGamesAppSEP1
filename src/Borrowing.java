import java.time.LocalDate;

public class Borrowing extends Period
{

  public Borrowing(int ID, int playerID, int gameID, LocalDate from,
      LocalDate to, String comment)
  {
    super(ID, playerID, gameID, from, to, comment);
  }

  public Borrowing(int playerID, int gameID, LocalDate from,
      LocalDate to, String comment)
  {
    super(playerID, gameID, from, to, comment);
  }
}
