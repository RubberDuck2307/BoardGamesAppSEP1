package Model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A class representing list of borrowings using Reservation objects
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 */
public class BorrowingsList
{

  private ArrayList<Reservation> borrowingList;

  /**
   * Zero-argument set borrowingList to new empty ArrayList
   */
  public BorrowingsList()
  {
    borrowingList = new ArrayList<>();
  }

  /**
   *
   * @param index index of the borrowing that is returned
   * @return the borrowing in the given index
   */
  public Reservation getBorrowing(int index)
  {
    return borrowingList.get(index);
  }

  /**
   *
   * @return the size of borrowingList
   */
  public int size()
  {
    return borrowingList.size();
  }

  /**
   *
   * @param id ID of the borrowing that is returned
   * @return borrowing whose ID equals the given one or null if there is no borrowing with such id in the list
   */
  public Reservation getBorrowingByID(int id)
  {
    for (int i = 0; i < size(); i++)
    {
      if (borrowingList.get(i).getID() == id)
      {
        return borrowingList.get(i);
      }
    }
   return null;
  }

  /**
   *
   * @param charSequence string that name of the player who reserve the game or name of the game which is reserved must include
   * @param boardGamesList the boardGamesList from which is the board game name get
   * @param playersList the playersList from which is the player name get
   * @return a new BorrowingsList with only borrowings from the original list whose name of the player who reserve the game or name of the game which is reserved contains the given charSequence string
   */
  public BorrowingsList filterBorrowingList(String charSequence,
      PlayersList playersList, BoardGamesList boardGamesList)
  {
    BorrowingsList newBorrowingList = new BorrowingsList();

    for (int i = 0; i < size(); i++)
    {
      if (playersList.getPlayerByID(borrowingList.get(i).getPlayerID())
          .getName().toLowerCase().contains(charSequence.toLowerCase()) || boardGamesList.getBoardGameByID(
          borrowingList.get(i).getGameID()).getName().toLowerCase().contains(charSequence.toLowerCase()))
      {
        newBorrowingList.addBorrowing(borrowingList.get(i));
      }
    }
    return newBorrowingList;
  }

  /**
   *
   * @param ID The ID of the player who borrows a game
   * @return new BorrowingsList with only borrowings from the original list whose playerID equals the given one
   */
  public BorrowingsList getBorrowingsByPlayer(int ID){
    BorrowingsList newBorrowingList = new BorrowingsList();
    for (int i = 0; i < size(); i++)
    {
      if(getBorrowing(i).getPlayerID() == ID){
        newBorrowingList.addBorrowing(getBorrowing(i));
      }
    }
    return newBorrowingList;
  }

  /**
   *
   * @param ID The ID of the game that is borrowed
   * @return new BorrowingsList with only borrowings from the original list whose gameID equals the given one
   */
  public Reservation getBorrowingByBoardGame(int ID){
    Reservation reservation;
    for (int i = 0; i < size(); i++)
    {
      if(getBorrowing(i).getGameID() == ID){
        reservation = getBorrowing(i);
        return reservation;
      }
    }
    return null;
  }

  /**
   * add a borrowing into borrowingList.
   * If the ID of the borrowing is -1 it is set to the value of the ID of the last borrowing in the list incremented by 1.
   * If the list is empty the ID is set to 0
   */

  public void addBorrowing(Reservation borrowing)
  {
    if (borrowing.getID() == -1)
    {
      if (borrowingList.size() == 0)
      {
        borrowing.setID(0);
        borrowingList.add(borrowing);
      }
      else
      {
        int lastID = borrowingList.get(borrowingList.size() - 1).getID();
        borrowing.setID(lastID + 1);
        borrowingList.add(borrowing);
      }
    }
    else
    {
      borrowingList.add(borrowing);
    }
  }

  /**
   *
   * @param ID of the borrowing which is returned
   * @return first borrowing in the list whose ID equals the given one, or null if there is no borrowing with such ID
   */
  public Reservation getBorrowingByGameID(int ID){
    for (int i = 0; i < size(); i++)
    {
      if (getBorrowing(i).getGameID() == ID){
        return getBorrowing(i);
      }
    }
    return null;

  }

  /**
   *
   * @return values of all attributes of all the borrowings int the list as a string
   */
  @Override public String toString()
  {
    return "Model.BorrowingsList{" + "borrowingList=" + borrowingList + '}';
  }


  /**
   * Removes a borrowing from the list by ID
   * @param ID ID of the borrowing that is removed
   */
  public void deleteByID(int ID)
  {
    for (int i = 0; i < borrowingList.size(); i++)
    {
      if (borrowingList.get(i).getID() == ID)
      {
        borrowingList.remove(i);
        break;
      }
    }
  }

  /**
   *
   * @param ID ID of the game that is borrowed
   * @return new BorrowingsList with only borrowings from the original list which has the same gameID as the given one
   */
  public BorrowingsList getByGameID(int ID)
  {
    BorrowingsList newBorrowingList = new BorrowingsList();
    for (int i = 0; i < size(); i++)
    {
      if (getBorrowing(i).getGameID() == ID)
      {
        newBorrowingList.addBorrowing(getBorrowing(i));
      }
    }
    return newBorrowingList;
  }

  /**
   * Replace a borrowing with particular ID with the given borrowing.
   * If a borrowing with the ID is not found in the list, the given borrowing is added on the end of the list instead.
   * @param borrowing borrowing by which is the current borrowing replaced.
   * @param ID the ID of the borrowing that will is replaced.
   */
  public void setBorrowing(Reservation borrowing, int ID)
  {
    for (int i = 0; i < borrowingList.size(); i++)
    {
      if (borrowingList.get(i).getID() == ID)
      {
        borrowingList.set(i, borrowing);
        break;
      }
      else
      {
        borrowingList.add(borrowing);
      }
    }
  }
}
