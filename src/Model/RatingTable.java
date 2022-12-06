package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RatingTable
{
  private StringProperty player;
  private IntegerProperty rating;
  private int ID;

  public RatingTable(String player, int rating, int ID){
    this.player = new SimpleStringProperty(player);
    this.rating = new SimpleIntegerProperty(rating);
    this.ID = ID;
  }

  public String getPlayer()
  {
    return player.get();
  }

  public StringProperty playerProperty()
  {
    return player;
  }

  public void setPlayer(String player)
  {
    this.player.set(player);
  }

  public int getRating()
  {
    return rating.get();
  }

  public IntegerProperty ratingProperty()
  {
    return rating;
  }

  public void setRating(int rating)
  {
    this.rating.set(rating);
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }
}
