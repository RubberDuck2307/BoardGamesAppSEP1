public class Rating
{
  private int ID;
  private int value;
  private int playerID;
  private int gameID;
  private String review;

  public Rating(int value, int playerID, int gameID, String review)
  {
    int ID = -1;
    set(ID, value, playerID, gameID, review);
  }

  public Rating(int ID, int value, int playerID, int gameID, String review)
  {
    set(ID, value, playerID, gameID, review);
  }

  public void set(int ID, int value, int playerID, int gameID, String review)
  {
    this.ID = ID;
    this.value = value;
    this.playerID = playerID;
    this.gameID = gameID;
    this.review = review;
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }

  public int getValue()
  {
    return value;
  }

  public void setValue(int value)
  {
    this.value = value;
  }

  public int getplayerID()
  {
    return playerID;
  }

  public void setplayerID(int playerID)
  {
    this.playerID = playerID;
  }

  public int getGameID()
  {
    return gameID;
  }

  public void setGameID(int gameID)
  {
    this.gameID = gameID;
  }

  public String getReview()
  {
    return review;
  }

  public void setReview(String review)
  {
    this.review = review;
  }

  @Override public String toString()
  {
    return "Rating{" + "ID=" + ID + ", value=" + value + ", playerID=" + playerID
        + ", gameID=" + gameID + ", review=" + review + '}';
  }
}
