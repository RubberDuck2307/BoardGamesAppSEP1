package Model;

import java.util.ArrayList;
import java.util.Objects;

public class BorrowingsList
{
  private ArrayList<Reservation> borrowingList;

  public BorrowingsList()
  {
    borrowingList = new ArrayList<>();
  }

  public BorrowingsList(ArrayList<Reservation> borrowingList)
  {
    this.borrowingList = borrowingList;
  }

  public Reservation getBorrowing(int index)
  {
    return borrowingList.get(index);
  }

  public int size()
  {
    return borrowingList.size();
  }

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

  public Reservation getBorrowingByGameID(int ID){
    for (int i = 0; i < size(); i++)
    {
      if (getBorrowing(i).getGameID() == ID){
        return getBorrowing(i);
      }
    }
    return null;

  }

  @Override public String toString()
  {
    return "Model.BorrowingsList{" + "borrowingList=" + borrowingList + '}';
  }

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
