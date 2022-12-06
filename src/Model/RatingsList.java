package Model;

import java.util.ArrayList;

/**
 * A class representing list of Rating objects
 */
public class RatingsList
{
  private ArrayList<Rating> ratingList;
  /**
   * Zero-argument constructor. Set ratingList to new empty ArrayList
   */
  public RatingsList()
  {
    ratingList = new ArrayList<>();
  }

  /**
   *
   * @param index index of the rating
   * @return the rating on the given index
   */
  public Rating getRating(int index)
  {
    return ratingList.get(index);
  }

  /**
   *
   * @return the size of ratingList
   */

  public int size()
  {
    return ratingList.size();
  }


  /**
   * Add the given rating to the List
   * If the ID of the rating is -1, it is set to the value of the ID of the last rating in the list incremented by 1.
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
   *
   * @param ID ID of the game which is rated
   * @return new Rating list with only ratings from the original list whose gameID equals the given ID
   */
  public RatingsList getRatingByBoardGame(int ID){
   RatingsList newRatingsList = new RatingsList();
    for (int i = 0; i < size(); i++)
    {
      if(ID == getRating(i).getGameID()){
        newRatingsList.addRating(getRating(i));
      }
    }
    return newRatingsList;
  }

  /**
   *
   * @param ID ID of the player who rated a game
   * @return new Rating list with only ratings from the original list whose playerID equals the given ID
   */
  public RatingsList getRatingsByPlayer(int ID){
    RatingsList newRatingsList = new RatingsList();
    for (int i = 0; i < size(); i++)
    {
      if(ID == getRating(i).getPlayerID()){
        newRatingsList.addRating(getRating(i));
      }
    }
    return newRatingsList;
  }

  /**
   *
   * @return values of all attributes of all ratings in the list as string
   */
  @Override public String toString()
  {
    return "Model.RatingsList{" + "ratingList=" + ratingList + '}';
  }

  /**
   * Removes a rating from the list by given ID
   * @param ID ID of the player that is removed
   */
  public void deleteByID(int ID){
    for (int i = 0; i < size(); i++)
    {
      if (getRating(i).getID() == ID){
        ratingList.remove(i);
      }
    }
  }

  /**
   * Remove the rating on the given index from the list
   * @param index index of the removed rating
   */
  public void delete(int index){
    ratingList.remove(index);
  }
}
