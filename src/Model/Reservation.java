package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation
{
  private int ID;
  private int playerID;
  private int gameID;
  private LocalDate from;
  private LocalDate to;
  private String comment;

  public Reservation(int ID, int playerID, int gameID, LocalDate from, LocalDate to,
      String comment)
  {
    set(ID,playerID,gameID,from,to, comment);
  }

  public Reservation(int playerID, int gameID, LocalDate from, LocalDate to,
      String comment)
  {
    int ID = -1;
    set(ID,playerID,gameID,from,to, comment);
  }

  public void set(int ID, int playerID, int gameID, LocalDate from, LocalDate to, String comment){
    this.ID = ID;
    this.playerID = playerID;
    this.gameID = gameID;
    this.from = from;
    this.to = to;
    this.comment = comment;
  }
  static public boolean validateData(LocalDate start, LocalDate end)
  {
    if (!end.isAfter(start)){
      throw new RuntimeException("The borrowing must end after it starts");
    }

    // TODO check if there are no reservation or borrowings for this date for this bg
    return true;
  }

  public int getID()
  {
    return ID;
  }


  public void setID(int ID)
  {
    this.ID = ID;
  }

  public int getPlayerID()
  {
    return playerID;
  }

  public void setPlayerID(int playerID)
  {
    this.playerID = playerID;
  }

  public int getGameID()
  {
    return gameID;
  }

  public void setGameID(int gameID)
  {
    this.gameID = gameID;
  }

  public LocalDate getFrom()
  {
    return from;
  }

  public void setFrom(LocalDate from)
  {
    this.from = from;
  }

  public LocalDate getTo()
  {
    return to;
  }


  public void setTo(LocalDate to)
  {
    this.to = to;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public static boolean VALIDATE_DATA(LocalDate from, LocalDate to){
    if(!from.isAfter(LocalDate.now())){
      throw new RuntimeException("The event has to start in the future");
    }
    if (!to.isAfter(from)){
      throw new RuntimeException("The event has to end after it starts");
    }
    return true;
  }
  @Override public String toString()
  {
    return "Model.Borrowing{" + "ID=" + ID + ", playerID=" + playerID + ", gameID="
        + gameID + ", from=" + from + ", to=" + to + ", comment='" + comment
        + '\'' + '}';
  }
}
