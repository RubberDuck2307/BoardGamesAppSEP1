package Model;

import java.time.LocalDate;

/**
 * A class representing a reservation and also a borrowing
 *
 * @author Anna Andrlova, Christos Artemision, Alex Bolfa, Jan Metela
 * @version 1.0 - November 2022
 */
public class Reservation
{
  private int ID;
  private int playerID;
  private int gameID;
  private LocalDate from;
  private LocalDate to;
  private String comment;

  /**
   * Six-arguments constructor calling the set method
   *
   * @param ID       the ID of the reservation/borrowing
   * @param playerID the ID of the player who reserves/borrows the game
   * @param gameID   the ID of the game which is reserved/borrowed
   * @param from     the date since when is the game reserved/borrowed
   * @param to       the date till when is the game reserved/borrowed
   * @param comment  a custom comment
   */
  public Reservation(int ID, int playerID, int gameID, LocalDate from,
      LocalDate to, String comment)
  {
    set(ID, playerID, gameID, from, to, comment);
  }

  /**
   * Five-arguments constructor calling the set method. ID is set to -1.
   *
   * @param playerID the ID of the player who reserves/borrows the game
   * @param gameID   the ID of the game which is reserved/borrowed
   * @param from     the date since when is the game reserved/borrowed
   * @param to       the date till when is the game reserved/borrowed
   * @param comment  a custom comment
   */
  public Reservation(int playerID, int gameID, LocalDate from, LocalDate to,
      String comment)
  {
    int ID = -1;
    set(ID, playerID, gameID, from, to, comment);
  }

  /**
   * Setter for every attribute
   *
   * @param ID       the ID of the reservation/borrowing
   * @param playerID the ID of the player who reserves/borrows the game
   * @param gameID   the ID of the game which is reserved/borrowed
   * @param from     the date since when is the game reserved/borrowed
   * @param to       the date till when is the game reserved/borrowed
   * @param comment  a custom comment
   */
  public void set(int ID, int playerID, int gameID, LocalDate from,
      LocalDate to, String comment)
  {
    this.ID = ID;
    this.playerID = playerID;
    this.gameID = gameID;
    this.from = from;
    this.to = to;
    this.comment = comment;
  }

  /**
   * Checks whether the passed dates are valid, the guest have not already borrowed a game, and the game is not already borrowed or reserved for the inserted period
   * @param reservationsList the list of all reservations of the game
   * @param borrowingsList the list of all borrowings of the game
   * @param start the starting date of the reservation of borrowing
   * @param end the ending date of the reservation or borrowing
   * @param gameID the ID of the game that is reserved borrowed
   * @param playersList the list including record about the player who is reserving/borrowing the game
   * @param playerID the ID of the player
   * @param currentReservationID the ID of the reservation what is edited. If no reservation is edited -1 should be passed.
   * @param currentBorrowingID the ID of the borrowing what is edited. If no reservation is edited -1 should be passed.
   * @throws RuntimeException if the data are invalid
   * @return true if the data are valid
   */
  static public boolean VALIDATE_DATA(ReservationsList reservationsList,
      BorrowingsList borrowingsList, LocalDate start, LocalDate end, int gameID,
      PlayersList playersList, int playerID, int currentReservationID,
      int currentBorrowingID)
  {

    if (!end.isAfter(start))
    {
      throw new RuntimeException("The borrowing must end after it starts");
    }
    if (!playersList.getPlayerByID(playerID).isMembership())
    {
      for (int j = 0; j < borrowingsList.size(); j++)
      {
        if (borrowingsList.getBorrowing(j).getPlayerID() == playerID && borrowingsList.getBorrowing(j).getID() != currentBorrowingID)
        {
          throw new RuntimeException(
              "Guest can not make 2 borrowings in the same time");
        }
      }
    }
    for (int i = 0; i < reservationsList.size(); i++)
    {
      if (reservationsList.getReservation(i).getGameID() == gameID)
      {
        if (currentReservationID != reservationsList.getReservation(i).getID())
        {
          if ((start.isAfter(reservationsList.getReservation(i).from)
              && start.isBefore(reservationsList.getReservation(i).to)) || (
              end.isAfter(reservationsList.getReservation(i).from)
                  && end.isBefore(reservationsList.getReservation(i).to)) || (
              reservationsList.getReservation(i).from.isAfter(start)
                  && reservationsList.getReservation(i).from.isBefore(end)) || (
              reservationsList.getReservation(i).to.isAfter(start)
                  && reservationsList.getReservation(i).to.isBefore(start))
              || reservationsList.getReservation(i).from.equals(start)
              || reservationsList.getReservation(i).to.equals(end))
          {
            throw new RuntimeException(
                "This game is already reserved for this date, please change the date ");
          }
        }
      }
    }
      for (int k = 0; k < borrowingsList.size(); k++)
      {
        if (borrowingsList.getBorrowing(k).getGameID() == gameID)
        {
          if (currentBorrowingID != borrowingsList.getBorrowing(k)
              .getID())
          {
            if ((start.isAfter(borrowingsList.getBorrowing(k).from)
                && start.isBefore(borrowingsList.getBorrowing(k).to)) || (
                end.isAfter(borrowingsList.getBorrowing(k).from)
                    && end.isBefore(borrowingsList.getBorrowing(k).to)) || (
                borrowingsList.getBorrowing(k).from.isAfter(start)
                    && borrowingsList.getBorrowing(k).from.isBefore(end)) || (
                borrowingsList.getBorrowing(k).to.isAfter(start)
                    && borrowingsList.getBorrowing(k).to.isBefore(start))
                || borrowingsList.getBorrowing(k).from.equals(start)
                || borrowingsList.getBorrowing(k).to.equals(end)

            )
            {
              throw new RuntimeException(
                  "This game is currently borrowed for this date, please change the date ");
            }
          }

      }
    }
    return true;
  }

  /**
   * Getter for ID
   */
  public int getID()
  {
    return ID;
  }

  /**
   * Setter for ID
   */
  public void setID(int ID)
  {
    this.ID = ID;
  }

  /**
   * Getter for playerID
   */
  public int getPlayerID()
  {
    return playerID;
  }

  /**
   * Setter for playerID
   */
  public void setPlayerID(int playerID)
  {
    this.playerID = playerID;
  }

  /**
   * Getter for gameID
   */
  public int getGameID()
  {
    return gameID;
  }

  /**
   * Setter for gameID
   */
  public void setGameID(int gameID)
  {
    this.gameID = gameID;
  }

  /**
   * Getter for from
   */
  public LocalDate getFrom()
  {
    return from;
  }

  /**
   * Setter for from
   */
  public void setFrom(LocalDate from)
  {
    this.from = from;
  }

  /**
   * Getter for to
   */
  public LocalDate getTo()
  {
    return to;
  }

  /**
   * Setter for to
   */
  public void setTo(LocalDate to)
  {
    this.to = to;
  }

  /**
   * Getter for comment
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Setter for comment
   */
  public void setComment(String comment)
  {
    this.comment = comment;
  }

  /**
   * Get a copy of reservation/borrowing
   */
  public Reservation copy()
  {
    return new Reservation(getID(), getPlayerID(), getGameID(), getFrom(),
        getTo(), getComment());

  }

  /**
   * @return values of all attributes as string
   */
  @Override public String toString()
  {
    return "Model.Borrowing{" + "ID=" + ID + ", playerID=" + playerID
        + ", gameID=" + gameID + ", from=" + from + ", to=" + to + ", comment='"
        + comment + '\'' + '}';
  }
}
