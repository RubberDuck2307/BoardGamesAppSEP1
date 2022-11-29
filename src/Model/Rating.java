package Model;

public class Rating
{
  private int ID;
  private int value;
  private int playerID;
  private int gameID;


  public Rating(int value, int playerID, int gameID)
  {
    int ID = -1;
    set(ID, value, playerID, gameID);
  }

  public Rating(int ID, int value, int playerID, int gameID)
  {
    set(ID, value, playerID, gameID);
  }

  public void set(int ID, int value, int playerID, int gameID)
  {
    this.ID = ID;
    this.value = value;
    this.playerID = playerID;
    this.gameID = gameID;
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

  public int getPlayerID()
  {
    return playerID;
  }

  public void setPlayerID(int playerID)
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


  @Override public String toString()
  {
    return "Model.Rating{" + "ID=" + ID + ", value=" + value + ", playerID=" + playerID
        + ", gameID=" + gameID + '}';
  }
}
