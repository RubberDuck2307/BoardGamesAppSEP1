package Model;

import java.util.ArrayList;

/**
 * A class representing the list of Rating objects
 */
public class RatingsList
{
  private ArrayList<Rating> ratingList;

  /**
   * The zero-argument constructor sets ratingList to new empty ArrayList
   */
  public RatingsList()
  {
    ratingList = new ArrayList<>();
  }

  /**
   * @param index the index of the rating
   * @return the rating with the given index
   */
  public Rating getRating(int index)
  {
    return ratingList.get(index);
  }

  /**
   * @return the size of ratingList
   */

  public int size()
  {
    return ratingList.size();
  }

  /**
   * Adds the given rating to the List
   * If the ID of the rating is -1, it is set to the value of the
   * last rating's ID in the list, incremented by 1.
   * If the list is empty, the ID is set to 0
   */
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

  /**
   * @param ID the ID of the rated game
   * @return a new Rating list showing only ratings matching with the given ID
   */
  public RatingsList getRatingByBoardGame(int ID)
  {
    RatingsList newRatingsList = new RatingsList();
    for (int i = 0; i < size(); i++)
    {
      if (ID == getRating(i).getGameID())
      {
        newRatingsList.addRating(getRating(i));
      }
    }
    return newRatingsList;
  }

  /**
   * @param ID the ID of the player rating a game
   * @return a new Rating list showing only ratings matching with the given ID
   */
  public RatingsList getRatingsByPlayer(int ID)
  {
    RatingsList newRatingsList = new RatingsList();
    for (int i = 0; i < size(); i++)
    {
      if (ID == getRating(i).getPlayerID())
      {
        newRatingsList.addRating(getRating(i));
      }
    }
    return newRatingsList;
  }

  /**
   * @return the values of the attributes of all the ratings in the list as string
   */
  @Override public String toString()
  {
    return "Model.RatingsList{" + "ratingList=" + ratingList + '}';
  }

  /**
   * Removes a rating by given ID from the list
   *
   * @param ID the ID of the player that is removed
   */
  public void deleteByID(int ID)
  {
    for (int i = 0; i < size(); i++)
    {
      if (getRating(i).getID() == ID)
      {
        ratingList.remove(i);
      }
    }
  }

  /**
   * Removes the rating from the list at the given index
   *
   * @param index the index of the removed rating
   */
  public void delete(int index)
  {
    ratingList.remove(index);
  }
}
