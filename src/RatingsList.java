import java.util.ArrayList;

public class RatingsList
{
  private ArrayList<Rating> ratingList;

  public RatingsList()
  {
    ratingList = new ArrayList<>();
  }

  public RatingsList(ArrayList<Rating> ratingList)
  {
    this.ratingList = ratingList;
  }

  public Rating getRating(int index)
  {
    return ratingList.get(index);
  }

  public int size()
  {
    return ratingList.size();
  }

  public void addRating(Rating rating)
  {
    if (rating.getID() == -1)
    {
      if (ratingList.size() == 0)
      {
        rating.setID(0);
        ratingList.add(rating);
      }
      else
      {
        int lastID = ratingList.get(ratingList.size() - 1).getID();
        rating.setID(lastID + 1);
        ratingList.add(rating);
      }
    }
    else
    {
      ratingList.add(rating);
    }
  }

  @Override public String toString()
  {
    return "RatingsList{" + "ratingList=" + ratingList + '}';
  }
}
