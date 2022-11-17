import java.util.ArrayList;

public class BorrowingsList
{
  private ArrayList<Borrowing> borrowingList;

  public BorrowingsList()
  {
    borrowingList = new ArrayList<>();
  }

  public BorrowingsList(ArrayList<Borrowing> borrowingList)
  {
    this.borrowingList = borrowingList;
  }

  public Borrowing getBorrowing(int index)
  {
    return borrowingList.get(index);
  }

  public int size()
  {
    return borrowingList.size();
  }

  public void addBorrowing(Borrowing borrowing)
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

  @Override public String toString()
  {
    return "BorrowingsList{" + "borrowingList=" + borrowingList + '}';
  }
}
