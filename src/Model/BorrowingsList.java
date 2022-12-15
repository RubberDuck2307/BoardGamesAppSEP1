package Model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A class representing the list of borrowings using Reservation objects
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 */
public class BorrowingsList
{

  private ArrayList<Reservation> borrowingList;

  /**
   * The zero-argument sets borrowingList to new empty ArrayList
   */
  public BorrowingsList()
  {
    borrowingList = new ArrayList<>();
  }

  /**
   *
   * @param index the index of the borrowing
   * @return the borrowing with the given index
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
   * @param id the ID of the borrowing
   * @return the borrowing matching with the given ID or null, if there is no borrowing with such ID in the list
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
   * @param charSequence the string containing either the name of the player
   * reserving or the name of the reserved game
   * @param boardGamesList the list containing all the board games
   * @param playersList the list containing all the players
   * @return a new BorrowingsList showing only the borrowings matching with charSequence
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
   * @param ID the ID of the player borrowing a game
   * @return new BorrowingsList showing only the borrowings matching the player's ID
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
   * @param ID The ID of the borrowed game
   * @return new BorrowingsList showing only borrowings matching game's ID
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
   * Adds a borrowing to the borrowingList.
   * If the ID of the borrowing is -1, it is set to the value of the
   * last borrowing's ID in the list, incremented by 1.
   * If the list is empty, the ID is set to 0
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
   * @param ID the ID of the borrowing
   * @return the first borrowing in the list matching the given ID or null,
   * if there is no borrowing with such ID
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
   * @return the values of the attributes of all the borrowings in the list as a string
   */
  @Override public String toString()
  {
    return "Model.BorrowingsList{" + "borrowingList=" + borrowingList + '}';
  }


  /**
   * Removes a borrowing by ID from the list
   * @param ID the ID of the borrowing that is removed
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
   * @param ID the ID of the game that is borrowed
   * @return new BorrowingsList containing only borrowings matching the game's ID
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
   * Replaces a borrowing having a particular ID with the given borrowing.
   * If a borrowing with the ID is not found in the list,
   * the given borrowing is added at the end of the list instead.
   * @param borrowing the borrowing that will replace the current borrowing.
   * @param ID the ID of the borrowing that will be replaced.
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
