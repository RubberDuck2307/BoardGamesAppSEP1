package Model;

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
  public Rating getRatingByID(int ID){
    for (int i = 0; i < size(); i++)
    {
      if(ID == getRating(i).getID()){
        return getRating(i);
      }
    }
    return null;
  }

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
  public RatingsList getRatings(){
    RatingsList newRatingsList = new RatingsList();
    for (int i = 0; i < size(); i++)
    {
        newRatingsList.addRating(getRating(i));
    }
    return newRatingsList;
  }
  @Override public String toString()
  {
    return "Model.RatingsList{" + "ratingList=" + ratingList + '}';
  }

  public void deleteByID(int ID){
    for (int i = 0; i < size(); i++)
    {
      if (getRating(i).getID() == ID){
        ratingList.remove(i);
      }
    }
  }
  public void delete(int index){
    ratingList.remove(index);
  }
}
