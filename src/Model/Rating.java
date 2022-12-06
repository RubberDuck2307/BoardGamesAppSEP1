package Model;

public class Rating
{
  /**
   * A class representing a rating of a board game
   */
  private int ID;
  private int value;
  private int playerID;
  private int gameID;

  /**
   * 3-argument constructor calling the set method. ID is set to -1.
   * @param value the value in the range 1-5, expressing how good the game is, while 5 being the best.
   * @param playerID the id of the player who rated the game
   * @param gameID the id of the game which is rated
   */
  public Rating(int value, int playerID, int gameID)
  {
    int ID = -1;
    set(ID, value, playerID, gameID);
  }

  /**
   * 4-argument constructor calling the set method
   * @param ID The ID of the rating
   * @param value the value in the range 1-5, expressing how good the game is, while 5 being the best.
   * @param playerID the id of the player who rated the game
   * @param gameID the id of the game which is rated
   */
  public Rating(int ID, int value, int playerID, int gameID)
  {
    set(ID, value, playerID, gameID);
  }
  /**
   * Setter for all attributes
   * @param ID The ID of the rating
   * @param value the value in the range 1-5, expressing how good the game is, while 5 being the best.
   * @param playerID the id of the player who rated the game
   * @param gameID the id of the game which is rated
   */
  public void set(int ID, int value, int playerID, int gameID)
  {
    this.ID = ID;
    this.value = value;
    this.playerID = playerID;
    this.gameID = gameID;
  }

  /**
   * Getter for ID
   */
  public int getID()
  {
    return ID;
  }

  /**
   * Setter for ID
   */
  public void setID(int ID)
  {
    this.ID = ID;
  }

  /**
   * Getter for value
   */
  public int getValue()
  {
    return value;
  }

  /**
   * Setter for value
   */
  public void setValue(int value)
  {
    this.value = value;
  }

  /**
   * Getter for playerID
   */
  public int getPlayerID()
  {
    return playerID;
  }

  /**
   * Setter for playerID
   */
  public void setPlayerID(int playerID)
  {
    this.playerID = playerID;
  }

  /**
   * Getter for gameID
   */
  public int getGameID()
  {
    return gameID;
  }


  /**
   * Setter for gameID
   */
  public void setGameID(int gameID)
  {
    this.gameID = gameID;
  }

  /**
   *
   * @return values of all attributes as string
   */
  @Override public String toString()
  {
    return "Model.Rating{" + "ID=" + ID + ", value=" + value + ", playerID=" + playerID
        + ", gameID=" + gameID + '}';
  }
}
